package com.yihu.jw.base.wx;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Trick on 2018/8/13.
 */
@Entity
@Table(name = "wx_menu_json")
public class WxMenuJsonDO extends IdEntityWithOperation implements java.io.Serializable{

    private String wechatId;//微信Id
    private String content;//'微信模板内容',
    private Integer status;//-1删除 0 冻结 1可用',

    @Column(name = "wechat_id")
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
