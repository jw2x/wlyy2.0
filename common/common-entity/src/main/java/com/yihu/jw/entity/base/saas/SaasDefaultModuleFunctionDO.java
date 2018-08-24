package com.yihu.jw.entity.base.saas;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - Saas初始化的时候分配给Saas管理员角色的模块功能
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_saas_default_module_function")
public class SaasDefaultModuleFunctionDO extends IntegerIdentityEntity {

    private SaasDO.Type type;
    private String moduleId;
    private String functionId;

    @Column(name = "type", nullable = false)
    public SaasDO.Type getType() {
        return type;
    }

    public void setType(SaasDO.Type type) {
        this.type = type;
    }

    @Column(name = "module_id", nullable = false, length = 50)
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "function_id", nullable = false, length = 50)
    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }
}
