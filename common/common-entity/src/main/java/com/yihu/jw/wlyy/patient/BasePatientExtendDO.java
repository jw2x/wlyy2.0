package com.yihu.jw.wlyy.patient;// default package

import com.yihu.jw.IdEntityWithOperation;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BasePatientExtend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_patient_extend")
public class BasePatientExtendDO extends IdEntityWithOperation implements java.io.Serializable {

	// Fields

	private String id;
	private String patientId;
	private Date entryDate;
	private String workState;
	private Date workStateTime;
	private String jobNumber;
	private String companyId;
	private String companyName;
	private String companyEmail;
	private String remark;

	// Constructors

	/** default constructor */
	public BasePatientExtendDO() {
	}

	/** minimal constructor */
	public BasePatientExtendDO(String id, Date entryDate,
			Date workStateTime, Date createTime, Date updateTime) {
		this.id = id;
		this.entryDate = entryDate;
		this.workStateTime = workStateTime;
	}

	/** full constructor */
	public BasePatientExtendDO(String id, String patientId, Date entryDate,
			String workState, Date workStateTime, String jobNumber,
			String companyId, String companyName, String companyEmail,
			String remark, String createUser, String createUserName,
			Date createTime, String updateUser, String updateUserName,
			Date updateTime) {
		this.id = id;
		this.patientId = patientId;
		this.entryDate = entryDate;
		this.workState = workState;
		this.workStateTime = workStateTime;
		this.jobNumber = jobNumber;
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "patient_id", length = 100)
	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

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


}