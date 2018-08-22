package com.yihu.jw.entity.base.wx;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Trick on 2018/8/21.
 */
@Entity
@Table(name = "wx_template_config")
public class WxTemplateConfigDO extends UuidIdentityEntityWithOperator implements java.io.Serializable{

    private String wechatId;//微信id
    private String templateId;//微信模板id
    private String templateName;//自定义模板名称
    private String scene;//使用场景值
    private String sceneDescription;//使用场景描述
    private String first;//头部,
    private String url;//跳转链接
    private String remark;//备注
    private String keyword1;//项目
    private String keyword2;//项目
    private String keyword3;//项目
    private String keyword4;//项目
    private String keyword5;//项目
    private String keyword6;//项目
    private String keyword7;//项目
    private String appid;//跳转小程序的appid
    private String pagepath;//跳转小程序路径

    @Column(name = "wechat_id")
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name = "template_id")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Column(name = "template_name")
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Column(name = "scene")
    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Column(name = "scene_description")
    public String getSceneDescription() {
        return sceneDescription;
    }

    public void setSceneDescription(String sceneDescription) {
        this.sceneDescription = sceneDescription;
    }

    @Column(name = "first")
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "keyword1")
    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    @Column(name = "keyword2")
    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    @Column(name = "keyword3")
    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    @Column(name = "keyword4")
    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    @Column(name = "keyword5")
    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    @Column(name = "keyword6")
    public String getKeyword6() {
        return keyword6;
    }

    public void setKeyword6(String keyword6) {
        this.keyword6 = keyword6;
    }

    @Column(name = "keyword7")
    public String getKeyword7() {
        return keyword7;
    }

    public void setKeyword7(String keyword7) {
        this.keyword7 = keyword7;
    }


    @Column(name = "appid")
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }


    @Column(name = "pagepath")
    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
