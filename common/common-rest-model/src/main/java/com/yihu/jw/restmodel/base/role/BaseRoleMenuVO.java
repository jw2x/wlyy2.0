package com.yihu.jw.restmodel.base.role;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 角色菜单表vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年10月23日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseRoleMenuVO", description = "角色菜单表")
public class BaseRoleMenuVO extends IntegerIdentityVO{

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
	 * 角色拥有的菜单id列表，逗号分隔，对应base_menu表
	 */
	@ApiModelProperty(value = "角色拥有的菜单id列表，逗号分隔，对应base_menu表", example = "模块1")
    private String menuId;

    /**
	 * 角色状态，0失效 1有效
	 */
	@ApiModelProperty(value = "角色状态，0失效 1有效", example = "模块1")
    private String del;

    /**
	 * 角色说明
	 */
	@ApiModelProperty(value = "角色说明", example = "模块1")
    private String description;


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

    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


}