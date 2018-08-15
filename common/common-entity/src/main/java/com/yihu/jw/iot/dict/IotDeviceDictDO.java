package com.yihu.jw.iot.dict;


import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yeshijie on 2017/12/1.
 */
@Entity
@Table(name = "iot_device_dict")
public class IotDeviceDictDO extends UuidIdentityEntityWithOperation implements Serializable {


    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "device_type")
    private String deviceType;//设备种类

    @Column(name = "name")
    private String name;//设备种类名称

    @Column(name = "data_type")
    private String dataType;//测量数据种类

    @Column(name = "data_type_name")
    private String dataTypeName;//测量数据种类名称

    @Column(name = "del")
    private Integer del;//删除标志

    public IotDeviceDictDO() {
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
