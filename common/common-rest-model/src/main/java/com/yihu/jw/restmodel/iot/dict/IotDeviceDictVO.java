package com.yihu.jw.restmodel.iot.dict;


import java.io.Serializable;

/**
 * @author yeshijie on 2017/12/1.
 */
public class IotDeviceDictVO implements Serializable {

    private String saasId;
    private String deviceType;//设备种类
    private String name;//设备种类名称
    private String dataType;//测量数据种类
    private String dataTypeName;//测量数据种类名称
    private Integer del;//删除标志


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
