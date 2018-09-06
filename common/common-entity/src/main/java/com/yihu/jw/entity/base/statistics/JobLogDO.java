package com.yihu.jw.entity.base.statistics;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * job执行日志表
 * @author yeshijie on 2018/9/3.
 */
@Entity
@Table(name = "base_quota")
public class JobLogDO extends UuidIdentityEntity implements Serializable {


    private String saasId;//
    private Date jobStartTime;//任务开始执行时间
    private Date jobEndTime;//任务结束时间
    private String jobId;//任务id
    private String jobContent;//任务执行情况
    private String jobType;// 1成功 0失败
    private String jobName;//任务名称

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "job_start_time")
    public Date getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(Date jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    @Column(name = "job_end_time")
    public Date getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(Date jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    @Column(name = "job_id")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Column(name = "job_content")
    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    @Column(name = "job_type")
    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
