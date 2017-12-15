package com.yihu.jw.restmodel.iot.supplier;

import java.io.Serializable;

/**
 * @author yeshijie on 2017/12/1.
 */
public class IotSupplyDeviceDataTypeVO implements Serializable {

    private String saasId;
    private String supplierDeviceCode;//供应商设备code',
    private String deviceModel;//设备型号',
    private String deviceType;//设备种类',
    private String dataType;//测量数据种类',
    private String dataTypeName;//测量数据种类名称',
    private Integer del;//

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getSupplierDeviceCode() {
        return supplierDeviceCode;
    }

    public void setSupplierDeviceCode(String supplierDeviceCode) {
        this.supplierDeviceCode = supplierDeviceCode;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
