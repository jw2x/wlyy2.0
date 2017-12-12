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
@Table(name = "iot_supply_device_data_type")
public class IotSupplyDeviceDataTypeDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "supplier_device_code")
    private String supplierDeviceCode;//供应商设备code',

    @Column(name = "device_model")
    private String deviceModel;//设备型号',

    @Column(name = "device_type")
    private String deviceType;//设备种类',

    @Column(name = "data_type")
    private String dataType;//测量数据种类',

    @Column(name = "data_type_name")
    private String dataTypeName;//测量数据种类名称',

    @Column(name = "del")
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
