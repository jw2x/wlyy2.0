package com.yihu.jw.restmodel.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.web.status.EnvelopStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Rest Model - 基类
 * Created by Progr1mmer on 2018/8/24.
 * 快速集成 {@link com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint}
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "Envelop", description = "基础实体")
public class Envelop implements Serializable {

    @ApiModelProperty(value = "信息", example = "success")
    protected String message;
    @ApiModelProperty(value = "状态（200 - 成功)", example = "200")
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
