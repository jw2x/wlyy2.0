package com.yihu.jw.entity.base.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 消息表实体
 *
 * @author litaohong on  2018年09月14日
 */
@Entity
@Table(name = "base_message")
public class BaseMessageDO extends IntegerIdentityEntity {

    /**
     * saas配置id，消息里不可为空
     */
    private String saasId;

    /**
     * 消息标识
     */
    private String code;

    /**
     * 消息接收人（微信平台为患者标识，医生APP平台为医生标识）
     */
    private String receiver;

    /**
     * 接收人姓名
     */
    private String receiverName;

    /**
     * 消息发送人标识
     */
    private String sender;

    /**
     * 发送人姓名
     */
    private String senderName;

    /**
     * 发送者头像
     */
    private String senderPhoto;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息摘要
     */
    private String msgDigest;

    /**
     * 消息内容，存json
     */
    private String msgContent;

    /**
     * 消息类型（1.是家庭签约信息  2体征 101患者申请取消签约、102患者同意取消签约、103患者拒绝取消签约、104患者填写了血糖记录、105患者填写了血压记录、106患者填写了体重记录、107患者填写了腰围记录、108患者填写了运动记录、109患者填写了用药记录、110患者填写了饮食记录、111患者提交了问卷随访、112请求添加好友消息、113入群消息、114群解散消息、115踢出群消息、116新的网络咨询、117网络咨询追问、201医生拒绝签约、202医生同意签约、203医生申请取消签约、204医生同意取消签约、205医生拒绝取消签约、206新的问卷随访、207新的健康干预、208请求添加好友消息、209入群消息、210群解散消息、211踢出群消息、212聊天消息提醒、213群聊天消息、214医生回复了网络咨询、215请求添加为家人、216电话随访，217、上门随访）
     */
    private Integer msgType;

    private String msgTypeName;

    /**
     * 消息平台，1微信端/患者端，2医生APP端
     */
    private Integer platform;

    /**
     * 阅读状态，1 已读，0未读
     */
    private Integer readState;

    /**
     * 只读消息：1否，0是
     */
    private Integer readonly;

    /**
     * 是否删除，1正常，0作废
     */
    private String del;

    /**
     * im会话id
     */
    private String sessionId;

    /**
     * im会话名称
     */
    private String sessionName;

    /**
     * 消息发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    /**
     * 消息阅读时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date readTime;


    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Column(name = "sender")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Column(name = "sender_name")
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Column(name = "sender_photo")
    public String getSenderPhoto() {
        return senderPhoto;
    }

    public void setSenderPhoto(String senderPhoto) {
        this.senderPhoto = senderPhoto;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "msg_digest")
    public String getMsgDigest() {
        return msgDigest;
    }

    public void setMsgDigest(String msgDigest) {
        this.msgDigest = msgDigest;
    }

    @Column(name = "msg_content")
    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Column(name = "msg_type")
    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    @Column(name = "msg_type_name")
    public String getMsgTypeName() {
        return msgTypeName;
    }

    public void setMsgTypeName(String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }

    @Column(name = "platform")
    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    @Column(name = "read_state")
    public Integer getReadState() {
        return readState;
    }

    public void setReadState(Integer readState) {
        this.readState = readState;
    }

    @Column(name = "readonly")
    public Integer getReadonly() {
        return readonly;
    }

    public void setReadonly(Integer readonly) {
        this.readonly = readonly;
    }

    @Column(name = "del")
    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    @Column(name = "session_id")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Column(name = "session_name")
    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "read_time")
    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }


}