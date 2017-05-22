package com.yihu.jw.wx.model;

import com.yihu.jw.base.model.base.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/21 0021.
 */
@Entity
@Table(name = "wx_graphic_message")
public class WxGraphicMessage extends IdEntity implements java.io.Serializable {

    private String code;//业务code
    private String wechatCode;//关联的微信code 关联表 Wx_Wechat
    private String name;//名称
    private String value;//图文消息url值
    private String keyword;//关键字
    private String createUser;//创建人
    private String createUserName;//创建人名
    private Date createTime;//创建时间
    private String updateUser;//修改人
    private String updateUserName;//修改人名称
    private Date updateTime;//修改时间
    private String remark;
    private Integer status; //状态 -1 已删除 0可用

    public WxGraphicMessage(String code, String wechatCode, String name, String value, String keyword, String createUser, String createUserName, Date createTime, String updateUser, String updateUserName, Date updateTime, String remark, Integer status) {
        this.code = code;
        this.wechatCode = wechatCode;
        this.name = name;
        this.value = value;
        this.keyword = keyword;
        this.createUser = createUser;
        this.createUserName = createUserName;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateUserName = updateUserName;
        this.updateTime = updateTime;
        this.remark = remark;
        this.status = status;
    }

    public WxGraphicMessage() {
    }

    @Column(name = "code", length = 64)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "wechat_code", length = 200)
    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    @Column(name = "name", length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "value", length = 2000)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "keyword", length = 100)
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
