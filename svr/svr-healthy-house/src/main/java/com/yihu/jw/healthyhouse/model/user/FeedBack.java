package com.yihu.jw.healthyhouse.model.user;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 意见反馈
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
@Entity
@Table(name = "feedback")
public class FeedBack extends UuidIdentityEntityWithOperator {
    //反馈类型，1建议、2投诉、3报修、4其他
    @Column(name = "feed_type", nullable = false)
    private String  feedType;
    //提出意见的用户联系电话
    @Column(name = "user_telephone", nullable = false)
    private String  userTelephone;
    //意见内容
    @Column(name = "content", nullable = false)
    private String  content;
    //反馈的处理状态：0未阅，1待处理，2已处理
    @Column(name = "flag", nullable = false)
    private Integer  flag;
    //回复内容
    @Column(name = "replyContent", nullable = false)
    private String  replyContent;
    //是否有附件，0无，1有
    @Column(name = "enclosureFlag", nullable = false)
    private String  enclosureFlag;
    //设施编号
    @Column(name = "facilitieCode", nullable = false)
    private String  facilitieCode;
    //设施经度
    @Column(name = "facilitieLongitude", nullable = false)
    private double  facilitieLongitude;
    //设施维度
    @Column(name = "facilitieLatitude", nullable = false)
    private double  facilitieLatitude;
    //上传附件地址(多张图片地址，用逗号隔开)
    @Column(name = "pig_path", nullable = false)
    private String  pigPath;

    //反馈类型，1建议、2投诉、3报修、4其他
    private String  feedTypeName;
    //反馈的处理状态：0未阅，1待处理，2已处理
    private String  flagValue;
    //是否有附件，0无，1有
    private String  enclosureFlagValue;

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getEnclosureFlag() {
        return enclosureFlag;
    }

    public void setEnclosureFlag(String enclosureFlag) {
        this.enclosureFlag = enclosureFlag;
    }

    public String getFacilitieCode() {
        return facilitieCode;
    }

    public void setFacilitieCode(String facilitieCode) {
        this.facilitieCode = facilitieCode;
    }

    public double getFacilitieLongitude() {
        return facilitieLongitude;
    }

    public void setFacilitieLongitude(double facilitieLongitude) {
        this.facilitieLongitude = facilitieLongitude;
    }

    public double getFacilitieLatitude() {
        return facilitieLatitude;
    }

    public void setFacilitieLatitude(double facilitieLatitude) {
        this.facilitieLatitude = facilitieLatitude;
    }

    public String getPigPath() {
        return pigPath;
    }

    public void setPigPath(String pigPath) {
        this.pigPath = pigPath;
    }
    @Transient
    public String getFeedTypeName() {
        return feedTypeName;
    }

    public void setFeedTypeName(String feedTypeName) {
        this.feedTypeName = feedTypeName;
    }
    @Transient
    public String getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(String flagValue) {
        this.flagValue = flagValue;
    }
    @Transient
    public String getEnclosureFlagValue() {
        return enclosureFlagValue;
    }

    public void setEnclosureFlagValue(String enclosureFlagValue) {
        this.enclosureFlagValue = enclosureFlagValue;
    }
}
