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
 * @author litaohong on 2018/03/27
 * @project patient-co-management
 * 线上咨询问题标签（包括续方咨询，续方咨询只是线上咨询的一种类型）
 */
@Component
public class OnLineConsultConvert implements Convert {

    @Autowired
    private ConvertHelper convertHelper;

    @Override
    public List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        FlLabelDict dict = flLabelDictList.get(0);
        Map<String,String> dictMap = new HashMap<>();
        flLabelDictList.forEach(
                one ->{
                    dictMap.put(one.getLabelCode(),one.getLabelName());
                }
        );
        for(DataModel dataModel:modelList){
            SaveModel saveModel = new SaveModel();
            saveModel.setIdcard(dataModel.getIdcard());
            saveModel.setLabelType(dict.getParentCode());
            saveModel.setLabelCode(dataModel.getLabelCode());
            saveModel.setLabelName(dictMap.get(dataModel.getLabelCode()));
            saveModel.setLabelValue(dataModel.getLabelValue());
            convertHelper.generateDataSource(dataModel,saveModel,sourceType,source);
            if (StringUtils.isEmpty(saveModel.getSource())) {
                return new ArrayList<>();
            }
            saveModel.setSourceTime(dataModel.getSourceTime());
            saveModel.setCreateTime(DateFormatUtils.format(new Date(), ConstantUtil.date_format));
            saveModel.setId(MakeIDUtil.makeSaveModelID(saveModel));
            saveModels.add(saveModel);
        }
        return saveModels;
    }
}
