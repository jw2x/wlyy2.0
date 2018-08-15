package com.yihu.jw.wlyy.patient;// default package

import com.yihu.jw.entity.UuidIdentityEntity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BasePatientWechat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_patient_wechat")
public class BasePatientWechatDO extends UuidIdentityEntity implements java.io.Serializable {

    // Fields

    private String id;
    private String saasId;
    private String wechatId;
    private String patientId;
    private String openid;
    private String unionid;
    private Date createTime;

    // Constructors

    /**
     * default constructor
     */
    public BasePatientWechatDO() {
    }

    /**
     * minimal constructor
     */
    public BasePatientWechatDO(String id, Date createTime) {
        this.id = id;
        this.createTime = createTime;
    }

    /**
     * full constructor
     */
    public BasePatientWechatDO(String id, String saasId, String wechatId,
                             String patientId, String openid, String unionid,
                             Date createTime) {
        this.id = id;
        this.saasId = saasId;
        this.wechatId = wechatId;
        this.patientId = patientId;
        this.openid = openid;
        this.unionid = unionid;
        this.createTime = createTime;
    }

    @Column(name = "saas_id", length = 64)
    public String getSaasId() {
        return this.saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "wechat_id", length = 10)
    public String getWechatId() {
        return this.wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name = "patient_id", length = 100)
    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Column(name = "openid", length = 50)
    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Column(name = "unionid", length = 50)
    public String getUnionid() {
        return this.unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Column(name = "create_time", nullable = false, length = 0)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}