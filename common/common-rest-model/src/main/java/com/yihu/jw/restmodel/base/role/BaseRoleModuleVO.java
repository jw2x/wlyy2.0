package com.yihu.jw.restmodel.base.role;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 业务模块角色vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年10月23日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseRoleModuleVO", description = "业务模块角色")
public class BaseRoleModuleVO extends IntegerIdentityVO{

    /**
	 * 角色标识
	 */
	@ApiModelProperty(value = "角色标识", example = "模块1")
    private String code;

    /**
	 * 角色名称
	 */
	@ApiModelProperty(value = "角色名称", example = "模块1")
    private String name;

    /**
	 * 业务模块id，多个用逗号分割
	 */
	@ApiModelProperty(value = "业务模块id，多个用逗号分割", example = "模块1")
    private String moduleId;

    /**
	 * 状态，0失效，1有效
	 */
	@ApiModelProperty(value = "状态，0失效，1有效", example = "模块1")
    private String del;


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getModuleId() {
        return moduleId;
    }
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }


}