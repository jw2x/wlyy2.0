package com.yihu.jw.base.wx;


import com.yihu.jw.IdEntity;
import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/21 0021.
 */
@Entity
@Table(name = "wx_graphic_message")
public class WxGraphicMessageDO extends IdEntityWithOperation implements java.io.Serializable {

    private String code;
    private String title;//标题
    private String description;//描述
    private String url;//图文消息url值
    private String picUrl;//图片地址
    private String remark;//备注
    private Integer status;  //状态 -1删除 0 冻结 1可用

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


    @Column(name = "remark", length = 1000)
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "remark", length = 1000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "code", length = 50)
    public String getCode() {
        return code;
    }
    @Column(name = "code", length = 50)
    public void setCode(String code) {
        this.code = code;
    }
}
