package com.yihu.jw.entity.base.saas;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - Saas所分配的模块功能
 * Created by progr1mmer on 2018/8/27.
 */
@Entity
@Table(name = "base_saas_module_function")
public class SaasModuleFunctionDO extends IntegerIdentityEntity {

    //Saas ID
    private String saasId;
    //模块ID
    private String moduleId;
    //功能ID
    private String functionId;

    @Column(name = "saas_id", nullable = false, length = 50)
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
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
