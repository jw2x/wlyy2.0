package com.yihu.jw.entity.base.sms;

import com.yihu.jw.UuidIdentityEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_sms_template")
public class SmsTemplateDO extends UuidIdentityEntity {

    public enum Label {
        verificationCode, //验证码
        notification, //服务通知
        dailyPush //日常推送
    }

    private String clientId; //应用ID
    private String label; //标签
    private String header; //头部
    private String content; //内容

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
