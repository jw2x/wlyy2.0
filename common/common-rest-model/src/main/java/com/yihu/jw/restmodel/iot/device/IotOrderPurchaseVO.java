package com.yihu.jw.restmodel.iot.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 采购清单表
 * @author yeshijie on 2017/12/1.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "采购清单", description = "采购清单")
public class IotOrderPurchaseVO extends BaseVO implements Serializable{

    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("供应商id")
    private String supplierId;
    @ApiModelProperty("供应商名称")
    private String supplierName;
    @ApiModelProperty("采购设备名称")
    private String deviceName;
    @ApiModelProperty("产品id")
    private String productId;
    @ApiModelProperty("厂商id")
    private String manufacturerId;
    @ApiModelProperty("厂商名称")
    private String manufacturerName;
    @ApiModelProperty("采购数量")
    private Long purchaseNum;
    @ApiModelProperty("下次质检时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private String nextQualityTime;
    @ApiModelProperty("质检状态")
    private String qualityStatus;
    @ApiModelProperty("质检状态")
    private String qualityStatusName;
    @ApiModelProperty("质检负责人")
    private String qualityLeader;
    @ApiModelProperty("维护单位Id")
    private String maintenanceUnitId;
    @ApiModelProperty("维护单位名称")
    private String maintenanceUnitName;
    @ApiModelProperty("已关联数量")
    private Long associatedNum;
    @ApiModelProperty("未关联数量")
    private Long unAssociatedNum;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Long getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Long purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getNextQualityTime() {
        return nextQualityTime;
    }

    public void setNextQualityTime(String nextQualityTime) {
        this.nextQualityTime = nextQualityTime;
    }

    public String getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(String qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public String getMaintenanceUnitId() {
        return maintenanceUnitId;
    }

    public void setMaintenanceUnitId(String maintenanceUnitId) {
        this.maintenanceUnitId = maintenanceUnitId;
    }

    public String getMaintenanceUnitName() {
        return maintenanceUnitName;
    }

    public void setMaintenanceUnitName(String maintenanceUnitName) {
        this.maintenanceUnitName = maintenanceUnitName;
    }

    public Long getAssociatedNum() {
        return associatedNum;
    }

    public void setAssociatedNum(Long associatedNum) {
        this.associatedNum = associatedNum;
    }

    public Long getUnAssociatedNum() {
        return unAssociatedNum;
    }

    public void setUnAssociatedNum(Long unAssociatedNum) {
        this.unAssociatedNum = unAssociatedNum;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQualityStatusName() {
        return qualityStatusName;
    }

    public void setQualityStatusName(String qualityStatusName) {
        this.qualityStatusName = qualityStatusName;
    }

    public String getQualityLeader() {
        return qualityLeader;
    }

    public void setQualityLeader(String qualityLeader) {
        this.qualityLeader = qualityLeader;
    }
}
