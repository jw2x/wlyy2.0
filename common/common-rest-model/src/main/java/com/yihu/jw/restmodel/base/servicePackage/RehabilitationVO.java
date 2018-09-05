package com.yihu.jw.restmodel.base.servicePackage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yeshijie on 2018/8/30.
 */
@ApiModel(value = "RehabilitationVO", description = "康复计划服务包")
public class RehabilitationVO implements Serializable {

    @ApiModelProperty(value = "服务包")
    private ServicePackageVO servicePackageVO;
    @ApiModelProperty(value = "服务包签约信息")
    private ServicePackageSignRecordVO signRecordVO;

    public ServicePackageVO getServicePackageVO() {
        return servicePackageVO;
    }

    public void setServicePackageVO(ServicePackageVO servicePackageVO) {
        this.servicePackageVO = servicePackageVO;
    }

    public ServicePackageSignRecordVO getSignRecordVO() {
        return signRecordVO;
    }

    public void setSignRecordVO(ServicePackageSignRecordVO signRecordVO) {
        this.signRecordVO = signRecordVO;
    }
}
