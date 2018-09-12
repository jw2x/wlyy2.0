package com.yihu.wlyy.figure.label.job;

import com.yihu.figure_label.entity.FlJobConfig;
import com.yihu.figure_label.entity.FlLabelDictJob;
import com.yihu.wlyy.figure.label.constant.BusinessConstant;
import com.yihu.wlyy.figure.label.controller.JobController;
import com.yihu.wlyy.figure.label.convert.ConvertHelper;
import com.yihu.wlyy.figure.label.dao.FlJobConfigDao;
import com.yihu.wlyy.figure.label.dao.FlLabelDictJobDao;
import com.yihu.wlyy.figure.label.enums.JobSqlFieldTypeEnum;
import com.yihu.wlyy.figure.label.extract.ESExtracter;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.service.JobService;
import com.yihu.wlyy.figure.label.storage.Store2ES;
import com.yihu.wlyy.figure.label.util.TimeUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
@DisallowConcurrentExecution//防止到了执行时间点前一任务还在执行中，但是这时有空闲的线程，那么马上又会执行，这样一来就会存在同一job被并行执行
public class Es2EsJob implements Job {

    private Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private FlJobConfigDao flJobConfigDao;

    @Autowired
    private FlLabelDictJobDao flLabelDictJobDao;

    @Autowired
    private ESExtracter esExtracter;

    @Autowired
    private ConvertHelper convertHelper;

    @Autowired
    Store2ES store2ES;

    @Autowired
    private JobService jobService;

    private FlJobConfig flJobConfig;

    private Long flJobConfigId;

    private String sqlFiledValue;

    private String sqlFiledCondition;

    private String finalSql;

    private List<DataModel> dataModelList;

    private String sourceType;

    private String source;

    /**
     * 数据表的id，有些数据是按时间增量查询，有些数据是按表的主键id增量查询
     */
    private long lastDataId;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap paramsMap = context.getJobDetail().getJobDataMap();
        initParams(paramsMap);
        //提取数据
        extract();
        //数据转换
        //数据转换
        List<SaveModel> list = convert();
        //转换后的结果
        if (!CollectionUtils.isEmpty(list)) {
            //数据保存
            boolean bool = save(list);

            //增量存储成功后，修改增量的czrq时间为当前时间或主键id为上次获取到的数据的最后的一条的id
            if (StringUtils.endsWithIgnoreCase(this.flJobConfig.getSqlFieldType().toString(), JobSqlFieldTypeEnum.TIME.toString())) {
                jobService.updateFieldValuetoCurrentTimeOrId(this.flJobConfigId, null, bool);
            } else if (StringUtils.endsWithIgnoreCase(this.flJobConfig.getSqlFieldType().toString(), JobSqlFieldTypeEnum.NUM.toString())) {
                int index = dataModelList.size();
                lastDataId = dataModelList.get(index - 1).getId();
                jobService.updateFieldValuetoCurrentTimeOrId(this.flJobConfigId, this.lastDataId, bool);
            }
        }
    }

    public void initParams(JobDataMap paramsMap){
        this.flJobConfigId = (Long)paramsMap.get("jobConfig");
        this.sourceType = String.valueOf(paramsMap.get("sourceType"));
        this.source = String.valueOf(paramsMap.get("source"));

        this.flJobConfig = flJobConfigDao.findById(this.flJobConfigId);
        this.sqlFiledValue = flJobConfig.getSqlFieldValue();
        this.sqlFiledCondition = paramsMap.getString(BusinessConstant.SQLFIELDCONDITION);
        //没有传增量值，以数据库配置的默认值为查询条件
        if(org.springframework.util.StringUtils.isEmpty(this.sqlFiledValue)){
            this.sqlFiledValue = this.flJobConfig.getSqlFieldValue();
            this.sqlFiledCondition= BusinessConstant.gt;
        }
        //1抽取数据
        String sql = this.flJobConfig.getQuery_sql();
        String sqlFiled = this.flJobConfig.getSqlField();
        this.finalSql = sql + " where " + sqlFiled + BusinessConstant.gt + "'" + TimeUtil.toEsTime(flJobConfig.getSqlFieldValue()) + "'";
    }

    /**
     * 提取数据，按数据库中配置的增量条件提取
     */
    public void extract(){
        this.dataModelList = esExtracter.extractData(this.finalSql,"");
    }

    /**
     * 转换
     */
    public List<SaveModel> convert(){
        List<SaveModel> list = new ArrayList<>();
        FlLabelDictJob flLabelDictJob = flLabelDictJobDao.findByJobId(this.flJobConfigId);
        try {
            list = convertHelper.convert(this.dataModelList, null,flLabelDictJob,this.sourceType,this.source);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    /**
     * 保存
     * @param list
     */
    public boolean save(List<SaveModel> list){
        boolean bool = true;
        try {
            store2ES.save(list);
        }catch (Exception e){
            logger.error("save to elasticsearch failed，convet data count:" + list.size());
            bool = false;
        }
        return bool;
    }

}
