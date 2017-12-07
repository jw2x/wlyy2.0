package com.yihu.jw.iot.supplier;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yeshijie on 2017/12/1.
 */
@Entity
@Table(name = "iot_supply_device")
public class IotSupplyDeviceDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "name")
    private String name;//'设备名称

    @Column(name = "device_model")
    private String deviceModel;//型号

    @Column(name = "devie_type")
    private String deviceType;//设备种类

    @Column(name = "is_composite")
    private Integer isComposite;//是否复合型

    @Column(name = "is_platform")
    private Integer isPlatform;//是否平台型

    @Column(name = "device_sn")
    private String deviceSn;//设备编码

    @Column(name = "supplier_type")
    private String supplierType;//供应商类型

    @Column(name = "supplier_code")
    private String supplierCode;//供应商code

    @Column(name = "supplier_name")
    private String supplierName;//供应商名称

    @Column(name = "del")
    private Integer del;//删除标志(1有效，0删除)

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

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
