package com.yihu.jw.restmodel.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.web.status.EnvelopStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by chenweida on 2018/1/16.
 * 快速集成 {@link com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint}
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "Envelop", description = "基础实体")
public class Envelop implements Serializable {

    @ApiModelProperty("信息")
    protected String message;
    @ApiModelProperty("状态（200成功，-1是失败）")
    protected Integer status = EnvelopStatus.success.code;

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

    public static Envelop getSuccess(String message) {
        Envelop envelop = new Envelop();
        envelop.setMessage(message);
        envelop.setStatus(200);
        return envelop;
    }

    public static Envelop getError(String message, int errorCode) {
        Envelop envelop = new Envelop();
        envelop.setMessage(message);
        envelop.setStatus(errorCode);
        return envelop;
    }

    public static Envelop getError(String message) {
        Envelop envelop = new Envelop();
        envelop.setMessage(message);
        envelop.setStatus(-1);
        return envelop;
    }

}
