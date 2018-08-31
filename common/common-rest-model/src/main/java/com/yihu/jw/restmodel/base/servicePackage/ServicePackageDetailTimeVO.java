package com.yihu.jw.restmodel.base.servicePackage;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 服务包明细执行时间表
 * @author yeshijie on 2018/8/17.
 */
@ApiModel(value = "ServicePackageDetailTimeVO", description = "服务包明细执行时间表")
public class ServicePackageDetailTimeVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saas id", example = "EwC0iRSrcS")
    private String saasId;
    @ApiModelProperty(value = "服务包明细id", example = "EwC0iRSrcS")
    private String detailId;//服务包明细id
    @ApiModelProperty(value = "执行时间")
    private Date excutionTime;//执行时间

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Date getExcutionTime() {
        return excutionTime;
    }

    public void setExcutionTime(Date excutionTime) {
        this.excutionTime = excutionTime;
    }
}
