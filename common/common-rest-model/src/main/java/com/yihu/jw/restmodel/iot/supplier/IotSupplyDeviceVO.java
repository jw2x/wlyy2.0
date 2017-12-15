package com.yihu.jw.restmodel.iot.supplier;

import java.io.Serializable;

/**
 * @author yeshijie on 2017/12/1.
 */
public class IotSupplyDeviceVO implements Serializable {

    private String saasId;
    private String name;//'设备名称
    private String deviceModel;//型号
    private String deviceType;//设备种类 1血压计、2血糖仪
    private Integer isComposite;//是否复合型
    private Integer isPlatform;//是否平台型
    private String deviceSn;//设备编码
    private String supplierType;//供应商类型
    private String supplierCode;//供应商code
    private String supplierName;//供应商名称
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
