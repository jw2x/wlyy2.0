package com.yihu.jw.restmodel.wx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class MWxMenu {
    private Long id;//主键id

    private String code;//业务code
    private String wechatCode;//关联的微信code 关联表 Wx_Wechat
    private String wechatName;//关联的微信名 关联表 Wx_Wechat
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
    private String updateUserName;//更新人
    private Date updateTime;//更新时间
    private Date createTime;//创建时间
    private String createUser;//创建人
    private String createUserName;//创建人
    private String remark;//备注
    private Integer status; //状态 -1删除 0 冻结 1可用
    private List<MWxMenu> children = new ArrayList<>();
    private String state;

    public List<MWxMenu> getChildren() {
        return children;
    }

    public void setChildren(List<MWxMenu> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    public String getSupMenucode() {
        return supMenucode;
    }

    public void setSupMenucode(String supMenucode) {
        this.supMenucode = supMenucode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

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

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
