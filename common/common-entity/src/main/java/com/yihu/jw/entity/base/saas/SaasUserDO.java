package com.yihu.jw.entity.base.saas;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 租户账号
 * @author yeshijie on 2018/10/16.
 */
@Entity
@Table(name = "base_saas_user")
public class SaasUserDO extends UuidIdentityEntity {

    private String saasId;//租户ID
    private String userId;//用户账号

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
