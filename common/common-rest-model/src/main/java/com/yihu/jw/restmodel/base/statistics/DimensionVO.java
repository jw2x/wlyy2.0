package com.yihu.jw.restmodel.base.statistics;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 统计维度表
 * @author yeshijie on 2018/8/31.
 */
@ApiModel(value = "DimensionVO", description = "统计维度")
public class DimensionVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saasId")
    private String saasId;
    @ApiModelProperty(value = "业务代码")
    private String code;//
    @ApiModelProperty(value = "类型")
    private String type;//
    @ApiModelProperty(value = "指标名称")
    private String name;//
    @ApiModelProperty(value = "状态(1: 正常 0：不可以用 -1 已删除)")
    private Integer status;//
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "备注")
    private String remark;//

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
