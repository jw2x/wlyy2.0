package com.yihu.jw.entity.base.im;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * im个推配置表
 * @author yeshijie on 2018/8/29.
 */
@Entity
@Table(name = "base_im_getui_config")
public class ImGetuiConfigDO extends UuidIdentityEntity {

    private String saasId;//个推url
    private String host;
    private String appId;//个推appid
    private String appkey;//个推appkey
    private String mastersecret;//个推的秘钥

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getMastersecret() {
        return mastersecret;
    }

    public void setMastersecret(String mastersecret) {
        this.mastersecret = mastersecret;
    }
}
