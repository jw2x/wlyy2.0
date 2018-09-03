package com.yihu.jw.restmodel.base.im;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author yeshijie on 2018/8/29.
 */
@ApiModel(value = "ImGetuiConfigVO", description = "im个推配置表")
public class ImGetuiConfigVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saas id", example = "EwC0iRSrcS")
    private String saasId;
    @ApiModelProperty(value = "个推url", example = "EwC0iRSrcS")
    private String host;
    @ApiModelProperty(value = "个推appid", example = "EwC0iRSrcS")
    private String appId;//个推appid
    @ApiModelProperty(value = "个推appkey", example = "EwC0iRSrcS")
    private String appkey;//个推appkey
    @ApiModelProperty(value = "个推的秘钥", example = "EwC0iRSrcS")
    private String mastersecret;//

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getMastersecret() {
        return mastersecret;
    }

    public void setMastersecret(String mastersecret) {
        this.mastersecret = mastersecret;
    }
}
