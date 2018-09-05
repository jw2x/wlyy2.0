package com.yihu.wlyy.entity.job;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 计划任务
 */
@Entity
@Table(name = "wlyy_job_config")
public class QuartzJobConfig implements java.io.Serializable {

	private String id;
	private String quotaId;//指标id
	private String jobName;//任务名称
	private String jobInfo;//任务描述
	private String jobType;//任务类型(0--单次执行  1--周期执行 2--监听任务)
	private String jobClass;//任务执行的class
	private String quartzCron;//quartz表达式
	private String status;//1 启动 0停止
	private String del;//是否删除 1正常 0删除
	private String sql;
	private String sqlCount;
	private String sqlDay;
	private String sqlYear;
	private String cacheKey;//缓存的key

	/** minimal constructor */
	public QuartzJobConfig() {

	}

	/** full constructor */
	public QuartzJobConfig(String jobName, String jobInfo, String jobType,
                           String jobClass, String quartzCron, String status) {
		this.jobName = jobName;
		this.jobInfo = jobInfo;
		this.jobType = jobType;
		this.jobClass = jobClass;
		this.quartzCron = quartzCron;
		this.status = status;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "job_name", length = 50)
	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Column(name = "job_info", length = 200)
	public String getJobInfo() {
		return this.jobInfo;
	}

	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}

	@Column(name = "job_type", length = 10)
	public String getJobType() {
		return this.jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	@Column(name = "job_class", length = 200)
	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	@Column(name = "quartz_cron", length = 200)
	public String getQuartzCron() {
		return this.quartzCron;
	}

	public void setQuartzCron(String quartzCron) {
		this.quartzCron = quartzCron;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "quota_id", length = 50)
	public String getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(String quotaId) {
		this.quotaId = quotaId;
	}

	@Column(name = "del", length = 1)
	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
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

}