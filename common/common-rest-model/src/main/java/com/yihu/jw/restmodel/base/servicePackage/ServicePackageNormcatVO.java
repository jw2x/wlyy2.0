package com.yihu.jw.restmodel.base.servicePackage;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 服务包类目表
 * @author yeshijie on 2018/8/29.
 */
@ApiModel(value = "ServicePackageNormcatVO", description = "服务包类目表")
public class ServicePackageNormcatVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saas id", example = "EwC0iRSrcS")
    private String saasId;
    @ApiModelProperty(value = "商品所属类目ID", example = "465")
    private Long cid;//商品所属类目ID
    @ApiModelProperty(value = "父类目ID", example = "0")
    private Long parentCid;//父类目ID=0时，代表的是一级的类目
    @ApiModelProperty(value = "类目名称", example = "服务")
    private String name;//类目名称
    @ApiModelProperty(value = "别名", example = "福")
    private String alias;//alias
    @ApiModelProperty(value = "类型图片")
    private String picUrl;//类型图片
    @ApiModelProperty(value = "分类简称")
    private String simpleName;//分类简称
    @ApiModelProperty(value = "创建时间")
    private Date createTime;//创建时间
    @ApiModelProperty(value = "状态。可选值:1(正常),0(删除)", example = "1")
    private Integer del;//状态。可选值:1(正常),0(删除)

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
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
