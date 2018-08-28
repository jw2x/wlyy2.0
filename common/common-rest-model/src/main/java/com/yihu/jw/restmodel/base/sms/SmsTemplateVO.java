package com.yihu.jw.restmodel.base.sms;

import com.yihu.jw.entity.base.sms.SmsTemplateDO;
import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * VO - 短信模板
 * Created by progr1mmer on 2018/8/14.
 */
@ApiModel(value = "SmsTemplateVO", description = "短信网关")
public class SmsTemplateVO extends UuidIdentityVO {

    //应用ID
    @ApiModelProperty(value = "应用ID", example = "EwC0iRSrcS")
    private String clientId;
    //标签
    @ApiModelProperty(value = "标签", example = "login")
    private SmsTemplateDO.Type type;
    //头部
    @ApiModelProperty(value = "头部", example = "【i健康综合管理平台】")
    private String header;
    //内容
    @ApiModelProperty(value = "内容", example = "您的登陆验证码为：2313")
    private String content;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public SmsTemplateDO.Type getType() {
        return type;
    }

    public void setType(SmsTemplateDO.Type type) {
        this.type = type;
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
