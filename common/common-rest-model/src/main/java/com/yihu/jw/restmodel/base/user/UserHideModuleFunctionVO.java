package com.yihu.jw.restmodel.base.user;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;


/**
 * @author progr1mmer
 * @date Created on 2018/9/11.
 */
@ApiModel(value = "UserHideModuleFunctionVO", description = "用户取消订阅的模块或者功能")
public class UserHideModuleFunctionVO extends IntegerIdentityVO {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", example = "402303ee65634dfs0234sf9a324a0023")
    private String userId;
    /**
     * 模块ID
     */
    @ApiModelProperty(value = "模块ID", example = "402303ee65634dfs0234sf9a324a0024")
    private Integer moduleId;
    /**
     * 功能ID（该字段为空则直接隐藏上级模块）
     */
    @ApiModelProperty(value = "功能ID", example = "402303ee65634dfs0234sf9a324a0025")
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
