package com.yihu.jw.base.wx;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Trick on 2018/8/15.
 */
@Entity
@Table(name = "wx_reply_scene")
public class WxReplySceneDO extends IdEntityWithOperation implements java.io.Serializable{

    private String wechatId;//微信id(唯一)
    private String appOriginId;//原始ID（唯一）
    private String msgType;//微信消息类型：text，event，image，voice，video，shortvideo，location，link
    private String event;//微信事件类型：SCAN，LOCATION，CLICK，subscribe，unsubscribe
    private String scene;//图文消息/自定义消息，分组场景
    private String content;//居民回复内容，消息字段
    private String status;//状态(-1删除 0 冻结 1可用

    @Column(name = "wechat_id")
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name = "app_origin_id")
    public String getAppOriginId() {
        return appOriginId;
    }

    public void setAppOriginId(String appOriginId) {
        this.appOriginId = appOriginId;
    }

    @Column(name = "msg_type")
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Column(name = "event")
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Column(name = "scene")
    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
