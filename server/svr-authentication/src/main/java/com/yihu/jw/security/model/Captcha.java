package com.yihu.jw.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Model - 验证码
 * Created by progr1mmer on 2018/9/3.
 */
public class Captcha implements Serializable {

    //验证码
    private String code;
    //过期时间（秒）
    private int expiresIn;
    //请求间隔（秒）
    private int interval;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
