package com.yihu.wlyy.figure.label.convert;

import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.wlyy.figure.label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author litaohong on 2018/4/8
 * @project patient-co-management
 * 医生咨询分析--及时回复标签
 */
@Component
public class ReplyInTimeConvert implements Convert{

    @Autowired
    private ConvertHelper convertHelper;

    @Autowired
    private ElastricSearchHelper elastricSearchHelper;

    @Override
    public List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source) {
        List<SaveModel> saveModels = new ArrayList<>();
        FlLabelDict dict = flLabelDictList.get(0);
        for(DataModel model:modelList){
            SaveModel saveModel = new SaveModel();
            saveModel.setIdcard(model.getIdcard());
            saveModel.setLabelType(dict.getParentCode());
            saveModel.setLabelCode(dict.getLabelCode());
            saveModel.setLabelName(dict.getLabelName());
            saveModel.setLabelValue(model.getLabelValue());
            //记录回复时间，方便以后统计一定时间内的回复率
            saveModel.setSourceTime(model.getSourceTime());
            convertHelper.generateDataSource(model,saveModel,sourceType,source);
            if(StringUtils.isEmpty(saveModel.getSource())){
                return new ArrayList<>();
            }
            saveModel.setId(MakeIDUtil.makeSaveModelID(saveModel));
            saveModels.add(saveModel);
        }
        return saveModels;
    }


    //lambda无法跳出循环
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        List list = new ArrayList();
        list.addAll(CollectionUtils.arrayToList(arr));
        list.forEach(
                one ->{
                    if(StringUtils.endsWithIgnoreCase(one.toString(),"3")){
                        return;
                    }
                    System.out.println(one);
                }
        );
        System.out.println("*********************************************************");
        for(Object one:list){
            if(StringUtils.endsWithIgnoreCase(one.toString(),"3")){
                return;
            }
            System.out.println(one);
        }
    }

}
