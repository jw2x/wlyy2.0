package com.yihu.jw.entity.base.message;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 基于MQ的消息推送
 * Created by progr1mmer on 2018/8/30.
 */
@Entity
@Table(name = "base_mq_message")
public class MqMessageDO extends UuidIdentityEntityWithOperator {

    //主题
    private String topic;
    //名称
    private String name;
    //备注
    private String remark;
    //模板
    private String template;

    @Column(name = "topic", nullable = false)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "template")
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
