package com.yihu.wlyy.figure.label.convert;

import com.yihu.figure_label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.IdCardUtil;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author lith on 2018.03.14
 * 年龄标签转换器
 */
@Component
public class AgeRangeConvert implements Convert {

    @Autowired
    private ConvertHelper convertHelper;

    @Override
    public List<SaveModel> convert(List<DataModel> models, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        FlLabelDict one = flLabelDictList.get(0);
        Map<String,String> map = new HashMap<>();

        flLabelDictList.forEach(
                fLlabelDict -> {
                    map.put(fLlabelDict.getLabelCode(),fLlabelDict.getLabelName());
                }
        );
        models.forEach(
                model -> {
                    int age = IdCardUtil.getAgeForIdcard(model.getIdcard());
                    String labelCode = getAgeLabelCode(age);
                    SaveModel saveModel = new SaveModel();
                    saveModel.setDictCode(one.getDictCode());
                    saveModel.setIdcard(model.getIdcard());
                    saveModel.setLabelType(one.getParentCode());
                    saveModel.setLabelCode(one.getLabelCode());
                    saveModel.setLabelName(map.get(labelCode));

                    //源数据来源时间
                    if(null != model.getSourceTime()){
                        saveModel.setSourceTime(model.getSourceTime());
                    }

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

    /**
     * fl_label_dict配置的code
     * @param age
     * @return
     */
    public String getAgeLabelCode(int age) {
        if (age <= 6) {
            return "10";
        }else if(age <=18){
            return "11";
        }else if(age <=30){
            return "12";
        }else if(age <=50){
            return "13";
        }else if(age <=65){
            return "14";
        }else{
            return "15";
        }
    }
}