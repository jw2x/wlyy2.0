package com.yihu.jw.entity.base.message;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 基于MQ的消息推送订阅者
 * @author progr1mmer
 * @date Created on 2018/8/14
 */
@Entity
@Table(name = "base_mq_message_subscriber")
public class MqMessageSubscriberDO extends UuidIdentityEntityWithOperator {

    /**
     * saas id
     */
    private String saasId;
    /**
     * 主题
     */
    private String topic;
    /**
     * 推送地址
     */
    private String url;
    /**
     * 备注
     */
    private String remark;

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
