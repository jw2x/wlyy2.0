package com.yihu.jw.restmodel.base.wx;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2018/10/10.
 */
public class WxTemplateConfigVO {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "微信id")
    private String wechatId;//微信id
    @ApiModelProperty(value = "微信模板id")
    private String templateId;//微信模板id
    @ApiModelProperty(value = "自定义模板名称")
    private String templateName;//自定义模板名称
    @ApiModelProperty(value = "使用场景值")
    private String scene;//使用场景值
    @ApiModelProperty(value = "使用场景描述")
    private String sceneDescription;//使用场景描述
    @ApiModelProperty(value = "头部")
    private String first;//头部,
    @ApiModelProperty(value = "跳转链接")
    private String url;//跳转链接
    @ApiModelProperty(value = "备注")
    private String remark;//备注
    @ApiModelProperty(value = "项目")
    private String keyword1;//项目
    @ApiModelProperty(value = "项目")
    private String keyword2;//项目
    @ApiModelProperty(value = "项目")
    private String keyword3;//项目
    @ApiModelProperty(value = "项目")
    private String keyword4;//项目
    @ApiModelProperty(value = "项目")
    private String keyword5;//项目
    @ApiModelProperty(value = "项目")
    private String keyword6;//项目
    @ApiModelProperty(value = "项目")
    private String keyword7;//项目
    @ApiModelProperty(value = "跳转小程序的appid")
    private String appid;//跳转小程序的appid
    @ApiModelProperty(value = "跳转小程序路径")
    private String pagepath;//跳转小程序路径

    @ApiModelProperty(value = "状态 -1删除 0 冻结 1可用")
    private Integer status;  //状态 -1删除 0 冻结 1可用
    @ApiModelProperty(value = "创建人")
    private String createUser;//创建人
    @ApiModelProperty(value = "创建人名")
    private String createUserName;//创建人名
    @ApiModelProperty(value = "创建时间")
    private Date createTime;//创建时间
    @ApiModelProperty(value = "修改人")
    private String updateUser;//修改人
    @ApiModelProperty(value = "修改人名称")
    private String updateUserName;//修改人名称
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;//修改时间

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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getSceneDescription() {
        return sceneDescription;
    }

    public void setSceneDescription(String sceneDescription) {
        this.sceneDescription = sceneDescription;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    public String getKeyword6() {
        return keyword6;
    }

    public void setKeyword6(String keyword6) {
        this.keyword6 = keyword6;
    }

    public String getKeyword7() {
        return keyword7;
    }

    public void setKeyword7(String keyword7) {
        this.keyword7 = keyword7;
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

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
