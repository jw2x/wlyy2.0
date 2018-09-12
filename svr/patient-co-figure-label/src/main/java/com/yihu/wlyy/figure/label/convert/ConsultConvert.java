package com.yihu.wlyy.figure.label.convert;

import com.yihu.figure_label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author litaohong on 2018/4/8
 * @project patient-co-management
 * 咨询问题标签
 */
@Service

public class ConsultConvert implements Convert{

    @Autowired
    private ConvertHelper convertHelper;

    @Override
    public List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        FlLabelDict dict = flLabelDictList.get(0);
        for(DataModel dataModel:modelList){
            SaveModel saveModel = new SaveModel();
            saveModel.setIdcard(dataModel.getIdcard());
            saveModel.setLabelType(dict.getParentCode());
            saveModel.setLabelCode(dict.getLabelCode());
            saveModel.setLabelName(dict.getLabelName());
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
