package com.yihu.jw.entity.base.doctor;

import com.yihu.jw.entity.IntegerIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 医生角色关联信息实体
 * 
 * @author litaohong on  2018年10月19日
 *
 */
@Entity
@Table(name = "base_doctor_role")
public class BaseDoctorRoleDO extends IntegerIdentityEntityWithOperator {

    /**
	 * saasid
	 */
	private String saasid;

	/**
	 * 医生code
	 */
	private String doctorCode;

    /**
	 * 角色名称
	 */
	private String name;

    /**
	 * 医生业务模块角色code
	 */
	private String roleModuleCode;

	/**
	 * 角色说明
	 */
	private String description;

    /**
	 * 作废标识，1正常，0作废
	 */
	private String del;

    @Column(name = "saasid")
    public String getSaasid() {
        return saasid;
    }

    public void setSaasid(String saasid) {
        this.saasid = saasid;
    }

    @Column(name = "doctor_code")
    public String getDoctorCode() {
        return doctorCode;
    }
    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "role_module_code")
    public String getRoleModuleCode() {
        return roleModuleCode;
    }
    public void setRoleModuleCode(String roleModuleCode) {
        this.roleModuleCode = roleModuleCode;
    }

	@Column(name = "del")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}