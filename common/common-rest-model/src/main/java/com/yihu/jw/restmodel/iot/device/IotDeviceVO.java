package com.yihu.jw.restmodel.iot.device;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yeshijie on 2017/12/1.
 */
public class IotDeviceVO implements Serializable {

    private String saasId;
    private String code;
    private String name;//设备名称
    private String deviceModel;//设备型号
    private String deviceSn;//设备sn码
    private Integer isComposite;//是否复合型(1是，0否即设备为单一功能)
    private Integer isPlatform;//是否平台型(1是，0否)
    private String deviceType;//设备种类
    private String deviceSource;//设备来源(1采购订单关联,2居民绑定,3管理员新增)
    private String supplierCode;//供应商code
    private String supplierName;//供应商名称
    private String manufacturerCode;//厂商code
    private String manufacturerName;//厂商名称
    private String orderCode;//订单code
    private String orderNo;//订单编号
    private String status;//设备状态(正常、报废、检修)
    private Date nextQualityTime;//下次质检时间
    private String purchaseCode;//采购code
    private String simNo;//sim卡号
    private Integer del;//删除标志(1有效，0删除)

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Integer getIsComposite() {
        return isComposite;
    }

    public void setIsComposite(Integer isComposite) {
        this.isComposite = isComposite;
    }

    public Integer getIsPlatform() {
        return isPlatform;
    }

    public void setIsPlatform(Integer isPlatform) {
        this.isPlatform = isPlatform;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceSource() {
        return deviceSource;
    }

    public void setDeviceSource(String deviceSource) {
        this.deviceSource = deviceSource;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getNextQualityTime() {
        return nextQualityTime;
    }

    public void setNextQualityTime(Date nextQualityTime) {
        this.nextQualityTime = nextQualityTime;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getSimNo() {
        return simNo;
    }

    public void setSimNo(String simNo) {
        this.simNo = simNo;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
