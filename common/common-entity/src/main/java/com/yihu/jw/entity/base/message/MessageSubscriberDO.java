package com.yihu.jw.entity.base.message;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_message_subscriber")
public class MessageSubscriberDO extends IntegerIdentityEntity {

    private Integer messageId; //消息ID
    private String saasId; //saas id
    private String url; //推送地址
    private String remark; //备注

    @Column(name = "message_id")
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @Column(name = "saas_id", length = 50)
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
