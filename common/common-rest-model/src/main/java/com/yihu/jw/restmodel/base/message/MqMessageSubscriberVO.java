package com.yihu.jw.restmodel.base.message;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VO - 基于MQ的消息推送订阅者
 * Created by progr1mmer on 2018/8/14.
 */
@ApiModel(value = "MqMessageSubscriberVO", description = "基于MQ的消息推送订阅者")
public class MqMessageSubscriberVO extends UuidIdentityVO {

    @ApiModelProperty(value = "SAAS ID", example = "402803ee656498890165649ad2xaaba2")
    private String saasId;
    @ApiModelProperty(value = "主题", example = "dailyReport")
    private String topic;
    @ApiModelProperty(value = "推送地址", example = "http://www.example.com/api")
    private String url;
    @ApiModelProperty(value = "备注", example = "我是备注")
    private String remark;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
