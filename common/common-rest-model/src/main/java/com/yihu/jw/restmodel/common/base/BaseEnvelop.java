package com.yihu.jw.restmodel.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenweida on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "基础实体")
public class BaseEnvelop {

    @ApiModelProperty("信息")
    protected String message;
    @ApiModelProperty("状态（200成功，-1是失败）")
    protected Integer status;

    public BaseEnvelop() {
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static BaseEnvelop getSuccess(String message) {
        BaseEnvelop envelop = new BaseEnvelop();
        envelop.setMessage(message);
        envelop.setStatus(200);
        return envelop;
    }

    public static BaseEnvelop getError(String message, int errorCode) {
        BaseEnvelop envelop = new BaseEnvelop();
        envelop.setMessage(message);
        envelop.setStatus(errorCode);
        return envelop;
    }

    public static BaseEnvelop getError(String message) {
        BaseEnvelop envelop = new BaseEnvelop();
        envelop.setMessage(message);
        envelop.setStatus(-1);
        return envelop;
    }

}
