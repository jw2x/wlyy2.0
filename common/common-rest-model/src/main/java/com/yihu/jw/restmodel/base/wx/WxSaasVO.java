package com.yihu.jw.restmodel.base.wx;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Trick on 2018/9/26.
 */
public class WxSaasVO {
    @ApiModelProperty(value = "saasid")
    private String saasid;
    @ApiModelProperty(value = "saas名称")
    private String saasName;

    public String getSaasid() {
        return saasid;
    }

    public void setSaasid(String saasid) {
        this.saasid = saasid;
    }

    public String getSaasName() {
        return saasName;
    }

    public void setSaasName(String saasName) {
        this.saasName = saasName;
    }
}
