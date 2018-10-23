package com.yihu.jw.restmodel.base.doctor;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 医生角色关联信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年10月19日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseDoctorRoleVO", description = "医生角色关联信息")
public class BaseDoctorRoleVO extends IntegerIdentityVO{

    /**
	 * 医生code
	 */
	@ApiModelProperty(value = "医生code", example = "456345tf45225654g")
    private String doctorCode;

    /**
	 * 医生角色code
	 */
	@ApiModelProperty(value = "医生角色id", example = "")
    private String roleCode;

    /**
	 * 作废标识，1正常，0作废
	 */
	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "")
    private String del;

    public String getDoctorCode() {
        return doctorCode;
    }
    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }


}