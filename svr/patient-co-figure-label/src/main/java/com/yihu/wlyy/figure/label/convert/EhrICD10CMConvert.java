package com.yihu.wlyy.figure.label.convert;

import com.yihu.base.SolrHelper;
import com.yihu.figure_label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import com.yihu.wlyy.figure.label.util.TimeUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author litaohong on 2018/5/7
 * @project patient-co-management
 * 医疗云数据，标准疾病诊断标签
 */
@Component
public class EhrICD10CMConvert implements Convert{
    public static final int solrFqSize = 1000;
    @Autowired
    private ConvertHelper convertHelper;

    @Autowired
    private SolrHelper solrHelper;

    @Autowired
    @Qualifier("healtharchiveTemplate")
    private JdbcTemplate healtharchiveTemplate;

    @Override
    public List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        if(CollectionUtils.isEmpty(hbaseDatas)){
            return saveModels;
        }
        FlLabelDict one = flLabelDictList.get(0);
        StringBuilder code = new StringBuilder();
        List<String> rowkeyList = new ArrayList<>();
        //此次循环，先剔除无用数据
        for (int i = 0; i < hbaseDatas.size(); i++) {

            if (null == hbaseDatas.get(i).get("diagnosis")) {
                continue;
            }
            Object labelName = hbaseDatas.get(i).get("diagnosis_name");
            if (null == labelName) {
                continue;
            }
            String labelCodeStr = String.valueOf(hbaseDatas.get(i).get("diagnosis"));
            if (labelCodeStr.contains(";")) {
                labelCodeStr = labelCodeStr.replace(";", "");
            }
            code.append("'").append(labelCodeStr).append("',");
            rowkeyList.add(String.valueOf(hbaseDatas.get(i).get("rowkey")));
        }

        // 根据rowkey去solr查子表的rowkey，因为rowkey含有字典集，根据字典集即可判断是否新生儿，孕妇等
        String q = "*:*";
        // 返回指定的字段
        String fl = "rowkey,profile_id";
        int num = rowkeyList.size()/solrFqSize + 1;
        List<SolrDocumentList> documentList = new ArrayList<>();
        for(int i = 0; i < num; i++){
            List<String> subList = null;
            if(i == num - 1){
                subList = rowkeyList.subList(i*solrFqSize,rowkeyList.size());
            }else{
                subList = rowkeyList.subList(i*solrFqSize,(i+1)*solrFqSize);
            }
            StringBuilder rowkeyFq = new StringBuilder("profile_id:(");
            for(int j = 0; j < subList.size();j++){
                if(j == subList.size() - 1){
                    rowkeyFq.append(rowkeyList.get(j)).append(")");
                }else{
                    rowkeyFq.append(rowkeyList.get(j)).append(" OR ");
                }
            }
            // 构造过滤查询参数
            String[] fq = {rowkeyFq.toString()};
            long count = 0;
            SolrDocumentList result = null;
            try {
                count = solrHelper.count(ConstantUtil.subcore,q);
                result = solrHelper.queryfl(ConstantUtil.subcore,q,fq,null,fl,0,count);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(!CollectionUtils.isEmpty(result)){
                documentList.add(result);
            }
        }
        Map<String,String> rowkwy2ProfileIdmap = new HashMap<>();
        if(CollectionUtils.isEmpty(documentList)){
            return saveModels;
        }
        for(SolrDocumentList sdl:documentList){
            sdl.forEach(
                    oneDocument -> {
                        String rowkey = String.valueOf(oneDocument.get("profile_id"));
                        String Subrowkey = String.valueOf(oneDocument.get("rowkey"));
                        if (rowkwy2ProfileIdmap.containsKey(rowkey) && rowkwy2ProfileIdmap.get(rowkey).contains(Subrowkey.split("\\$")[1])) {
                            return;
                        }
                        rowkwy2ProfileIdmap.put(rowkey, Subrowkey);
                    }
            );
        }

        String sql = "select code,chronic_flag,infectious_flag from icd10_dict where code in (" + code.substring(0,code.lastIndexOf(",")) + ")";
        List<Map<String,Object>> icd10Map = healtharchiveTemplate.queryForList(sql);
        Map<String,String> chronicMap = new HashMap<>();
        Map<String,String> infectiousMap = new HashMap<>();
        icd10Map.forEach(
                map -> {
                    if(null != map.get("chronic_flag")){
                        chronicMap.put(String.valueOf(map.get("code")),String.valueOf(map.get("chronic_flag")));
                    }
                    if(null != map.get("infectious_flag")){
                        infectiousMap.put(String.valueOf(map.get("code")),String.valueOf(map.get("infectious_flag")));
                    }
                }
        );
        hbaseDatas.forEach(
                hbaseMap -> {
                    SaveModel saveModel = new SaveModel();
                    Object idcard = null;
                    // ehr那边 EHR_000017 demographic_id 为身份证编码，优先选择EHR_000017，demographic_id作为补录
                    if(null != hbaseMap.get("EHR_000017")){
                        idcard = hbaseMap.get("EHR_000017");
                    }else if(null != hbaseMap.get("demographic_id")){
                        idcard = hbaseMap.get("demographic_id");
                    }else{
                        return;
                    }
                    saveModel.setIdcard(String.valueOf(idcard));
                    saveModel.setDictCode(one.getDictCode());
                    String labelCode = String.valueOf(hbaseMap.get("diagnosis"));
                    if(null == labelCode){
                        return;
                    }
                    Object labelName = hbaseMap.get("diagnosis_name");
                    if(null == labelName){
                        return;
                    }
                    saveModel.setLabelType(one.getParentCode());
                    saveModel.setLabelCode(String.valueOf(labelCode));
                    saveModel.setLabelName(String.valueOf(labelName));
                    String labelValue = "";
                    String[] codearr = labelCode.split(";");
                    for (String codeStr : codearr) {
                        if (null != chronicMap.get(codeStr) && null != infectiousMap.get(codeStr)) {
                            labelValue = "慢病;传染病";
                        } else if (null != chronicMap.get(codeStr)) {
                            labelValue = "慢病";
                        } else if (null != infectiousMap.get(codeStr)) {
                            labelValue = "传染病";
                        }
                    }
                    if(rowkwy2ProfileIdmap.containsKey(hbaseMap.get("rowkey")) && (rowkwy2ProfileIdmap.get(hbaseMap.get("rowkey")).contains("HDSB02_04") || rowkwy2ProfileIdmap.get(hbaseMap.get("rowkey")).contains("HDSB02_07") || rowkwy2ProfileIdmap.get(hbaseMap.get("rowkey")).contains("HDSB02_87"))){
                        labelValue = labelValue + ";孕妇";
                    }else if(rowkwy2ProfileIdmap.containsKey(hbaseMap.get("rowkey")) && (rowkwy2ProfileIdmap.get(hbaseMap.get("rowkey")).contains("HDSB01_01")) || (rowkwy2ProfileIdmap.containsKey(hbaseMap.get("rowkey")) && rowkwy2ProfileIdmap.get(hbaseMap.get("rowkey")).contains("HDSB01_03")) || (rowkwy2ProfileIdmap.containsKey(hbaseMap.get("rowkey")) && rowkwy2ProfileIdmap.get(hbaseMap.get("rowkey")).contains("HDSD00_89"))){
                        labelValue = labelValue + ";新生儿";
                    }
                    saveModel.setLabelValue(labelValue);
                    DataModel dataModel = new DataModel();
                    dataModel.setIdstr(String.valueOf(hbaseMap.get("rowkey")));
                    convertHelper.generateDataSource(dataModel,saveModel,sourceType,source);
                    String javaTime = TimeUtil.toJavaTime(String.valueOf(hbaseMap.get("event_date")));
                    saveModel.setSourceTime(javaTime);
                    saveModel.setCreateTime(DateFormatUtils.format(new Date(), ConstantUtil.date_format));
                    saveModel.setId(MakeIDUtil.makeSaveModelID(saveModel));
                    saveModels.add(saveModel);
                }
        );
        return saveModels;
    }


    class ThreadSolrSearch implements Callable {
        private String q;
        private String[] fq;
        private String fl;

        public ThreadSolrSearch(String q,String[] fq,String fl){
            this.q = q;
            this.fq = fq;
            this.fl = fl;
        }

        @Override
        public Object call() throws Exception {
            long count = 0;
            SolrDocumentList documentList = null;
            try {
                count = solrHelper.count(ConstantUtil.subcore,q);
                documentList = solrHelper.queryfl(ConstantUtil.subcore,q,fq,null,fl,0,count);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return documentList;
        }
    }

}

