package com.yihu.jw.entity.base.doctor;

import com.yihu.jw.entity.IntegerIdentityEntity;
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
public class BaseDoctorRoleDO extends IntegerIdentityEntity {

    /**
	 * 医生code
	 */
	private String doctorCode;

    /**
	 * 医生角色code
	 */
	private String roleCode;

    /**
	 * 作废标识，1正常，0作废
	 */
	private String del;

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

	@Column(name = "del")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }
}