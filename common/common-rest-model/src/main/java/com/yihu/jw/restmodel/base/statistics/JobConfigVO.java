package com.yihu.jw.restmodel.base.statistics;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author yeshijie on 2018/8/31.
 */
@ApiModel(value = "JobConfigVO", description = "统计job配置")
public class JobConfigVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saasId")
    private String saasId;
    @ApiModelProperty(value = "任务名称")
    private String jobName;//
    @ApiModelProperty(value = "任务描述")
    private String jobInfo;//
    @ApiModelProperty(value = "任务类型(0--单次执行  1--周期执行 2--监听任务)")
    private String jobType;//
    @ApiModelProperty(value = "任务执行的class")
    private String jobClass;//
    @ApiModelProperty(value = "统计sql语句")
    private String sql;//
    @ApiModelProperty(value = "统计总数语句")
    private String sqlCount;//
    @ApiModelProperty(value = "天数条件")
    private String sqlDay;//
    @ApiModelProperty(value = "年份条件")
    private String sqlYear;//
    @ApiModelProperty(value = "缓存的key")
    private String cacheKey;//
    @ApiModelProperty(value = "quartz表达式")
    private String quartzCron;//
    @ApiModelProperty(value = "1 启动 0停止")
    private String status;//
    @ApiModelProperty(value = "指标code")
    private String quotaCode;//
    @ApiModelProperty(value = "1: 正常 0： 删除")
    private Integer del;//
    @ApiModelProperty(value = "抽取类型   1或者为空：数据库 2ES")
    private String extractType;//
    @ApiModelProperty(value = "1增量 2到达量 3生成到达量也生成增量")
    private String timeLevel;//
    @ApiModelProperty(value = "增量时间间隔1天,2周,3月")
    private Integer incrementInterval;//

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlCount() {
        return sqlCount;
    }

    public void setSqlCount(String sqlCount) {
        this.sqlCount = sqlCount;
    }

    public String getSqlDay() {
        return sqlDay;
    }

    public void setSqlDay(String sqlDay) {
        this.sqlDay = sqlDay;
    }

    public String getSqlYear() {
        return sqlYear;
    }

    public void setSqlYear(String sqlYear) {
        this.sqlYear = sqlYear;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getQuartzCron() {
        return quartzCron;
    }

    public void setQuartzCron(String quartzCron) {
        this.quartzCron = quartzCron;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuotaCode() {
        return quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getExtractType() {
        return extractType;
    }

    public void setExtractType(String extractType) {
        this.extractType = extractType;
    }

    public String getTimeLevel() {
        return timeLevel;
    }

    public void setTimeLevel(String timeLevel) {
        this.timeLevel = timeLevel;
    }

    public Integer getIncrementInterval() {
        return incrementInterval;
    }

    public void setIncrementInterval(Integer incrementInterval) {
        this.incrementInterval = incrementInterval;
    }
}
