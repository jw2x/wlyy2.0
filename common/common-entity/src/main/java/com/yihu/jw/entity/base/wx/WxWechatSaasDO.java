package com.yihu.jw.entity.base.wx;

import com.yihu.jw.entity.UuidIdentityEntity;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2018/10/8.
 */
@Entity
@Table(name = "wx_wechat_saas")
public class WxWechatSaasDO extends UuidIdentityEntity implements java.io.Serializable {

    private String wechatId;
    private String saasId;

    @Column(name="wechat_id")
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name="saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }
}
