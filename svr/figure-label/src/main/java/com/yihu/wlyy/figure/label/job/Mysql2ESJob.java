package com.yihu.wlyy.figure.label.job;

import com.yihu.wlyy.figure.label.constant.BusinessConstant;
import com.yihu.wlyy.figure.label.controller.JobController;
import com.yihu.wlyy.figure.label.convert.ConvertHelper;
import com.yihu.wlyy.figure.label.dao.FlJobConfigDao;
import com.yihu.wlyy.figure.label.dao.FlLabelDictJobDao;
import com.yihu.wlyy.figure.label.entity.FlJobConfig;
import com.yihu.wlyy.figure.label.entity.FlLabelDictJob;
import com.yihu.wlyy.figure.label.enums.JobSqlFieldTypeEnum;
import com.yihu.wlyy.figure.label.extract.MysqlExtracter;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.service.JobService;
import com.yihu.wlyy.figure.label.storage.Store2ES;
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

/**
 * Created by chenweida on 2018/3/7.
 */
//@Component
@Service
@Scope("prototype")
@DisallowConcurrentExecution//防止到了执行时间点前一任务还在执行中，但是这时有空闲的线程，那么马上又会执行，这样一来就会存在同一job被并行执行
public class Mysql2ESJob implements Job {

    private Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private FlJobConfigDao flJobConfigDao;

    @Autowired
    private FlLabelDictJobDao flLabelDictJobDao;

    @Autowired
    private MysqlExtracter mysqlExtracter;

    @Autowired
    private ConvertHelper convertHelper;

    @Autowired
    Store2ES store2ES;

    @Autowired
    private JobService jobService;

    private FlJobConfig flJobConfig;

    private Long flJobConfigId;

    private String sqlFieldValue;

    private String sqlFieldCondition;

    private String finalSql;

    private String countSql;

    private List<DataModel> dataModelList;

    private String sourceType;

    private String source;

    private String datasource;

    /**
     * 数据表的id，有些数据是按时间增量查询，有些数据是按表的主键id增量查询
     */
    private long lastDataId;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap paramsMap = context.getJobDetail().getJobDataMap();

        initParams(paramsMap);
        //根据obconfig里配置的sql提取数据
        extract();
        //数据转换
        List<SaveModel> list = convert();
        //转换后的结果
        if (!CollectionUtils.isEmpty(list)) {
            //数据保存
            boolean bool = save(list);

            //增量存储成功后，修改增量的czrq时间为当前时间或主键id为上次获取到的数据的最后的一条的id
            if(StringUtils.endsWithIgnoreCase(this.flJobConfig.getSqlFieldType().toString(), JobSqlFieldTypeEnum.TIME.toString())){
                jobService.updateFieldValuetoCurrentTimeOrId(this.flJobConfigId,null,bool);
            }else if(StringUtils.endsWithIgnoreCase(this.flJobConfig.getSqlFieldType().toString(), JobSqlFieldTypeEnum.NUM.toString())){
                int index = dataModelList.size();
                lastDataId = dataModelList.get(index -1).getId();
                jobService.updateFieldValuetoCurrentTimeOrId(this.flJobConfigId,this.lastDataId,bool);
            }
        }
    }

    public void initParams(JobDataMap paramsMap){
        this.flJobConfigId = (Long)paramsMap.get("jobConfig");
        this.flJobConfig = flJobConfigDao.findById(this.flJobConfigId);
        this.sourceType = flJobConfig.getSourceType();
        this.source = flJobConfig.getSource();
        this.datasource = flJobConfig.getDatasource();
        this.sqlFieldCondition = paramsMap.getString(BusinessConstant.SQLFIELDCONDITION);
        this.sqlFieldValue = flJobConfig.getSqlFieldValue();
        //没有传增量值，以数据库配置的默认值为查询条件
        if(StringUtils.isEmpty(this.sqlFieldValue)){
            this.sqlFieldValue = this.flJobConfig.getSqlFieldValue();
            this.sqlFieldCondition =">";
        }
        //1抽取数据
        String sql = this.flJobConfig.getQuery_sql();
        String sqlFiled = this.flJobConfig.getSqlField();

        this.finalSql = getFinalSql(sql,sqlFiled,sqlFieldValue);
        this.countSql = getTotalCountSql(flJobConfig.getCountSql(),sqlFiled, sqlFieldValue);
    }

    /**
     * 提取数据，按数据库中配置的增量条件提取
     */

    public void extract(){
        this.dataModelList = mysqlExtracter.extractData(this.finalSql,this.countSql,this.datasource);
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

    /**
     * 拼凑获取增量数据的sql
     * @param sql
     * @param sqlFiledValue
     * @return
     */
    public String getJobConfigSql(String sql,String sqlFiled,String sqlFiledCondition,String sqlFiledValue){
        StringBuilder result = new StringBuilder();
        result.append(sql).append(" ");
        if(StringUtils.isEmpty(sqlFiledCondition)){
            return result.toString();
        }
        String[] sqlFiledConditionStr = {};
        if (sqlFiledCondition.contains(",")){
            sqlFiledConditionStr = sqlFiledCondition.split(",");
        }

        StringBuffer sqlFiledString = new StringBuffer();
        if (sqlFiledConditionStr.length>0 && sqlFiledValue.contains(",")){
            String[] sqlFiledValues = sqlFiledValue.split(",");
            sqlFiledString.append(sqlFiled).append(sqlFiledConditionStr[0]).append("\'"+sqlFiledValues[0]+"\'").append(" and ").append(sqlFiled).append(sqlFiledConditionStr[1]).append("\'"+sqlFiledValues[1]+"\'");
        }else{
            sqlFiledString.append(sqlFiled).append(sqlFiledCondition).append("\'"+sqlFiledValue+"\'");
        }

        if (sql.contains("where") &&  !sql.contains("$")) {
            result.append(" and ").append(sqlFiledString.toString());
        }else if (sql.contains("$")){
            StringBuffer otherCondition = new StringBuffer();
            if (sql.contains("where")){
                otherCondition.append(" and ").append(sqlFiledString.toString());
            }else{
                otherCondition.append(" where ").append(sqlFiledString.toString());
            }
            result.replace(result.indexOf("$"),result.indexOf("$")+1,otherCondition.toString());
        }else {
            result.append(" where ").append(sqlFiledString.toString());
        }

        return result.toString();
    }

    /**
     * 最终执行获取数据的sql，替换增量字段为具体值
     * @param sql
     * @param sqlfield
     * @param sqlfieldValue
     * @return
     */
    public String getFinalSql(String sql, String sqlfield, String sqlfieldValue) {
        String firstReplaceSql = sql.replace(BusinessConstant.sql_field_str,sqlfield);
        String result = firstReplaceSql.replace(BusinessConstant.sql_field_value_str,sqlfieldValue);
        return result.toString();
    }

    /**
     * 统计增量数据的条数
     * @param countSql
     * @param sqlfield
     * @param sqlfieldValue
     * @return
     */
    public String getTotalCountSql(String countSql, String sqlfield, String sqlfieldValue) {
        if(StringUtils.isEmpty(countSql)){
            logger.error("countSql is null,job_config id = :" + this.flJobConfigId);
            return "";
        }
       String firstReplaceSql = countSql.replace(BusinessConstant.sql_field_str,sqlfield);
       String result = firstReplaceSql.replace(BusinessConstant.sql_field_value_str,sqlfieldValue);
        return result.toString();
    }
}

