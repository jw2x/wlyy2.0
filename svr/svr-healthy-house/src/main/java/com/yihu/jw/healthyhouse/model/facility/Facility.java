package com.yihu.jw.healthyhouse.model.facility;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  设施服务
 * @author HZY
 * @created 2018/9/20 8:48
 */
@Entity
@Table(name = "facility")
public class Facility extends UuidIdentityEntity{

    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "code", nullable = false)
    private String name;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "org_code", nullable = false)
    private String orgCode;
    @Column(name = "org_name")
    private String orgName;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "user_telephone", nullable = false)
    private String userTelephone;
    @Column(name = "province_id", nullable = false)
    private String provinceId;
    @Column(name = "city_code", nullable = false)
    private String cityCode;
    @Column(name = "county_code", nullable = false)
    private String countyCode;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "service_day", nullable = false)
    private String serviceDay;
    @Column(name = "service_start_time", nullable = false)
    private String serviceStartTime;
    @Column(name = "service_end_time", nullable = false)
    private String serviceEndTime;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "longitude", nullable = false)
    private String longitude;
    @Column(name = "latitude", nullable = false)
    private String latitude;
    @Column(name = "img_path")
    private String imgPath;
    @Column(name = "remarks")
    private String remarks;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(String serviceDay) {
        this.serviceDay = serviceDay;
    }

    public String getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(String serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public String getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(String serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
