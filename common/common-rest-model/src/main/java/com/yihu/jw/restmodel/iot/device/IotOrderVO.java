package com.yihu.jw.restmodel.iot.device;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;


/**
 * @author yeshijie on 2018/1/18.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "设备订单", description = "设备订单")
public class IotOrderVO implements Serializable{

    @ApiModelProperty("设备订单")
    private IotDeviceOrderVO iotDeviceOrderVO;
    @ApiModelProperty("采购清单")
    private List<IotOrderPurchaseVO> purchaseVOList;

    public IotDeviceOrderVO getIotDeviceOrderVO() {
        return iotDeviceOrderVO;
    }

    public void setIotDeviceOrderVO(IotDeviceOrderVO iotDeviceOrderVO) {
        this.iotDeviceOrderVO = iotDeviceOrderVO;
    }

    public List<IotOrderPurchaseVO> getPurchaseVOList() {
        return purchaseVOList;
    }

    public void setPurchaseVOList(List<IotOrderPurchaseVO> purchaseVOList) {
        this.purchaseVOList = purchaseVOList;
    }
}
