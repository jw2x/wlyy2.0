package com.yihu.wlyy.figure.label.convert;

import com.yihu.figure_label.entity.FlLabelDict;
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
 * 地址标签转换器
 */
@Component
public class AddressConvert implements Convert{
    @Autowired
    private ConvertHelper convertHelper;

    @Override
    public List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        FlLabelDict dict = flLabelDictList.get(0);
        for(DataModel dataModel : modelList){
            SaveModel saveModel = new SaveModel();
            saveModel.setIdcard(dataModel.getIdcard());
            saveModel.setLabelType(dict.getParentCode());
            saveModel.setLabelCode(dict.getLabelCode());
            saveModel.setLabelName(dict.getLabelName());
            saveModel.setLabelValue(dataModel.getLabelValue());
            convertHelper.generateDataSource(dataModel,saveModel,sourceType,source);

            if(StringUtils.isEmpty(saveModel.getSource())){
                return new ArrayList<>();
            }
            saveModel.setCreateTime(DateFormatUtils.format(new Date(), ConstantUtil.date_format));

            saveModel.setId(MakeIDUtil.makeSaveModelID(saveModel));
            saveModels.add(saveModel);
        }
        return saveModels;
    }
}
