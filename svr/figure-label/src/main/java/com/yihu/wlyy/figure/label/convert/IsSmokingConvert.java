package com.yihu.wlyy.figure.label.convert;

import com.yihu.wlyy.figure.label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author humingfen on 2018.03.14
 * 是否抽烟标签转换器
 */
@Component
public class IsSmokingConvert implements Convert{
    @Autowired
    private ConvertHelper convertHelper;

    @Override
    public List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        // 抽烟code为2，代表未知
        String hasSmoking = "2";
        Map<String,String> dictMap = new HashMap<>();
        flLabelDictList.forEach(
                one ->{
                    dictMap.put(one.getLabelCode(),one.getLabelName());
                }
        );
        for(DataModel dataModel:modelList){
            if(StringUtils.isNotBlank(dataModel.getLabelValue())) {
                float dailySmoking = Float.parseFloat(dataModel.getLabelValue());
                if(dailySmoking > 0){
                    hasSmoking = "0";
                }else if(dailySmoking <= 0) {
                    hasSmoking = "1";
                }
            }
            SaveModel saveModel = new SaveModel();
            FlLabelDict dict = flLabelDictList.get(Integer.parseInt(hasSmoking));
            saveModel.setId(MakeIDUtil.makeEsSaveModelID(dataModel,dict));
            saveModel.setLabelType(dict.getParentCode());
            saveModel.setLabelCode(hasSmoking);
            saveModel.setLabelName(dictMap.get(hasSmoking));
            convertHelper.generateDataSource(dataModel,saveModel,sourceType,source);
            //如果fl_job_config表配置的id和数据来源不一致，则不保存数据
            if (StringUtils.isEmpty(saveModel.getSource())) {
                return new ArrayList<>();
            }
            saveModel.setCreateTime(DateFormatUtils.format(new Date(), ConstantUtil.date_format));
            saveModels.add(saveModel);
        }
        return saveModels;
    }
}
