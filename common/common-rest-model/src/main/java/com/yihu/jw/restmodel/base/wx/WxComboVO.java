package com.yihu.jw.restmodel.base.wx;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/10/9.
 */
public class WxComboVO {

    @ApiModelProperty(value = "微信id")
    private String id;
    @ApiModelProperty(value = "微信公众号")
    private String name;
    @ApiModelProperty(value = "原始id")
    private String appOriginId;
    @ApiModelProperty(value="公众号类型")
    private String publicType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppOriginId() {
        return appOriginId;
    }

    public void setAppOriginId(String appOriginId) {
        this.appOriginId = appOriginId;
    }

    public String getPublicType() {
        return publicType;
    }

    public void setPublicType(String publicType) {
        this.publicType = publicType;
    }
}
