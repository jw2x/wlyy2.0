package com.yihu.jw.iot.device;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.searchbox.annotations.JestId;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;


/**
 * Created by chenweida on 2018/2/12.
 */

public class LocationDataDO {
    @JestId
    private String id;

    private String idCard; //设备绑定身份证

    private String deviceSn;//设备SnID

    private String categoryCode;//设备类型标识

    @GeoPointField
    private GeoPoint location;//经纬度


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX")
    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ssXX")
    private Date deviceTime;//设备绑定时间

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX")
    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ssXX")
    private Date createTime;  // 创建时间（ES:必填）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public Date getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Date deviceTime) {
        this.deviceTime = deviceTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public void setLocation(Double lat, Double lng) {
        GeoPoint geoPoint = new GeoPoint(lat, lng);
        this.location = geoPoint;
    }
}

