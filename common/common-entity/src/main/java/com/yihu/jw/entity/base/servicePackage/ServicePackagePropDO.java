package com.yihu.jw.entity.base.servicePackage;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 服务包属性表
 * @author yeshijie on 2018/8/29.
 */
@Entity
@Table(name = "base_service_package_prop")
public class ServicePackagePropDO extends UuidIdentityEntity implements Serializable {

    private String saasId;
    private Long pid;//属性 ID
    private Long parentPid;//上级属性ID
    private Long parentVid;//上级属性值ID
    private String name;//属性名
    private Integer isKeyProp;//是否关键属性。可选值:true(是),false(否)
    private Integer isSaleProp;//是否销售属性。可选值:true(是),false(否)
    private Integer isItemProp;//是否商品属性。可选值:true(是),false(否)
    private Integer must;//是否为必选属性。可选值:true(是),false(否)
    private Integer multi;//是否可以多选。可选值:true(是),false(否)
    private Long sortOrder;
    private Long cid;//类目ID
    private Date createTime;
    private Integer del;//状态。可选值:1(正常),0(删除)

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Column(name = "parent_pid")
    public Long getParentPid() {
        return parentPid;
    }

    public void setParentPid(Long parentPid) {
        this.parentPid = parentPid;
    }

    @Column(name = "parent_vid")
    public Long getParentVid() {
        return parentVid;
    }

    public void setParentVid(Long parentVid) {
        this.parentVid = parentVid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_key_prop")
    public Integer getIsKeyProp() {
        return isKeyProp;
    }

    public void setIsKeyProp(Integer isKeyProp) {
        this.isKeyProp = isKeyProp;
    }

    @Column(name = "is_sale_prop")
    public Integer getIsSaleProp() {
        return isSaleProp;
    }

    public void setIsSaleProp(Integer isSaleProp) {
        this.isSaleProp = isSaleProp;
    }

    @Column(name = "is_item_prop")
    public Integer getIsItemProp() {
        return isItemProp;
    }

    public void setIsItemProp(Integer isItemProp) {
        this.isItemProp = isItemProp;
    }

    public Integer getMust() {
        return must;
    }

    public void setMust(Integer must) {
        this.must = must;
    }

    public Integer getMulti() {
        return multi;
    }

    public void setMulti(Integer multi) {
        this.multi = multi;
    }

    @Column(name = "sort_order")
    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
