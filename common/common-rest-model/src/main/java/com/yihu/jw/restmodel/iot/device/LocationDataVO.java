package com.yihu.jw.restmodel.iot.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.base.es.config.model.SaveModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;


/**
 * Created by chenweida on 2018/2/12.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "设备地址信息", description = "设备地址信息")
public class LocationDataVO extends SaveModel {

    @ApiModelProperty("设备绑定的居民身份证")
    private String idCard;

    @ApiModelProperty("设备类型标识")
    private String categoryCode;

    @ApiModelProperty("设备SnID")
    private String deviceSn;

    @ApiModelProperty("设备经纬度")
    private GeoPoint location;

    @ApiModelProperty("设备绑定时间")
    private String deviceTime;

    @ApiModelProperty("病情：0绿标，1黄标，2红标")
    private Integer diseaseCondition;

    @ApiModelProperty("创建时间")
    private String createTime;

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

    public String getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(String deviceTime) {
        this.deviceTime = deviceTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDiseaseCondition() {
        return diseaseCondition;
    }

    public void setDiseaseCondition(Integer diseaseCondition) {
        this.diseaseCondition = diseaseCondition;
    }

    public void setLocation(Double lat, Double lng) {
        GeoPoint geoPoint = new GeoPoint(lat, lng);
        this.location = geoPoint;
    }
}

