package com.yihu.jw.restmodel.base.servicePackage;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 服务包属性值表
 * @author yeshijie on 2018/8/29.
 */
@ApiModel(value = "ServicePackagePropvalueVO", description = "服务包属性值表")
public class ServicePackagePropvalueVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saasId")
    private String saasId;
    @ApiModelProperty(value = "类目ID")
    private Long cid;//类目ID',
    @ApiModelProperty(value = "属性 ID")
    private Long pid;//属性 ID',
    @ApiModelProperty(value = "属性名")
    private String propName;//属性名',
    @ApiModelProperty(value = "属性值ID")
    private Long vid;//属性值ID',
    @ApiModelProperty(value = "属性值")
    private String name;//属性值',
    @ApiModelProperty(value = "属性值别名")
    private String nameAlias;//属性值别名',
    @ApiModelProperty(value = "是否为父类目属性")
    private Integer isParent;//是否为父类目属性',
    @ApiModelProperty(value = "排列序号")
    private Long sortOrder;//排列序号。取值范围:大于零的整数',
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "状态。可选值:1(正常),0(删除)")
    private Integer del;//',

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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Long getVid() {
        return vid;
    }

    public void setVid(Long vid) {
        this.vid = vid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAlias() {
        return nameAlias;
    }

    public void setNameAlias(String nameAlias) {
        this.nameAlias = nameAlias;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
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
