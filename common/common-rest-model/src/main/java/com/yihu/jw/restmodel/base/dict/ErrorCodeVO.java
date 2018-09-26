package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 错误码
 * @author yeshijie on 2018/9/26.
 */
@ApiModel(value = "ErrorCodeVO", description = "错误码")
public class ErrorCodeVO extends UuidIdentityEntityWithOperator {

    @ApiModelProperty(value = "模块", example = "common")
    private String model;
    @ApiModelProperty(value = "错误码", example = "-1")
    private String errorCode;
    @ApiModelProperty(value = "提示内容", example = "模块")
    private String errorMsg;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
