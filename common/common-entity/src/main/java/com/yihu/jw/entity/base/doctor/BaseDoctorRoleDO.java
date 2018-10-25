package com.yihu.jw.entity.base.doctor;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 医生与业务模块角色关联信息实体
 * 
 * @author litaohong on  2018年10月25日
 *
 */
@Entity
@Table(name = "base_doctor_role")
public class BaseDoctorRoleDO extends IntegerIdentityEntity {

    /**
	 * 医生code
	 */
	private String doctorCode;

    /**
	 * 医生角色id
	 */
	private String roleCode;


	@Column(name = "doctor_code")
    public String getDoctorCode() {
        return doctorCode;
    }
    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

	@Column(name = "role_code")
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }



}