package com.yihu.jw.restmodel.base.message;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 消息类型字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年09月14日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseMessageTypeVO", description = "消息类型字典")
public class BaseMessageTypeVO extends IntegerIdentityVO{

    /**
	 * saas配置id，null标识公共字典
	 */
	@ApiModelProperty(value = "saas配置id，null标识公共字典", example = " ")
    private String saasId;

    /**
	 * 类型编码（体征预警消息、提醒缴费消息、提醒测量体征数据消息、续签提醒消息、筛查提醒消息 1.是家庭签约信息  2体征 101患者申请取消签约、102患者同意取消签约、103患者拒绝取消签约、104患者填写了血糖记录、105患者填写了血压记录、106患者填写了体重记录、107患者填写了腰围记录、108患者填写了运动记录、109患者填写了用药记录、110患者填写了饮食记录、111患者提交了问卷随访、112请求添加好友消息、113入群消息、114群解散消息、115踢出群消息、116新的网络咨询、117网络咨询追问、201医生拒绝签约、202医生同意签约、203医生申请取消签约、204医生同意取消签约、205医生拒绝取消签约、206新的问卷随访、207新的健康干预、208请求添加好友消息、209入群消息、210群解散消息、211踢出群消息、212聊天消息提醒、213群聊天消息、214医生回复了网络咨询、215请求添加为家人、216电话随访，217、上门随访）
	 */
	@ApiModelProperty(value = "类型编码（体征预警消息、提醒缴费消息、提醒测量体征数据消息、续签提醒消息、筛查提醒消息 1.是家庭签约信息  2体征 101患者申请取消签约、102患者同意取消签约、103患者拒绝取消签约、104患者填写了血糖记录、105患者填写了血压记录、106患者填写了体重记录、107患者填写了腰围记录、108患者填写了运动记录、109患者填写了用药记录、110患者填写了饮食记录、111患者提交了问卷随访、112请求添加好友消息、113入群消息、114群解散消息、115踢出群消息、116新的网络咨询、117网络咨询追问、201医生拒绝签约、202医生同意签约、203医生申请取消签约、204医生同意取消签约、205医生拒绝取消签约、206新的问卷随访、207新的健康干预、208请求添加好友消息、209入群消息、210群解散消息、211踢出群消息、212聊天消息提醒、213群聊天消息、214医生回复了网络咨询、215请求添加为家人、216电话随访，217、上门随访）", example = " ")
    private String code;

    /**
	 * 类型名称
	 */
	@ApiModelProperty(value = "类型名称", example = " ")
    private String name;


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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}