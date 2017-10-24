package com.yihu.jw.business.wx.model;

import com.yihu.jw.business.base.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信 菜单表
 */
@Entity
@Table(name = "wx_menu")
public class WxMenu extends IdEntity implements java.io.Serializable {

    private String wechatCode;//关联的微信code 关联表 Wx_Wechat
    @Transient
    private String wechatName;
    private String supMenucode;//父菜单id 如果是一级菜单 此字段为空
    private String type;//菜单类型
    private String name;//菜单名称
    private String menuKey;//click等点击类型必须
    private Integer sort;//菜单排序 父菜单排序 不包含子菜单那
    private String url;//url
    private String mediaId;//点用新增永久素材接口返回的合法media_id
    private String appid;//小程序的appid
    private String pagepath;//小程序的页面程序
    private String remark;//备注
    private Integer status; //状态 -1删除 0 冻结 1可用

    @Transient
    private String state;                //children长度为0时    state  “open”表示是子节点，“closed”表示为父节点；
                                         // children长度>0时,    state   “open,closed”表示是节点的打开关闭
    @Transient
    private List<WxMenu> children = new ArrayList<>();



    /**
     * default constructor
     */
    public WxMenu() {
    }

    public List<WxMenu> getChildren() {
        return children;
    }

    public void setChildren(List<WxMenu> children) {
        this.children = children;
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

    @Column(name = "remark", length = 1000)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }
}