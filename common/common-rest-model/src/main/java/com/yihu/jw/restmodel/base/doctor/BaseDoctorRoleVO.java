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
	 * 医生业务模块角色code
	 */
	@ApiModelProperty(value = "医生业务模块角色code", example = "")
    private String roleModuleCode;

    /**
	 * 医生业务模块角色名称
	 */
	@ApiModelProperty(value = "医生业务模块角色名称", example = "")
    private String name;

	/**
	 * 医生业务模块角色名称
	 */
	@ApiModelProperty(value = "医生业务模块角色说明", example = "")
    private String description;

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

    public String getRoleModuleCode() {
        return roleModuleCode;
    }
    public void setRoleModuleCode(String roleModuleCode) {
        this.roleModuleCode = roleModuleCode;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}