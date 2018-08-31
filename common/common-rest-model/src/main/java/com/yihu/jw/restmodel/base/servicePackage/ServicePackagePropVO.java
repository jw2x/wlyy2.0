package com.yihu.jw.restmodel.base.servicePackage;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *
 * @author yeshijie on 2018/8/29.
 */
@ApiModel(value = "ServicePackagePropVO", description = "服务包属性表")
public class ServicePackagePropVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saasId", example = "xsaasdaqq")
    private String saasId;
    @ApiModelProperty(value = "属性 id", example = "123412")
    private Long pid;//属性 ID
    @ApiModelProperty(value = "上级属性ID", example = "134")
    private Long parentPid;//上级属性ID
    @ApiModelProperty(value = "上级属性值ID", example = "1324")
    private Long parentVid;//上级属性值ID
    @ApiModelProperty(value = "属性名", example = "爱迪生")
    private String name;//属性名
    @ApiModelProperty(value = "是否关键属性", example = "1")
    private Integer isKeyProp;//是否关键属性。可选值:true(是),false(否)
    @ApiModelProperty(value = "是否销售属性", example = "1")
    private Integer isSaleProp;//是否销售属性。可选值:true(是),false(否)
    @ApiModelProperty(value = "是否商品属性", example = "1")
    private Integer isItemProp;//是否商品属性。可选值:true(是),false(否)
    @ApiModelProperty(value = "是否为必选属性", example = "1")
    private Integer must;//是否为必选属性。可选值:true(是),false(否)
    @ApiModelProperty(value = "是否可以多选", example = "1")
    private Integer multi;//是否可以多选。可选值:true(是),false(否)
    @ApiModelProperty(value = "排序", example = "1")
    private Long sortOrder;
    @ApiModelProperty(value = "类目ID", example = "123")
    private Long cid;//类目ID
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "状态。可选值:1(正常),0(删除)", example = "1")
    private Integer del;//状态。可选值:1(正常),0(删除)

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

    public Long getParentPid() {
        return parentPid;
    }

    public void setParentPid(Long parentPid) {
        this.parentPid = parentPid;
    }

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

    public Integer getIsKeyProp() {
        return isKeyProp;
    }

    public void setIsKeyProp(Integer isKeyProp) {
        this.isKeyProp = isKeyProp;
    }

    public Integer getIsSaleProp() {
        return isSaleProp;
    }

    public void setIsSaleProp(Integer isSaleProp) {
        this.isSaleProp = isSaleProp;
    }

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
