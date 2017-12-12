package com.yihu.jw.iot.device;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yeshijie on 2017/12/1.
 */
@Entity
@Table(name = "iot_device_purchase")
public class IotDevicePurchaseDO extends IdEntityWithOperation implements Serializable{

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "order_code")
    private String orderCode;//订单code

    @Column(name = "order_no")
    private String orderNo;//订单编号

    @Column(name = "supplier_code")
    private String supplierCode;//供应商code

    @Column(name = "supplier_name")
    private String supplierName;//供应商名称

    @Column(name = "device_name")
    private String deviceName;//采购设备名称

    @Column(name = "device_model")
    private String deviceModel;//采购设备型号

    @Column(name = "device_type")
    private String deviceType;//设备种类

    @Column(name = "manufacturer_code")
    private String manufacturerCode;//厂商code

    @Column(name = "manufacturer_name")
    private String manufacturerName;//厂商名称

    @Column(name = "purchase_num")
    private Long purchaseNum;//采购数量

    @Column(name = "quality_cycle")
    private String qualityCycle;//质检周期

    @Column(name = "associated_num")
    private Long associatedNum;//已关联设备数量

    @Column(name = "unassociated_num")
    private Long unassociatedNum;//未关联设备数量

    @Column(name = "del")
    private Integer del;//删除标志

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
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

    public String getQualityCycle() {
        return qualityCycle;
    }

    public void setQualityCycle(String qualityCycle) {
        this.qualityCycle = qualityCycle;
    }

    public Long getAssociatedNum() {
        return associatedNum;
    }

    public void setAssociatedNum(Long associatedNum) {
        this.associatedNum = associatedNum;
    }

    public Long getUnassociatedNum() {
        return unassociatedNum;
    }

    public void setUnassociatedNum(Long unassociatedNum) {
        this.unassociatedNum = unassociatedNum;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
