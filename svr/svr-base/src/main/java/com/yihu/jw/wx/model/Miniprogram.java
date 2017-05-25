package com.yihu.jw.wx.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public class Miniprogram implements Serializable{

    private static final long serialVersionUID = -7963058957081033262L;

    private String appid;
    private String pagepath;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
