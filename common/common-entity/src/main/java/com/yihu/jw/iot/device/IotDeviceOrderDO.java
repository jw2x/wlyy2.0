package com.yihu.jw.iot.device;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 设备订单表
 * @author yeshijie on 2017/12/1.
 */
@Entity
@Table(name = "iot_device_order")
public class IotDeviceOrderDO extends IdEntityWithOperation implements Serializable{

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "order_no")
    private String orderNo;//订单编号

    @Column(name = "order_status")
    private String orderStatus;//订单状态(1新增、2已采购、3已入库)

    @Column(name = "purchase_time")
    private Date purchaseTime;//采购时间

    @Column(name = "order_contract_name")
    private String orderContractName;//订单合同名称

    @Column(name = "order_contract_url")
    private String orderContractUrl;//订单合同链接

    @Column(name = "purchase_unit_code")
    private String purchaseUnitCode;//采购单位编码

    @Column(name = "purchase_unit_name")
    private String purchaseUnitName;//采购单位名称

    @Column(name = "purchaser_name")
    private String purchaserName;//采购负责人

    @Column(name = "purchaser_phone")
    private String purchaserPhone;//采购负责人联系方式

    @Column(name = "supplier_id")
    private String supplierId;//供应商code

    @Column(name = "supplier_name")
    private String supplierName;//供应商名称

    @Column(name = "supplier_leader")
    private String supplierLeader;//供应商负责人

    @Column(name = "supplier_leader_phone")
    private String supplierLeaderPhone;//供应商负责人联系方式

    @Column(name = "instruction")
    private String instruction;//订单说明

    @Column(name = "ymd")
    private String ymd;//年月日

    @Column(name = "del")
    private Integer del;//删除标志

    public enum DeviceOrderStatus {
        create("新增", "1"),
        purchased("已采购", "2"),
        storaged("已入库", "3");
        private String name;
        private String value;

        DeviceOrderStatus(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }
}
