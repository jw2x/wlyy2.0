package com.yihu.wlyy.figure.label.convert;

import com.yihu.figure_label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import com.yihu.wlyy.figure.label.util.TimeUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author litaohong on 2018/5/7
 * @project patient-co-management
 * 医疗云数据，健康问题标签
 */
@Component
public class EhrHealthProblemConvert implements Convert{

    @Autowired
    private ConvertHelper convertHelper;

    @Override
    public List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        FlLabelDict one = flLabelDictList.get(0);
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
                    Object labelCode = hbaseMap.get("health_problem");
                    if(null == labelCode){
                        return;
                    }
                    Object labelName = hbaseMap.get("health_problem_name");
                    if(null == labelName){
                        return;
                    }
                    saveModel.setLabelType(one.getParentCode());
                    saveModel.setLabelCode(String.valueOf(labelCode));
                    saveModel.setLabelName(String.valueOf(labelName));
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
}
