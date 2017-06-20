package com.yihu.jw.manage.model.wechat;

import com.yihu.jw.manage.model.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/21 0021.
 */
@Entity
@Table(name = "wx_graphic_message")
public class GraphicMessage extends IdEntity implements java.io.Serializable {

    private String code;//业务code
    private String title;//标题
    private String description;//描述
    private String url;//图文消息url值
    private String picUrl;//图片地址
    private String createUser;//创建人
    private String createUserName;//创建人名
    private Date createTime;//创建时间
    private String updateUser;//修改人
    private String updateUserName;//修改人名称
    private Date updateTime;//修改时间
    private String remark;//备注
    private Integer status;  //状态 -1删除 0 冻结 1可用


    public GraphicMessage() {
    }

    public GraphicMessage(String code, String title, String description, String url, String picUrl, String createUser, String createUserName, Date createTime, String updateUser, String updateUserName, Date updateTime, String remark, Integer status) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.url = url;
        this.picUrl = picUrl;
        this.createUser = createUser;
        this.createUserName = createUserName;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateUserName = updateUserName;
        this.updateTime = updateTime;
        this.remark = remark;
        this.status = status;
    }

    @Column(name = "code", length = 64)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Column(name = "create_user", length = 200)
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(name = "create_user_name", length = 200)
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", length = 0)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_user", length = 200)
    public String getUpdateUser() {
        return updateUser;
    }

    @Column(name = "remark", length = 1000)
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "update_user_name", length = 200)
    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", length = 0)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}
