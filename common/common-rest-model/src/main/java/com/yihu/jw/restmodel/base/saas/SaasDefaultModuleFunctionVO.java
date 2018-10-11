package com.yihu.jw.restmodel.base.saas;

import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VO - Saas默认模块功能
 * Created by progr1mmer on 2018/8/14.
 */
@ApiModel(value = "SaasDefaultModuleFunctionVO", description = "Saas默认模块功能")
public class SaasDefaultModuleFunctionVO extends IntegerIdentityVO {

    @ApiModelProperty(value = "Saas类型编码（从1开始自增）", example = "1")
    private Integer saasType;
    @ApiModelProperty(value = "模块ID", example = "402303ee656498890234sf9ad2wa00sa")
    private String moduleId;
    @ApiModelProperty(value = "功能ID", example = "402303ee656498890sd24s9ad2wa00sd")
    private String functionId;

    public Integer getSaasType() {
        return saasType;
    }

    public void setSaasType(Integer saasType) {
        this.saasType = saasType;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }
}
