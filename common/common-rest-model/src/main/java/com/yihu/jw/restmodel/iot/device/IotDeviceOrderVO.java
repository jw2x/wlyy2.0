package com.yihu.jw.restmodel.iot.device;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yeshijie on 2017/12/1.
 */
public class IotDeviceOrderVO implements Serializable{

    private String saasId;
    private String orderNo;//订单编号
    private String orderStatus;//订单状态
    private Date purchaseTime;//采购时间
    private String orderContractName;//订单合同名称
    private String orderContractUrl;//订单合同链接
    private String purchaseUnitCode;//采购单位编码
    private String purchaseUnitName;//采购单位名称
    private String purchaserName;//采购负责人
    private String purchaserPhone;//采购负责人联系方式
    private String supplierCode;//供应商code
    private String supplierName;//供应商名称
    private String supplierType;//供应商类型
    private String supplierLeader;//供应商负责人
    private String supplierLeaderPhone;//供应商负责人联系方式

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getOrderContractName() {
        return orderContractName;
    }

    public void setOrderContractName(String orderContractName) {
        this.orderContractName = orderContractName;
    }

    public String getOrderContractUrl() {
        return orderContractUrl;
    }

    public void setOrderContractUrl(String orderContractUrl) {
        this.orderContractUrl = orderContractUrl;
    }

    public String getPurchaseUnitCode() {
        return purchaseUnitCode;
    }

    public void setPurchaseUnitCode(String purchaseUnitCode) {
        this.purchaseUnitCode = purchaseUnitCode;
    }

    public String getPurchaseUnitName() {
        return purchaseUnitName;
    }

    public void setPurchaseUnitName(String purchaseUnitName) {
        this.purchaseUnitName = purchaseUnitName;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    public void setPurchaserPhone(String purchaserPhone) {
        this.purchaserPhone = purchaserPhone;
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

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierLeader() {
        return supplierLeader;
    }

    public void setSupplierLeader(String supplierLeader) {
        this.supplierLeader = supplierLeader;
    }

    public String getSupplierLeaderPhone() {
        return supplierLeaderPhone;
    }

    public void setSupplierLeaderPhone(String supplierLeaderPhone) {
        this.supplierLeaderPhone = supplierLeaderPhone;
    }
}
