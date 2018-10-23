package com.yihu.jw.entity.base.role;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 业务模块角色实体
 * 
 * @author litaohong on  2018年10月23日
 *
 */
@Entity
@Table(name = "base_role_module")
public class BaseRoleModuleDO extends IntegerIdentityEntity {

    /**
	 * 角色标识
	 */
	private String code;

    /**
	 * 角色名称
	 */
	private String name;

    /**
	 * 业务模块id，多个用逗号分割
	 */
	private String moduleId;

    /**
	 * 状态，0失效，1有效
	 */
	private String del;


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

	@Column(name = "module_id")
    public String getModuleId() {
        return moduleId;
    }
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

	@Column(name = "del")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}