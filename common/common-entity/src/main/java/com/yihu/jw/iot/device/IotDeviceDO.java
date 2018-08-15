package com.yihu.jw.iot.device;

import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 设备表
 * @author yeshijie on 2017/12/1.
 */
@Entity
@Table(name = "iot_device")
public class IotDeviceDO extends UuidIdentityEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "product_id")
    private String productId;//产品id

    @Column(name = "name")
    private String name;//设备名称

//    @Column(name = "device_model")
//    private String deviceModel;//设备型号

    @Column(name = "device_sn")
    private String deviceSn;//设备sn码

    @Column(name = "is_composite")
    private Integer isComposite;//是否复合型(1是，0否即设备为单一功能)

    @Column(name = "is_platform")
    private Integer isPlatform;//是否平台型(1是，0否)

//    @Column(name = "device_type")
//    private String deviceType;//设备种类

    @Column(name = "device_source")
    private String deviceSource;//设备来源(1采购订单关联,2居民绑定,3管理员新增)

    @Column(name = "supplier_id")
    private String supplierId;//供应商code

    @Column(name = "supplier_name")
    private String supplierName;//供应商名称

    @Column(name = "manufacturer_id")
    private String manufacturerId;//厂商code

    @Column(name = "manufacturer_name")
    private String manufacturerName;//厂商名称

    @Column(name = "hospital")
    private String hospital;//归属社区

    @Column(name = "hospital_name")
    private String hospitalName;//归属社区名称

    @Column(name = "order_id")
    private String orderId;//订单code

    @Column(name = "order_no")
    private String orderNo;//订单编号

    @Column(name = "status")
    private String status;//设备状态(正常、报废、检修)

    @Column(name = "next_quality_time")
    private Date nextQualityTime;//下次质检时间

    @Column(name = "purchase_id")
    private String purchaseId;//采购code

    @Column(name = "sim_no")
    private String simNo;//sim卡号

    @Column(name = "del")
    private Integer del;//删除标志(1有效，0删除)

    public enum DeviceStatus {
        normal("正常", "1"),
        scrap("报废", "2"),
        overhaul("检修", "3");
        private String name;
        private String value;

        DeviceStatus(String name, String value) {
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

    public enum DeviceSource {
        purchaese("采购订单关联", "1"),
        binding("居民绑定", "2"),
        create("管理员新增", "3");
        private String name;
        private String value;

        DeviceSource(String name, String value) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getDeviceModel() {
//        return deviceModel;
//    }
//
//    public void setDeviceModel(String deviceModel) {
//        this.deviceModel = deviceModel;
//    }

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

//    public String getDeviceType() {
//        return deviceType;
//    }
//
//    public void setDeviceType(String deviceType) {
//        this.deviceType = deviceType;
//    }

    public String getDeviceSource() {
        return deviceSource;
    }

    public void setDeviceSource(String deviceSource) {
        this.deviceSource = deviceSource;
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

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
