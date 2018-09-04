package com.yihu.wlyy.statistics.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/16.
 */
public class WlyyJobConfigVO implements  Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String quotaId;//指标id
    private String jobName;//任务名称
    private String jobInfo;//任务描述
    private String jobType;//任务类型(1 累加  2平均值
    private String jobClass;//任务执行的class
    private String quartzCron;//quartz表达式
    private String status;//1 启动 0停止
    private String del;//是否删除 1正常 0删除
    private String sql; //mysql的查询语句
    private String sqlCount;//mysql的查询数量语句，用在多线程抽取数据用
    private String sqlDay;//按日统计
    private String sqlYear;//按年统计
    private String cacheKey;//缓存的key
    private Integer incrementInterval;//增量的时间间隔（1天，2周，3月）
    private String timeLevel;//1增量 2到达量 3生成到达量也生成增量

    public WlyyJobConfigVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
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

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "WlyyJobConfigVO{" +
                "id='" + id + '\'' +
                ", quotaId='" + quotaId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobInfo='" + jobInfo + '\'' +
                ", jobType='" + jobType + '\'' +
                ", jobClass='" + jobClass + '\'' +
                ", quartzCron='" + quartzCron + '\'' +
                ", status='" + status + '\'' +
                ", del='" + del + '\'' +
                '}';
    }


    public String getSqlYear() {
        return sqlYear;
    }

    public void setSqlYear(String sqlYear) {
        this.sqlYear = sqlYear;
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

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public Integer getIncrementInterval() {
        return incrementInterval;
    }

    public void setIncrementInterval(Integer incrementInterval) {
        this.incrementInterval = incrementInterval;
    }

    public String getTimeLevel() {
        return timeLevel;
    }

    public void setTimeLevel(String timeLevel) {
        this.timeLevel = timeLevel;
    }
}
