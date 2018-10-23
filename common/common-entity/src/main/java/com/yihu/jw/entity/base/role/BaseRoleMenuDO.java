package com.yihu.jw.entity.base.role;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 角色菜单表实体
 * 
 * @author litaohong on  2018年10月23日
 *
 */
@Entity
@Table(name = "base_role_menu")
public class BaseRoleMenuDO extends IntegerIdentityEntity {

    /**
	 * 角色标识
	 */
	private String code;

    /**
	 * 角色名称
	 */
	private String name;

    /**
	 * 角色拥有的菜单id列表，逗号分隔，对应base_menu表
	 */
	private String menuId;

    /**
	 * 角色状态，0失效 1有效
	 */
	private String del;

    /**
	 * 角色说明
	 */
	private String description;


	@Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "menu_id")
    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
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