package com.yihu.jw.entity.iot.device;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 采购清单表
 * @author yeshijie on 2017/12/1.
 */
@Entity
@Table(name = "iot_order_purchase")
public class IotOrderPurchaseDO extends UuidIdentityEntityWithOperator implements Serializable{

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "order_id")
    private String orderId;//订单code

    @Column(name = "order_no")
    private String orderNo;//订单编号

    @Column(name = "supplier_id")
    private String supplierId;//供应商code

    @Column(name = "supplier_name")
    private String supplierName;//供应商名称

    @Column(name = "device_name")
    private String deviceName;//采购设备名称

    @Column(name = "product_id")
    private String productId;//产品id

//    @Column(name = "device_model")
//    private String deviceModel;//采购设备型号
//
//    @Column(name = "device_type")
//    private String deviceType;//设备种类

    @Column(name = "manufacturer_id")
    private String manufacturerId;//厂商code

    @Column(name = "manufacturer_name")
    private String manufacturerName;//厂商名称

    @Column(name = "purchase_num")
    private Long purchaseNum;//采购数量

    @Column(name = "next_quality_time")
    private Date nextQualityTime;//下次质检时间

    @Column(name = "quality_status")
    private String qualityStatus;//质检状态

    @Column(name = "quality_leader")
    private String qualityLeader;//质检负责人

    @Column(name = "maintenance_unit_id")
    private String maintenanceUnitId;//维护单位Id

    @Column(name = "maintenance_unit_name")
    private String maintenanceUnitName;//维护单位名称

    @Column(name = "del")
    private Integer del;//删除标志

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

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

//    public String getDeviceModel() {
//        return deviceModel;
//    }
//
//    public void setDeviceModel(String deviceModel) {
//        this.deviceModel = deviceModel;
//    }
//
//    public String getDeviceType() {
//        return deviceType;
//    }
//
//    public void setDeviceType(String deviceType) {
//        this.deviceType = deviceType;
//    }

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

    public Date getNextQualityTime() {
        return nextQualityTime;
    }

    public void setNextQualityTime(Date nextQualityTime) {
        this.nextQualityTime = nextQualityTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(String qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public String getQualityLeader() {
        return qualityLeader;
    }

    public void setQualityLeader(String qualityLeader) {
        this.qualityLeader = qualityLeader;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
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
}
