package com.yihu.jw.entity.base.wx;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2018/10/9.
 */@Entity
@Table(name = "wx_graphic_scene")
public class WxGraphicSceneDO extends UuidIdentityEntity implements java.io.Serializable{

    private String wechatId;//所属微信
    private String scene;//场景值


    @Column(name = "wechat_id")
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name="scene")
    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
