package com.yihu.jw.restmodel.base.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 消息表vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年09月14日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseMessageVO", description = "消息表")
public class BaseMessageVO extends IntegerIdentityVO{

    /**
	 * saas配置id，消息里不可为空
	 */
	@ApiModelProperty(value = "saas配置id，消息里不可为空", example = "jsdg499j0k0k95b23qqazfdh534gfv423f89h0a")
    private String saasId;

    /**
	 * 消息标识
	 */
	@ApiModelProperty(value = "消息标识", example = "")
    private String code;

    /**
	 * 消息接收人（微信平台为患者标识，医生APP平台为医生标识）
	 */
	@ApiModelProperty(value = "消息接收人（微信平台为患者标识，医生APP平台为医生标识）", example = "")
    private String receiver;

    /**
	 * 接收人姓名
	 */
	@ApiModelProperty(value = "接收人姓名", example = "")
    private String receiverName;

    /**
	 * 消息发送人标识
	 */
	@ApiModelProperty(value = "消息发送人标识", example = "")
    private String sender;

    /**
	 * 发送人姓名
	 */
	@ApiModelProperty(value = "发送人姓名", example = "")
    private String senderName;

    /**
	 * 发送者头像
	 */
	@ApiModelProperty(value = "发送者头像", example = "")
    private String senderPhoto;

    /**
	 * 消息标题
	 */
	@ApiModelProperty(value = "消息标题", example = "")
    private String title;

    /**
	 * 消息摘要
	 */
	@ApiModelProperty(value = "消息摘要", example = "")
    private String msgDigest;

    /**
	 * 消息内容，存json
	 */
	@ApiModelProperty(value = "消息内容，存json", example = "")
    private String msgContent;

    /**
	 * 消息类型（1.是家庭签约信息  2体征 101患者申请取消签约、102患者同意取消签约、103患者拒绝取消签约、104患者填写了血糖记录、105患者填写了血压记录、106患者填写了体重记录、107患者填写了腰围记录、108患者填写了运动记录、109患者填写了用药记录、110患者填写了饮食记录、111患者提交了问卷随访、112请求添加好友消息、113入群消息、114群解散消息、115踢出群消息、116新的网络咨询、117网络咨询追问、201医生拒绝签约、202医生同意签约、203医生申请取消签约、204医生同意取消签约、205医生拒绝取消签约、206新的问卷随访、207新的健康干预、208请求添加好友消息、209入群消息、210群解散消息、211踢出群消息、212聊天消息提醒、213群聊天消息、214医生回复了网络咨询、215请求添加为家人、216电话随访，217、上门随访）
	 */
	@ApiModelProperty(value = "消息类型（1.是家庭签约信息  2体征 101患者申请取消签约、102患者同意取消签约、103患者拒绝取消签约、104患者填写了血糖记录、105患者填写了血压记录、106患者填写了体重记录、107患者填写了腰围记录、108患者填写了运动记录、109患者填写了用药记录、110患者填写了饮食记录、111患者提交了问卷随访、112请求添加好友消息、113入群消息、114群解散消息、115踢出群消息、116新的网络咨询、117网络咨询追问、201医生拒绝签约、202医生同意签约、203医生申请取消签约、204医生同意取消签约、205医生拒绝取消签约、206新的问卷随访、207新的健康干预、208请求添加好友消息、209入群消息、210群解散消息、211踢出群消息、212聊天消息提醒、213群聊天消息、214医生回复了网络咨询、215请求添加为家人、216电话随访，217、上门随访）", example = " ")
    private Integer msgType;

	@ApiModelProperty(value = "消息类型名称",example = "")
    private String msgTypeName;

    /**
	 * 消息平台，1微信端/患者端，2医生APP端
	 */
	@ApiModelProperty(value = "消息平台，1微信端/患者端，2医生APP端", example = " ")
    private Integer platform;

    /**
	 * 阅读状态，1 已读，0未读
	 */
	@ApiModelProperty(value = "阅读状态，1 已读，0未读", example = " ")
    private Integer readState;

    /**
	 * 只读消息：1否，0是
	 */
	@ApiModelProperty(value = "只读消息：1否，0是", example = " ")
    private Integer readonly;

    /**
	 * 是否删除，1正常，0作废
	 */
	@ApiModelProperty(value = "是否删除，1正常，0作废", example = " ")
    private String del;

    /**
	 * im会话id
	 */
	@ApiModelProperty(value = "im会话id", example = " ")
    private String sessionId;

    /**
	 * im会话名称
	 */
	@ApiModelProperty(value = "im会话名称", example = " ")
    private String sessionName;

    /**
	 * 消息发送时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "消息发送时间", example = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
	 * 消息阅读时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "消息阅读时间", example = "yyyy-MM-dd HH:mm:ss")
    private Date readTime;


    public String getSaasId() {
        return saasId;
    }
    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderName() {
        return senderName;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhoto() {
        return senderPhoto;
    }
    public void setSenderPhoto(String senderPhoto) {
        this.senderPhoto = senderPhoto;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgDigest() {
        return msgDigest;
    }
    public void setMsgDigest(String msgDigest) {
        this.msgDigest = msgDigest;
    }

    public String getMsgContent() {
        return msgContent;
    }
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getMsgType() {
        return msgType;
    }
    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }

    public void setMsgTypeName(String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }

    public Integer getPlatform() {
        return platform;
    }
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getReadState() {
        return readState;
    }
    public void setReadState(Integer readState) {
        this.readState = readState;
    }

    public Integer getReadonly() {
        return readonly;
    }
    public void setReadonly(Integer readonly) {
        this.readonly = readonly;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReadTime() {
        return readTime;
    }
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }


}