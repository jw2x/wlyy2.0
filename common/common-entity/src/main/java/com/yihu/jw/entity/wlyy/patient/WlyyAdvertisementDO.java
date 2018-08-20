package com.yihu.jw.entity.wlyy.patient;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@Entity
@Table(name = "wlyy_advertisement")
public class WlyyAdvertisementDO extends UuidIdentityEntityWithOperator implements Serializable {

    private static final long serialVersionUID = 1497635003375865515L;
    private String saasId;//0,为默认广告
    private String name;//名称
    private String picture;//展示的图片
    private String url;//链接
    private Integer sort;//排序( 数字越小排越前面)
    private Integer status;//状态 -1 删除 0 禁用 1可用
    private String remark;//备注

    
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

    
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
