package com.yihu.jw.restmodel.base.wx;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/10/9.
 */
public class WxReplySceneVO {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "微信id(唯一)")
    private String wechatId;//微信id(唯一)
    @ApiModelProperty(value = "原始ID（唯一）")
    private String appOriginId;//原始ID（唯一）
    @ApiModelProperty(value = "微信消息类型：text，event，image，voice，video，shortvideo，location，link")
    private String msgType;//微信消息类型：text，event，image，voice，video，shortvideo，location，link
    @ApiModelProperty(value = "微信事件类型：SCAN，LOCATION，CLICK，subscribe，unsubscribe")
    private String event;//微信事件类型：SCAN，LOCATION，CLICK，subscribe，unsubscribe
    @ApiModelProperty(value = "图文消息/自定义消息，分组场景，微信的eventKey")
    private String scene;//图文消息/自定义消息，分组场景，微信的eventKey
    @ApiModelProperty(value = "居民回复内容，消息字段")
    private String content;//居民回复内容，消息字段
    @ApiModelProperty(value = "状态(-1删除 0 冻结 1可用")
    private Integer status;//状态(-1删除 0 冻结 1可用

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getAppOriginId() {
        return appOriginId;
    }

    public void setAppOriginId(String appOriginId) {
        this.appOriginId = appOriginId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
