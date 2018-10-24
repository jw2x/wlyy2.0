package com.yihu.jw.entity.base.doctor;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


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
	private String orgCode;

    /**
	 * 医院名称
	 */
	private String orgName;

    /**
     * 医生标识
     */
	private String doctorCode;

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

	public BaseDoctorHospitalDO(String orgCode, String orgName, String doctorDutyCode, String doctorDutyName){
	    this.orgCode = orgCode;
	    this.orgName = orgName;
	    this.doctorDutyCode = doctorDutyCode;
	    this.doctorDutyName = doctorDutyName;
    }

	@Column(name = "org_code")
    public String getOrgCode() {
        return orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

	@Column(name = "org_name")
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Column(name = "doctor_code")
    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
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