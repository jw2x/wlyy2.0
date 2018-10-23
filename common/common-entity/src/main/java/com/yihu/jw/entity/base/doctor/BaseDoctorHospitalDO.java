package com.yihu.jw.entity.base.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 医生执业信息实体
 * 
 * @author Administrator on  2018年09月05日
 *
 */
@Entity
@Table(name = "base_doctor_hospital")
public class BaseDoctorHospitalDO extends IntegerIdentityEntity {

    /**
	 * 医院标识
	 */
	private String hospCode;

    /**
	 * 医院名称
	 */
	private String hospName;

    /**
     * 医生标识
     */
	private String doctorCode;

    /**
	 * 职称代码
	 */
	private String jobTitleCode;

    /**
	 * 职称名称
	 */
	private String jobTitleName;

    /**
     * 医生所在机构部门标识
     */
    private String deptCode;

    /**
     * 职务代码
     */
    private String doctorDutyCode;

    /**
     * 职务名称
     */
    private String doctorDutyName;

    /**
	 * 作废标识，1正常，0作废
	 */
	private String del;

	public BaseDoctorHospitalDO(){}

	public BaseDoctorHospitalDO(String hospCode,String hospName,String doctorDutyCode,String doctorDutyName){
	    this.hospCode = hospCode;
	    this.hospName = hospName;
	    this.doctorDutyCode = doctorDutyCode;
	    this.doctorDutyName = doctorDutyName;
    }

	@Column(name = "hosp_code")
    public String getHospCode() {
        return hospCode;
    }
    public void setHospCode(String hospCode) {
        this.hospCode = hospCode;
    }

	@Column(name = "hosp_name")
    public String getHospName() {
        return hospName;
    }
    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    @Column(name = "doctor_code")
    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

	@Column(name = "job_title_code")
    public String getJobTitleCode() {
        return jobTitleCode;
    }
    public void setJobTitleCode(String jobTitleCode) {
        this.jobTitleCode = jobTitleCode;
    }

	@Column(name = "job_title_name")
    public String getJobTitleName() {
        return jobTitleName;
    }
    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

	@Column(name = "del")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDoctorDutyCode() {
        return doctorDutyCode;
    }

    public void setDoctorDutyCode(String doctorDutyCode) {
        this.doctorDutyCode = doctorDutyCode;
    }

    public String getDoctorDutyName() {
        return doctorDutyName;
    }

    public void setDoctorDutyName(String doctorDutyName) {
        this.doctorDutyName = doctorDutyName;
    }
}