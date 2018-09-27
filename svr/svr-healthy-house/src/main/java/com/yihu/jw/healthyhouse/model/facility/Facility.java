package com.yihu.jw.healthyhouse.model.facility;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

/**
 * 设施
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
@Entity
@Table(name = "facility")
public class Facility extends UuidIdentityEntityWithOperator {
    //设施编码
    @Column(name = "code", nullable = false)
    private String code;
    //设施名字
    @Column(name = "name", nullable = false)
    private String name;
    //设施类别-系统字典-设施类别
    @Column(name = "category", nullable = false)
    private Integer category ;
    //设施联系人隶属机构编码
    @Column(name = "org_code")
    private String orgCode;
    //设施联系人隶属机构名称
    @Column(name = "org_name")
    private String orgName;
    // （管理员）联系人id
    @Column(name = "user_id")
    private String userId;
    // （管理员）联系人姓名
    @Column(name = "user_name")
    private String userName;
    //联系人电话
    @Column(name = "user_telephone")
    private String userTelephone ;
    //设施所在省份id
    @Column(name = "province_id")
    private String provinceId;
    //城市编码
    @Column(name = "city_code")
    private String cityCode;
    //城市名称
    @Column(name = "city_name")
    private String cityName;
    //县编码
    @Column(name = "county_code")
    private String countyCode ;
    //县名称
    @Column(name = "county_name")
    private String countyName;
    //街道
    @Column(name = "street")
    private String street;
    //地址
    @Column(name = "adress")
    private String adress;
    //服务时间：周一至周天，用逗号隔开
    @Column(name = "service_day")
    private String serviceDay ;
    //服务开始时间：默认00:00:00
    @Column(name = "service_start_time")
    private String serviceStartTime;
    // 服务结束时间：默认23:59:59
    @Column(name = "service_end_time")
    private String serviceEndTime;
    // 运营状态：0开放，1关闭，2损坏，3维修
    @Column(name = "status")
    private String status;
    //  设施经度
    @Column(name = "longitude")
    private double longitude;
    // 设施维度
    @Column(name = "latitude")
    private double latitude;
    // 备注
    @Column(name = "remarks")
    private String remarks;
    //小屋形象照
    @Column(name = "img_path")
    private String imgPath;

    private String statusName;
    private String  categoryValue ;
    private List<FacilityServerRelation> facilityServerRelation;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Formula("( SELECT a.value FROM system_dict_entries a WHERE a.dict_id = 3 AND a.code = status )")
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    @Transient
    public List<FacilityServerRelation> getFacilityServerRelation() {
        return facilityServerRelation;
    }

    public void setFacilityServerRelation(List<FacilityServerRelation> facilityServerRelation) {
        this.facilityServerRelation = facilityServerRelation;
    }

    @Formula("( SELECT a.value FROM system_dict_entries a WHERE a.dict_id = 5 AND a.code = category )")
    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }
    @Transient
    public String getAddress(){
        String address = "";
        if (this.getProvinceId()!=null ) {

        }
        if (this.getCityName() !=null ){
            address += this.getCityName();
        }
        if (this.getCountyName() !=null ){
            address += this.getCountyName();
        }
        if (this.getStreet() !=null) {
            address += this.getStreet();
        }
        return address;
    }
}
