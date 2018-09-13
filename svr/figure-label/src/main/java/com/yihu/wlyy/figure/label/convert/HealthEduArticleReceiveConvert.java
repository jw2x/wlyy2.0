package com.yihu.wlyy.figure.label.convert;

import com.yihu.wlyy.figure.label.constant.BusinessConstant;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author litaohong on 2018/5/31
 * @project patient-co-management
 */
public class HealthEduArticleReceiveConvert implements Convert{

    public Map<String,String> code2IdcardMap = new ConcurrentHashMap<>();

    @Autowired
    private ConvertHelper convertHelper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initMap(){
        String sql = "select code,idcard from wlyy.wlyy_patient";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        list.forEach(
                map -> {
                    code2IdcardMap.put(String.valueOf(map.get("code")),String.valueOf(map.get("idcard")));
                }
        );

    }

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
            saveModel.setIdcard(code2IdcardMap.get(dataModel.getIdcard()));
            saveModel.setDictCode(dict.getDictCode());
            saveModel.setLabelType(dataModel.getParentCode());
            saveModel.setLabelCode(dataModel.getLabelCode());
            saveModel.setLabelName(dataModel.getLabelName());
            saveModel.setLabelValue(dataModel.getFirstLevelCategoryName() + BusinessConstant.separator + dataModel.getSecondLevelCategoryName());
            // 文章推送人名字
            saveModel.setSendName(dataModel.getSendName());
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
