package com.yihu.jw.entity.base.statistics;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 统计job配置表
 * @author yeshijie on 2018/8/31.
 */
@Entity
@Table(name = "base_job_config")
public class JobConfigDO extends UuidIdentityEntity implements Serializable {

    private String saasId;
    private String quotaId;//指标id
    private String jobName;//任务名称
    private String jobInfo;//任务描述
    private String jobType;//任务类型(0--单次执行  1--周期执行 2--监听任务)
    private String jobClass;//任务执行的class
    private String sql;//统计sql语句
    private String sqlCount;//统计总数语句
    private String sqlDay;//天数条件
    private String sqlYear;//年份条件
    private String cacheKey;//缓存的key
    private String quartzCron;//quartz表达式
    private String status;//1 启动 0停止
    private String quotaCode;//指标code
    private Integer del;//1: 正常 0： 删除
    private String extractType;//抽取类型   1或者为空：数据库 2ES
    private String timeLevel;//1增量 2到达量 3生成到达量也生成增量
    private Integer incrementInterval;//增量时间间隔1天,2周,3月

    private String startTime;
    private String endTime;

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "quota_id")
    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
    }

    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Column(name = "job_info")
    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    @Column(name = "job_type")
    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Column(name = "job_class")
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

    @Column(name = "sql_count")
    public String getSqlCount() {
        return sqlCount;
    }

    public void setSqlCount(String sqlCount) {
        this.sqlCount = sqlCount;
    }

    @Column(name = "sql_day")
    public String getSqlDay() {
        return sqlDay;
    }

    public void setSqlDay(String sqlDay) {
        this.sqlDay = sqlDay;
    }

    @Column(name = "sql_year")
    public String getSqlYear() {
        return sqlYear;
    }

    public void setSqlYear(String sqlYear) {
        this.sqlYear = sqlYear;
    }

    @Column(name = "cache_key")
    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    @Column(name = "quartz_cron")
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

    @Column(name = "quota_code")
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

    @Column(name = "extract_type")
    public String getExtractType() {
        return extractType;
    }

    public void setExtractType(String extractType) {
        this.extractType = extractType;
    }

    @Column(name = "time_level")
    public String getTimeLevel() {
        return timeLevel;
    }

    public void setTimeLevel(String timeLevel) {
        this.timeLevel = timeLevel;
    }

    @Column(name = "increment_interval")
    public Integer getIncrementInterval() {
        return incrementInterval;
    }

    public void setIncrementInterval(Integer incrementInterval) {
        this.incrementInterval = incrementInterval;
    }

    @Transient
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Transient
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
