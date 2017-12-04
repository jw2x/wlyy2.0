package com.yihu.jw.cache.model;

/**
 * Created by LiTaohong on 2017/12/04.
 */
public class LoginCacheModel {
    private String code;//用户code
    private String saasId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }
}
