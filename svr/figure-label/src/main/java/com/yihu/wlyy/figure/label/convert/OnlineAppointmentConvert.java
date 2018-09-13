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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author litaohong on 2018/4/12
 * @project patient-co-management
 * 网上预约行为标签
 */
@Component
public class OnlineAppointmentConvert implements Convert{

    @Autowired
    private ConvertHelper convertHelper;

    @Override
    public List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        FlLabelDict dict = flLabelDictList.get(0);
        for(DataModel dataModel : modelList){
            SaveModel saveModel = new SaveModel();
            saveModel.setIdcard(dataModel.getIdcard());
            saveModel.setDictCode(dict.getDictCode());
            saveModel.setLabelType(dict.getParentCode());
            saveModel.setLabelCode(dataModel.getLabelCode());
            saveModel.setLabelName(dataModel.getParentName() + "-" + dataModel.getLabelName());
            saveModel.setLabelValue(dataModel.getLabelValue());
            //如果有医生代预约
            if(!StringUtils.isEmpty(dataModel.getDoctor())){
                saveModel.setDoctor(dataModel.getDoctor());
            }
            convertHelper.generateDataSource(dataModel,saveModel,sourceType,source);
            if(StringUtils.isEmpty(saveModel.getSource())){
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
