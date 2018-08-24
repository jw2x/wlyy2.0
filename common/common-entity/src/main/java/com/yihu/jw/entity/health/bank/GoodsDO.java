package com.yihu.jw.entity.health.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wang zhinan on 2018/4/27.
 */
@Entity
@Table(name = "wlyy_health_bank_goods")
public class GoodsDO extends UuidIdentityEntityWithOperator implements Serializable{

    @Column(name = "saas_id")
    private String saasId;//saasid

    @Column(name = "name")
    private String name;//商品名称

    @Column(name = "img_url")
    private String imgUrl;//图片地址

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Column(name = "start_time")
    private Date startTime;//开始时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Column(name = "over_time")
    private Date overTime;//结束时间

    @Column(name = "status")
    private String status;//状态

    @Column(name = "require_integrate")
    private String requireIntegrate; //兑换产品所需积分

    @Column(name = "content")
    private String content;//商品内容

    @Column(name = "community")
    private String community;//社区字段

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequireIntegrate() {
        return requireIntegrate;
    }

    public void setRequireIntegrate(String requireIntegrate) {
        this.requireIntegrate = requireIntegrate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }
}
