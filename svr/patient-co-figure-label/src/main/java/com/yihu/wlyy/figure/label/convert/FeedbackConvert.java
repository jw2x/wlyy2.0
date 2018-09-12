package com.yihu.wlyy.figure.label.convert;

import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.figure_label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author litaohong on 2018/4/9
 * @project patient-co-management
 * 反馈行为标签  记录用户是否有反馈过，反馈了几次
 */
@Component
public class FeedbackConvert implements Convert {

    @Autowired
    private ConvertHelper convertHelper;

    @Autowired
    private ElastricSearchHelper elastricSearchHelper;

    private Map<String,String> distinctIdcardMap = new HashMap<>();

    @Override
    public List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        FlLabelDict dict = flLabelDictList.get(0);
        Map<String,String> mapES = countESFeedbackByIdcard(modelList);
        Map<String,DataModel> modelMap = distinctIdcard(modelList);
        for(String idcard : modelMap.keySet()){
            SaveModel saveModel = new SaveModel();
            int value = 0;
            saveModel.setIdcard(idcard);
            saveModel.setLabelType(dict.getParentCode());
            saveModel.setLabelCode(dict.getLabelCode());
            saveModel.setLabelName(dict.getLabelName());
            if(mapES.containsKey(idcard)){
                value = Integer.parseInt(mapES.get(idcard));
            }
            if(distinctIdcardMap.containsKey(idcard)){
                value = Integer.parseInt(distinctIdcardMap.get(idcard)) + value;
            }
            saveModel.setLabelValue(String.valueOf(value));
            convertHelper.generateDataSource(modelMap.get(idcard),saveModel,sourceType,source);
            if (StringUtils.isEmpty(saveModel.getSource())) {
                return new ArrayList<>();
            }
            saveModel.setSourceTime(modelMap.get(idcard).getSourceTime());
            saveModel.setCreateTime(DateFormatUtils.format(new Date(), ConstantUtil.date_format));
            saveModel.setId(MakeIDUtil.makeSaveModelID(saveModel));
            saveModels.add(saveModel);
        }
        return saveModels;
    }


    /**
     * 累加es中每个用户反馈的次数
     * @return
     */
    public Map<String,String> countESFeedbackByIdcard(List<DataModel> modelList){
        Map<String, String> result = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
        StringBuilder idcardStr = new StringBuilder();
        idcardStr.append("(");
        for (int i = 0; i < modelList.size(); i++) {
            idcardStr.append("'").append(modelList.get(i).getIdcard()).append("'");
            if (i != modelList.size() - 1) {
                idcardStr.append(",");
            }
        }
        idcardStr.append(")");
        //先查找出es中已有的用户的反馈次数
        String querySql = "select labelValue from " + ConstantUtil.figure_label_es_index + "/" + ConstantUtil.figure_label_es_type + " where lableType = 'feedback_dict' and idcard in " + idcardStr.toString();
        list = elastricSearchHelper.excuceSQL(querySql);
        //组装以idcard为key的map
        if(!CollectionUtils.isEmpty(list)){
            list.forEach(
                    data -> {
                        result.put(data.get("idcard").toString(), data.get("lableValue").toString());
                    }
            );
        }
        return result;
    }


    /**
     * 统计从数据库中查询出来的idcard的反馈记录次数，一个人可能会有多条反馈
     * @param modelList
     * @return
     */
    public Map<String,DataModel> distinctIdcard(List<DataModel> modelList){
        Map<String,DataModel> result = new HashMap<>();
        modelList.forEach(
                model ->{
                    if(distinctIdcardMap.containsKey(model.getIdcard())){
                        int count = Integer.parseInt(distinctIdcardMap.get(model.getIdcard())) + 1;
                        distinctIdcardMap.put(model.getIdcard(),String.valueOf(count));
                    }else {
                        distinctIdcardMap.put(model.getIdcard(),"1");
                    }

                    if(result.containsKey(model.getIdcard())){
                        return;
                    }else{
                        result.put(model.getIdcard(),model);
                    }
                }
        );
        return result;
    }
}
