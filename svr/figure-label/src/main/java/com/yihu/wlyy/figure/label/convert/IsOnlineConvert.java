package com.yihu.wlyy.figure.label.convert;

import com.yihu.wlyy.figure.label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 适用于线上/线下的签约、缴费
 */

@Component
public class IsOnlineConvert implements Convert {

    @Autowired
    private ConvertHelper convertHelper;

    @Override
    public List<SaveModel> convert(List<DataModel> models, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        if(CollectionUtils.isEmpty(flLabelDictList)){
            return saveModels;
        }
        FlLabelDict one = flLabelDictList.get(0);
        Map<String,String> map = new HashMap<>();
        flLabelDictList.forEach(
                fLlabelDict -> {
                    map.put(fLlabelDict.getLabelCode(),fLlabelDict.getLabelName());
                }
        );
        models.forEach(
                model -> {
                    SaveModel saveModel = new SaveModel();
                    saveModel.setIdcard(model.getIdcard());
                    saveModel.setLabelType(one.getParentCode());
                    saveModel.setLabelCode(model.getLabelCode());
                    saveModel.setLabelName(map.get(model.getLabelCode()));
                    saveModel.setCreateTime(DateFormatUtils.format(new Date(), ConstantUtil.date_format));

                    convertHelper.generateDataSource(model,saveModel,sourceType,source);
                    //如果fl_job_config表配置的id和数据来源不一致，则不保存数据
                    if(StringUtils.isEmpty(saveModel.getSource())){
                        return;
                    }
                    saveModel.setId(MakeIDUtil.makeSaveModelID(saveModel));
                    saveModels.add(saveModel);
                }
        );
        return saveModels;
    }
}