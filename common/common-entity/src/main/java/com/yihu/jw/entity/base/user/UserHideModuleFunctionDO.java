package com.yihu.jw.entity.base.user;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_user_hide_module_function")
public class UserHideModuleFunctionDO extends IntegerIdentityEntity {

    //用户ID
    private String userId;
    //模块ID
    private Integer moduleId;
    //功能ID（该字段为空则直接隐藏上级模块）
    private Integer functionId;

    @Column(name = "user_id", nullable = false, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
