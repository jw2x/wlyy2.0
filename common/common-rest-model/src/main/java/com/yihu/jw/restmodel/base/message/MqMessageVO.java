package com.yihu.jw.restmodel.base.message;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * VO - 基于MQ的消息推送
 * Created by progr1mmer on 2018/9/3.
 */
@ApiModel(value = "MqMessageVO", description = "基于MQ的消息推送")
public class MqMessageVO extends UuidIdentityVO {

    //主题
    @ApiModelProperty(value = "主题", example = "dailyReport")
    private String topic;
    //名称
    @ApiModelProperty(value = "名称", example = "日常统计数据")
    private String name;
    //备注
    @ApiModelProperty(value = "备注", example = "我是备注")
    private String remark;
    //模板
    @ApiModelProperty(value = "模板")
    private String template;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
