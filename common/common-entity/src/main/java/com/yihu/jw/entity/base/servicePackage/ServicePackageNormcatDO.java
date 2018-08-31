package com.yihu.jw.entity.base.servicePackage;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 服务包类目表
 * @author yeshijie on 2018/8/29.
 */
@Entity
@Table(name = "base_service_package_normcat")
public class ServicePackageNormcatDO extends UuidIdentityEntity implements Serializable {

    private String saasId;
    private Long cid;//商品所属类目ID
    private Long parentCid;//父类目ID=0时，代表的是一级的类目
    private String name;//类目名称
    private Integer isParent;//该类目是否为父类目(即：该类目是否还有子类目),1为true,0为false
    private Long sortOrder;//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
    private String alias;//alias
    private String picUrl;//类型图片
    private String simpleName;//分类简称
    private Date createTime;//创建时间
    private Integer del;//状态。可选值:1(正常),0(删除)

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Column(name = "parent_cid")
    public Long getParentCid() {
        return parentCid;
    }

    public void setParentCid(Long parentCid) {
        this.parentCid = parentCid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_parent")
    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    @Column(name = "sort_order")
    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Column(name = "pic_url")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Column(name = "simple_name")
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
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
