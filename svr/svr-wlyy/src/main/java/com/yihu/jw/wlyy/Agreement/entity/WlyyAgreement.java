package com.yihu.jw.wlyy.Agreement.entity;


import com.yihu.jw.wlyy.Agreement.entity.base.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Entity
@Table(name = "wlyy_agreement")
public class WlyyAgreement extends IdEntity implements Serializable{

    private static final long serialVersionUID = -4343130835307199266L;

    private String code;//业务code
    private String parentCode;//
    private String saasId;
    private String name;//套餐名称
    private BigDecimal price;//套餐价格
    private String posterPic;//海报图
    private String remark;//描述
    private String type;//类型
    private Timestamp createTime;
    private Timestamp updateTime;
    private String status;//状态
    private String createUser;

    public WlyyAgreement(String code, String parentCode, String saasId, String name, BigDecimal price, String posterPic, String remark, String type, Timestamp createTime, Timestamp updateTime, String status, String createUser) {
        this.code = code;
        this.parentCode = parentCode;
        this.saasId = saasId;
        this.name = name;
        this.price = price;
        this.posterPic = posterPic;
        this.remark = remark;
        this.type = type;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.createUser = createUser;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "parent_code")
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "poster_pic")
    public String getPosterPic() {
        return posterPic;
    }

    public void setPosterPic(String posterPic) {
        this.posterPic = posterPic;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "create_user")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
