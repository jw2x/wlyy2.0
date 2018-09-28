package com.yihu.jw.entity.base.customize;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 用户取消订阅的模块或功能
 * @author progr1mmer
 * @date Created on 2018/8/14.
 */
@Entity
@Table(name = "base_user_hide_module_function")
public class UserHideModuleFunctionDO extends IntegerIdentityEntity {

    /**
     * 对象ID
     */
    private String objId;
    /**
     * 模块ID
     */
    private Integer moduleId;
    /**
     * 功能ID（该字段为空则直接隐藏上级模块）
     */
    private Integer functionId;

    @Column(name = "user_id", nullable = false, length = 50)
    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    @Column(name = "module_id", nullable = false)
    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "function_id")
    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }
}
