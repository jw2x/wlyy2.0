package com.yihu.wlyy.figure.label.convert;

import com.yihu.figure_label.entity.FlLabelDict;
import com.yihu.figure_label.entity.FlLabelDictJob;
import com.yihu.wlyy.figure.label.enums.SourceTypeEnum;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.util.ApplicationContextHolderUtil;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.MakeIDUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author lith on 2018.03.14
 * 维度的key值转换器
 */
@Component
@Scope("prototype")
public class ConvertHelper {
    private Logger logger = LoggerFactory.getLogger(ConvertHelper.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BasicDictItemHelper basicDictItemHelper;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private int numPerPage = 50000; //分页，一次性最多循环100000万条

    /**
     * 进行具体标签生成转换
     * @param dataModels
     * @param hbaseDatas
     * @param flLabelDictJob
     * @param sourceType
     * @param source
     * @return
     * @throws Exception
     */
    public List<SaveModel> convert(List<DataModel> dataModels, List<Map<String,Object>> hbaseDatas,FlLabelDictJob flLabelDictJob, String sourceType, String source) throws Exception {
        List<SaveModel> saveModels = new ArrayList<>();
        //如果源数据为空
        if(CollectionUtils.isEmpty(dataModels) && CollectionUtils.isEmpty(hbaseDatas)){
            return saveModels;
        }
        //标签job未配置
        if(null == flLabelDictJob){
            return saveModels;
        }
        //转换器为空表示不需要转换
        if (StringUtils.isEmpty(flLabelDictJob.getConvertClazz())) {
            if(dataModels.size() >= numPerPage){
                int number = dataModels.size() / numPerPage + 1;
                for(int i = 0; i < number; i++){
                    MutilThreadConvert mutilThreadConvert = new MutilThreadConvert(dataModels.subList(i*numPerPage,(i+1)*numPerPage),flLabelDictJob.getCategoryId(),sourceType,source);
                    Future<List<SaveModel>> future = executorService.submit(mutilThreadConvert);
                    saveModels.addAll(future.get());
                }
            }else{
                return generateSaveModleWithOutConvert(dataModels,flLabelDictJob.getCategoryId(),sourceType,source);
            }
            return saveModels;
        }
        //转换器不为空，判断配置的字典是否存在，字典不存在则表示此条数据错误
        List<FlLabelDict> flLabelDictList = jdbcTemplate.query(flLabelDictJob.getSql(), new BeanPropertyRowMapper(FlLabelDict.class));
        if(CollectionUtils.isEmpty(flLabelDictList)){
            return saveModels;
        }
        try {
            // 加载转换器类
            Class clazz = Class.forName(flLabelDictJob.getConvertClazz());
            Object obj = ApplicationContextHolderUtil.getContext().getBean(clazz);
            Method method = obj.getClass().getMethod("convert", List.class, List.class,List.class,String.class,String.class);
            // 调用转换方法，数据量大，分批次调用
            if(null != dataModels && dataModels.size() >= numPerPage){
                int number = dataModels.size() / numPerPage + 1;
                for(int i = 0; i < number; i++){
                    List<SaveModel> list = new ArrayList<>();
                    if (i == number - 1) {
                        int size = dataModels.size();
                        list = (List<SaveModel>) method.invoke(obj, dataModels.subList(i * numPerPage, size), hbaseDatas, flLabelDictList, sourceType, source);
                    } else {
                        list = (List<SaveModel>) method.invoke(obj, dataModels.subList(i * numPerPage, (i + 1) * numPerPage), hbaseDatas, flLabelDictList, sourceType, source);
                    }
                    saveModels.addAll(list);
                }
                return saveModels;
            } else if(null != hbaseDatas && hbaseDatas.size() >= numPerPage){
                int number = hbaseDatas.size() / numPerPage + 1;
                for(int i = 0; i < number; i++){
                    List<SaveModel> list = new ArrayList<>();
                    if (i == number - 1) {
                        int size = hbaseDatas.size();
                        list = (List<SaveModel>) method.invoke(obj, dataModels, hbaseDatas.subList(i * numPerPage, size), flLabelDictList, sourceType, source);
                    }else {
                        list = (List<SaveModel>) method.invoke(obj, dataModels, hbaseDatas.subList(i*numPerPage,(i+1)*numPerPage),flLabelDictList,sourceType,source);
                    }
                    saveModels.addAll(list);
                }
                return saveModels;
            }
            else{
                 saveModels = (List<SaveModel>) method.invoke(obj, dataModels, hbaseDatas,flLabelDictList,sourceType,source);
            }
        } catch (Exception e) {

          logger.error("customized class or convert() method not found:" + flLabelDictJob.getConvertClazz());
            e.printStackTrace();
            return saveModels;
        }
        return saveModels;
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
    public List<SaveModel> generateSaveModleWithOutConvert(List<DataModel> dataModels,Long categoryId,String sourceType,String source){
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
            this.generateDataSource(dataModel,saveModel,sourceType,source);
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

    /**
     * 生成数据来源
     * @param dataModel
     * @param saveModel
     * @param sourceType
     * @param sources
     * @return
     */
    public void generateDataSource(DataModel dataModel,SaveModel saveModel,String sourceType,String sources) {
        if(!StringUtils.endsWithIgnoreCase(SourceTypeEnum.MYSQL.toString(),sourceType) && !StringUtils.endsWithIgnoreCase(SourceTypeEnum.ELASTICSEARCH.toString(),sourceType) && !StringUtils.endsWithIgnoreCase(SourceTypeEnum.HBASE.toString(),sourceType)){
            return;
        }
        StringBuilder ids = new StringBuilder();
        // 默认值为0
        if(dataModel.getId() != null && dataModel.getId() != 0 ){
            ids.append(dataModel.getId()).append(",");
        }
        if(dataModel.getId1() != null && dataModel.getId1() != 0 ){
            ids.append(dataModel.getId1()).append(",");
        }
        if(dataModel.getId2() != null && dataModel.getId2() != 0 ){
            ids.append(dataModel.getId2()).append(",");
        }
        if(dataModel.getId3() != null && dataModel.getId3() != 0 ){
            ids.append(dataModel.getId3()).append(",");
        }
        if(dataModel.getId4() != null && dataModel.getId4() != 0 ){
            ids.append(dataModel.getId4());
        }
        if(!StringUtils.isEmpty(dataModel.getIdstr())){
            ids.append(dataModel.getIdstr());
        }
        if(!StringUtils.isEmpty(dataModel.getIdstr1())){
            ids.append(dataModel.getIdstr1());
        }
        if(!StringUtils.isEmpty(dataModel.getIdstr2())){
            ids.append(dataModel.getIdstr2());
        }
        String[] idArr = ids.toString().split(",");
        String[]sourceArr = sources.split(",");
        if(idArr.length != sourceArr.length){
            logger.error("number of id is not compatible with table's setted in fl_job_config[sql,source],model do not save!");
            return;
        }
        StringBuilder source = new StringBuilder();
        for(int i = 0; i < idArr.length;i++){
            source.append(sourceType).append(".").append(sourceArr[i]).append(".").append(idArr[i]);
            if(i != idArr.length -1){
                source.append(",");
            }
        }
        saveModel.setSource(source.toString());
        return ;
    }
}
