package com.yihu.jw.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

/**
 * Model - 认证失败信息
 * Created by progr1mmer on 2018/8/29.
 */
public class Oauth2Envelop<T> {

    protected String message;
    protected Integer status;
    private T obj = (T)new HashMap<>();

    public Oauth2Envelop(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public Oauth2Envelop(String message, Integer status, T obj) {
        this.message = message;
        this.status = status;
        this.obj = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
