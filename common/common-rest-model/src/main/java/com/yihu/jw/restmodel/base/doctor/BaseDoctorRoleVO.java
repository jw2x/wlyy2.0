package com.yihu.jw.restmodel.base.doctor;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 医生与业务模块角色关联信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年10月25日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseDoctorRoleVO", description = "医生与业务模块角色关联信息")
public class BaseDoctorRoleVO extends IntegerIdentityVO{

    /**
	 * 医生code
	 */
	@ApiModelProperty(value = "医生code", example = "模块1")
    private String doctorCode;

    /**
	 * 医生角色id
	 */
	@ApiModelProperty(value = "医生角色id", example = "模块1")
    private String roleCode;


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


}