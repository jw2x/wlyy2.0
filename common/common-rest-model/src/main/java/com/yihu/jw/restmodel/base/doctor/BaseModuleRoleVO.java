package com.yihu.jw.restmodel.base.doctor;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 业务模块与业务模块角色关联信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年10月25日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseModuleRoleVO", description = "业务模块与业务模块角色关联信息")
public class BaseModuleRoleVO extends IntegerIdentityVO{

    /**
	 * 角色标识
	 */
	@ApiModelProperty(value = "角色标识", example = "模块1")
    private String roleCode;

    /**
	 * 业务模块id
	 */
	@ApiModelProperty(value = "业务模块id", example = "模块1")
    private String moduleId;


    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getModuleId() {
        return moduleId;
    }
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }


}