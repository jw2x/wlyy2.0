package com.yihu.jw.restmodel.base.servicePackage;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *
 * @author yeshijie on 2018/8/17.
 */
@ApiModel(value = "ServicePackageDetailsVO", description = "服务包明细表")
public class ServicePackageDetailsVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saas id", example = "EwC0iRSrcS")
    private String saasId;
    @ApiModelProperty(value = "服务包id", example = "EwC0iRSrcS")
    private String servicePackageId;//服务包id',
    @ApiModelProperty(value = "服务项目编码", example = "EwC0iRSrcS")
    private String code;//服务项目编码',
    @ApiModelProperty(value = "服务项目名称", example = "康复计划")
    private String name;//服务项目名称',
    @ApiModelProperty(value = "创建时间")
    private Date createTime;//创建时间',
    @ApiModelProperty(value = "执行时间")
    private Date executionTime;//执行时间',
    @ApiModelProperty(value = "执行类型", example = "1")
    private String executionType;//执行类型（1固定时间，2固定次数（不固定时间），3长期）',
    @ApiModelProperty(value = "最少执行次数", example = "1")
    private Integer exceutionNum;//最少执行次数',

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(String servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExecutionType() {
        return executionType;
    }

    public void setExecutionType(String executionType) {
        this.executionType = executionType;
    }

    public Integer getExceutionNum() {
        return exceutionNum;
    }

    public void setExceutionNum(Integer exceutionNum) {
        this.exceutionNum = exceutionNum;
    }

    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }
}
