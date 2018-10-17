package com.yihu.jw.entity.base.wx;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/21 0021.
 */
@Entity
@Table(name = "wx_graphic_message")
public class WxGraphicMessageDO extends UuidIdentityEntityWithOperator implements java.io.Serializable {

    private String wechatId;//微信id
    private String title;//标题
    private String description;//描述
    private String url;//图文消息url值
    private String picUrl;//图片地址
    private Integer status;  //状态 -1删除 0 冻结 1可用

    @Column(name = "wechat_id", length = 50)
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name = "title", length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "url", length = 2000)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "pic_url", length = 2000)
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Column(name = "status", length = 2000)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
