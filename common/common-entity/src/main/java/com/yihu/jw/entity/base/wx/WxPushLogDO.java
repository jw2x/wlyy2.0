package com.yihu.jw.entity.base.wx;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by Trick on 2018/8/21.
 */
public class WxPushLogDO extends UuidIdentityEntity {

    private String wechatId;//推送类型
    private String tempName;//模板名称
    private String scene;//场景值
    private String openid;//接收者openid
    private String receiver;//接收者code
    private String receiverName;//接收者姓名
    private String request;//请求入参
    private String response;//返回值
    private String status;//状态（0失败，1成功）
    private Date createTime;//创建时间

    @Column(name = "wechat_id")
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name = "temp_name")
    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    @Column(name = "scene")
    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Column(name = "receiver")
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Column(name = "receiver_name")
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Column(name = "request")
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Column(name = "response")
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
