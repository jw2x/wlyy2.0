package com.yihu.jw.entity.base.message;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 基于MQ的消息推送订阅者
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_mq_message_subscriber")
public class MqMessageSubscriberDO extends UuidIdentityEntity {

    private String saasId; //saas id
    private String topic; //主题
    private String url; //推送地址
    private String remark; //备注

    @Column(name = "saas_id", nullable = false, length = 50)
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "topic", nullable = false)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Column(name = "url", nullable = false)
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
