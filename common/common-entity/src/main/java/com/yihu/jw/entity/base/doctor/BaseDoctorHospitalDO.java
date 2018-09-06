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
	 * 医生角色标识
	 */
	private String roleCode;

    /**
	 * 医院角色名称
	 */
	private String roleName;

    /**
	 * 职称代码
	 */
	private String jobTitleCode;

    /**
	 * 职称名称
	 */
	private String jobTitleName;

    /**
	 * 作废标识，1正常，0作废
	 */
	private String del;

    /**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;


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

	@Column(name = "role_code")
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

	@Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

	@Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}