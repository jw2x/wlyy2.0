package com.yihu.jw.healthyhouse.model.user;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户导航记录
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
@Entity
@Table(name = "facility_used_records")
public class FacilityUsedRecord extends UuidIdentityEntityWithOperator {
    //用户起始位置经度
    @Column(name = "user_initial_longitude", nullable = false)
    private double userInitialLongitude;
    //用户起始位置纬度
    @Column(name = "user_initial_latitudes", nullable = false)
    private double userInitialLatitudes;
    //设施经度
    @Column(name = "facilitie_longitude", nullable = false)
    private double facilitieLongitude;
    //设施纬度
    @Column(name = "facilitie_latitudes", nullable = false)
    private double facilitieLatitudes;
    //设施编码
    @Column(name = "facilitie_code", nullable = false)
    private String facilitieCode;
    //设施名称
    @Column(name = "facilitie_name", nullable = false)
    private String facilitieName;
    //设施地址
    @Column(name = "facilitie_addr", nullable = false)
    private String facilitieAddr;
    //服务编码
    @Column(name = "service_code")
    private String serviceCode;
    //服务名称
    @Column(name = "service_name")
    private String serviceName;
    //导航时长
    @Column(name = "duration")
    private String duration;
    //用户id
    @Column(name = "user_id")
    private String userId;
    //使用次数
    @Column(name = "num")
    private Integer num;

    public double getUserInitialLongitude() {
        return userInitialLongitude;
    }

    public void setUserInitialLongitude(double userInitialLongitude) {
        this.userInitialLongitude = userInitialLongitude;
    }

    public double getUserInitialLatitudes() {
        return userInitialLatitudes;
    }

    public void setUserInitialLatitudes(double userInitialLatitudes) {
        this.userInitialLatitudes = userInitialLatitudes;
    }

    public double getFacilitieLongitude() {
        return facilitieLongitude;
    }

    public void setFacilitieLongitude(double facilitieLongitude) {
        this.facilitieLongitude = facilitieLongitude;
    }

    public double getFacilitieLatitudes() {
        return facilitieLatitudes;
    }

    public void setFacilitieLatitudes(double facilitieLatitudes) {
        this.facilitieLatitudes = facilitieLatitudes;
    }

    public String getFacilitieCode() {
        return facilitieCode;
    }

    public void setFacilitieCode(String facilitieCode) {
        this.facilitieCode = facilitieCode;
    }

    public String getFacilitieName() {
        return facilitieName;
    }

    public void setFacilitieName(String facilitieName) {
        this.facilitieName = facilitieName;
    }

    public String getFacilitieAddr() {
        return facilitieAddr;
    }

    public void setFacilitieAddr(String facilitieAddr) {
        this.facilitieAddr = facilitieAddr;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
