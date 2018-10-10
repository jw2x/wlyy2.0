package com.yihu.jw.restmodel.base.wx;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/10/9.
 */
public class WxGraphicSceneVO {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "所属微信")
    private String wechatId;//所属微信
    @ApiModelProperty(value = "场景值")
    private String scene;//场景值

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
