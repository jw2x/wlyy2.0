package com.yihu.jw.entity.base.saas;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - Saas默认模块功能
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_saas_default_module_function")
public class SaasDefaultModuleFunctionDO extends IntegerIdentityEntity {

    //Saas类型
    private Integer saasType;
    //模块ID
    private String moduleId;
    //功能ID
    private String functionId;

    @Column(name = "saas_type", nullable = false)
    public Integer getSaasType() {
        return saasType;
    }

    public void setSaasType(Integer saasType) {
        this.saasType = saasType;
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
