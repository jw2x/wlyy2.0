package com.yihu.wlyy.figure.label.convert;

import com.yihu.wlyy.figure.label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import com.yihu.wlyy.figure.label.util.TimeUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author lith 2018/03/23
 * 多线程分页查询mysql数据，因为mysql数据量比较大的时候，会比较慢
 */
public class MutilThreadConvert implements Callable {

    private Logger logger = LoggerFactory.getLogger(MutilThreadConvert.class);

    @Autowired
    private ConvertHelper convertHelper;

    @Autowired
    private BasicDictItemHelper basicDictItemHelper;

    private List<DataModel> dataModels;
    private Long categoryId;
    private String sourceType;
    private String source;

    public MutilThreadConvert(List<DataModel> dataModels,Long categoryId, String sourceType, String source){
        this.dataModels = dataModels;
        this.categoryId = categoryId;
        this.sourceType = sourceType;
        this.source = source;
    }

    @Override
    public Object call() throws Exception {
        String title = Thread.currentThread().getName() + " generate savemodel";
        long start = System.currentTimeMillis();
        TimeUtil.start(logger,title,start);
        List<SaveModel> list = generateSaveModleWithOutConvert(dataModels,categoryId,sourceType,source);
        TimeUtil.finish(logger,title,start,System.currentTimeMillis());
        return list;
    }

    /**
     * 无需转换器直接转换，此种情况为源数据已经分类好标签
     * @param dataModels
     * @param categoryId
     * @param sourceType
     * @param source
     *
     * @return
     */
    public List<SaveModel> generateSaveModleWithOutConvert(List<DataModel> dataModels, Long categoryId, String sourceType, String source){
        String dictCode = basicDictItemHelper.dictCategoryMap.get(String.valueOf(categoryId));
        List<SaveModel> saveModels = new ArrayList<>();
        //lambda无法跳出循环
        for(DataModel dataModel:dataModels){
            SaveModel saveModel = new SaveModel();
            saveModel.setIdcard(dataModel.getIdcard());
            saveModel.setDictCode(dictCode);
            //找不到该类别
            if (null == basicDictItemHelper.labelDictMap.get(dictCode)) {
                logger.error("label dictCode not exist :【" + dictCode + "】");
                return new ArrayList<>();
            }
            //只有一种类别
            if(basicDictItemHelper.labelDictMap.get(dictCode).size() == 1){
                List<FlLabelDict> flLabelDictList = basicDictItemHelper.labelDictMap.get(dictCode);
                saveModel.setLabelType(flLabelDictList.get(0).getParentCode());
                saveModel.setLabelCode(flLabelDictList.get(0).getLabelCode());
                saveModel.setLabelName(flLabelDictList.get(0).getLabelName());
                if(!StringUtils.isEmpty(dataModel.getLabelValue())){
                    if(StringUtils.endsWithIgnoreCase("height_dict",dictCode)){
                        saveModel.setLabelValue(dataModel.getLabelValue()+"cm");
                    }
                    else if(StringUtils.endsWithIgnoreCase("weight_dict",dictCode)){
                        saveModel.setLabelValue(dataModel.getLabelValue()+"kg");
                    }else{
                        saveModel.setLabelValue(dataModel.getLabelValue());
                    }
                }
            }else{
                //当有多种类别，sql里构造好需要格式，例如不同code对应不同的label_name
                saveModel.setLabelType(dataModel.getParentCode());
                saveModel.setLabelCode(dataModel.getLabelCode());
                saveModel.setLabelName(dataModel.getLabelName());
            }
            saveModel.setCreateTime(DateFormatUtils.format(new Date(), ConstantUtil.date_format));
            convertHelper.generateDataSource(dataModel,saveModel,sourceType,source);
            //如果fl_job_config表配置的id和数据来源不一致，则不保存数据,原子性，全部不保存，不然部分存了部分没存不好剔除数据
            if(StringUtils.isEmpty(saveModel.getSource())){
                return new ArrayList<>();
            }
            if(!StringUtils.isEmpty(dataModel.getSourceTime())){
                saveModel.setSourceTime(dataModel.getSourceTime());
            }
            saveModel.setId(MakeIDUtil.makeSaveModelID(saveModel));
            saveModels.add(saveModel);
        }
        return saveModels;
    }
}
