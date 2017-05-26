package com.yihu.jw.wx.model;// default package

import com.yihu.jw.base.model.base.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 微信 菜单表
 */
@Entity
@Table(name = "wx_menu")
public class WxMenu extends IdEntity implements java.io.Serializable {


    private String code;//业务code
    private String wechatCode;//关联的微信code 关联表 Wx_Wechat
    private String supMenucode;//父菜单id 如果是一级菜单 此字段为空
    private String type;//菜单类型
    private String name;//菜单名称
    private String menuKey;//click等点击类型必须
    private Integer sort;//菜单排序 父菜单排序 不包含子菜单那
    private String url;//url
    private String mediaId;//点用新增永久素材接口返回的合法media_id
    private String appid;//小程序的appid
    private String pagepath;//小程序的页面程序
    private String updateUser;//更新人
    private Date updateTime;//更新时间
    private Date createTime;//创建时间
    private String createUser;//创建人
    private String remark;//备注
    private Integer status; //状态 -1删除 0 冻结 1可用



    /**
     * default constructor
     */
    public WxMenu() {
    }

    public WxMenu(String code, String wechatCode, String supMenucode, String type, String name, String menuKey, Integer sort, String url, String mediaId, String appid, String pagepath, String updateUser, Date updateTime, Date createTime, String createUser, String remark, Integer status) {
        this.code = code;
        this.wechatCode = wechatCode;
        this.supMenucode = supMenucode;
        this.type = type;
        this.name = name;
        this.menuKey = menuKey;
        this.sort = sort;
        this.url = url;
        this.mediaId = mediaId;
        this.appid = appid;
        this.pagepath = pagepath;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.createUser = createUser;
        this.remark = remark;
        this.status = status;
    }

    @Column(name="menu_key")

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getMediaId() {
        return mediaId;
    }

    @Column(name = "media_id", length = 200)
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }
    // Constructors

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "wechat_code", length = 200)
    public String getWechatCode() {
        return this.wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    @Column(name = "sup_menucode", length = 200)
    public String getSupMenucode() {
        return this.supMenucode;
    }

    public void setSupMenucode(String supMenucode) {
        this.supMenucode = supMenucode;
    }

    @Column(name = "type", length = 20)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "url", length = 1000)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "update_user", length = 50)
    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", length = 0)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", length = 0)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "create_user", length = 50)
    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(name = "remark", length = 1000)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}