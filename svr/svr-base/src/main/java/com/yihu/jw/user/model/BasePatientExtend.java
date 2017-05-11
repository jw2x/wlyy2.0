package com.yihu.jw.user.model;// default package

import java.util.Date;
import javax.persistence.*;

/**
 * BasePatientExtend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_patient_extend")
public class BasePatientExtend implements java.io.Serializable {

	// Fields
	private Integer id;//'主键'
	private String code;//'业务code'
	private String patientCode;//'患者code关联 wlyy_patient'
	private Date entryDate;//'入职时间'
	private String workState;//'工作状态 1实习 2试用期 3转正 4兼职 5离职 6产假 7医疗期 8退休 9返聘'
	private Date workStateTime;//'状态开始时间'
	private String jobNumber;//'工号'
	private String companyId;//'企业id'
	private String companyName;//'企业名称'
	private String companyEmail;//'企业邮箱'
	private String remark;//'备注'
	private Date createTime;//'创建时间'
	private Date updateTime;//'修改时间'

	// Constructors

	/** default constructor */
	public BasePatientExtend() {
	}

	/** minimal constructor */
	public BasePatientExtend(Integer id, Date entryDate, Date workStateTime,
			Date createTime, Date updateTime) {
		this.id = id;
		this.entryDate = entryDate;
		this.workStateTime = workStateTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public BasePatientExtend(Integer id, String code, String patientCode,
			Date entryDate, String workState, Date workStateTime,
			String jobNumber, String companyId, String companyName,
			String companyEmail, String remark, Date createTime, Date updateTime) {
		this.id = id;
		this.code = code;
		this.patientCode = patientCode;
		this.entryDate = entryDate;
		this.workState = workState;
		this.workStateTime = workStateTime;
		this.jobNumber = jobNumber;
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "patient_code", length = 100)
	public String getPatientCode() {
		return this.patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entry_date", nullable = false, length = 0)
	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "work_state", length = 2)
	public String getWorkState() {
		return this.workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "work_state_time", nullable = false, length = 0)
	public Date getWorkStateTime() {
		return this.workStateTime;
	}

	public void setWorkStateTime(Date workStateTime) {
		this.workStateTime = workStateTime;
	}

	@Column(name = "job_number", length = 20)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "company_id", length = 100)
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "company_name", length = 30)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "company_email", length = 50)
	public String getCompanyEmail() {
		return this.companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	@Column(name = "remark", length = 2000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}