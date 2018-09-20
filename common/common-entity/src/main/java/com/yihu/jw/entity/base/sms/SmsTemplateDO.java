package com.yihu.jw.entity.base.sms;

import com.yihu.jw.entity.UuidIdentityEntity;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 短信模板
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_sms_template")
public class SmsTemplateDO extends UuidIdentityEntityWithOperator {

    /**
     * 0 - 注册
     * 1 - 登陆
     * 2 - 重置密码
     */
    public enum Type {
        register,
        login,
        resetPassword
    }

    //应用ID
    private String clientId;
    //标签
    private Type type;
    //头部
    private String header;
    //内容
    private String content;

    @Column(name = "client_id", nullable = false)
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Column(name = "type", nullable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(name = "header", nullable = false)
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
