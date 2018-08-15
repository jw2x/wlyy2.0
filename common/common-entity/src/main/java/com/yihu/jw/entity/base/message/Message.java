package com.yihu.jw.entity.base.message;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */@Entity
@Table(name = "base_message")
public class Message extends UuidIdentityEntity {

    private String topic; //消息主题
    private String template; //消息模板

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
