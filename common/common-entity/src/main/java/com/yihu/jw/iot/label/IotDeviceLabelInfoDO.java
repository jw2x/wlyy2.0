package com.yihu.jw.iot.label;

import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 设备标签信息表
 * @author yeshijie on 2017/12/28.
 */
@Entity
@Table(name = "iot_device_label_info")
public class IotDeviceLabelInfoDO extends UuidIdentityEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "device_code")
    private String deviceCode;//设备code
    @Column(name = "device_name")
    private String deviceName;//设备名称
    @Column(name = "label_code")
    private String labelCode;//标签code
    @Column(name = "label_name")
    private String labelName;//标签名称
    @Column(name = "label_type")
    private String labelType;//标签类型

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }
}
