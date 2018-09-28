package com.yihu.jw.healthyhouse.model.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author HZY
 * @created 2018/9/18 17:07
 */
@Entity
@Table(name = "user")
public class User extends UuidIdentityEntityWithOperator {

    @Column(name = "login_code", nullable = false)
    private String loginCode;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "gender" )
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    @Column(name = "birthday" )
    private Date birthday;
    @Column(name = "id_card_no", nullable = false)
    private String idCardNo;
    @Column(name = "telephone", nullable = false)
    private String telephone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Column(name = "last_login_time", length = 0)
    private Date lastLoginTime;
    @Column(name = "img_remote_path")
    private String imgRemotePath;   //头像地址
    @Column(name = "user_type", nullable = false)
    private String userType;         //用户类型
    @Column(name = "activated", nullable = false)
    private Integer activated;       //用户状态 0冻结，1激活
    @Column(name = "activated_content", nullable = false)
    private String activatedContent;       //账户冻结原因
    @Column(name = "province_code", nullable = false)
    private String provinceCode;    //省编码
    @Column(name = "city_code", nullable = false)
    private String cityCode;        //市编码
    @Column(name = "city_name", nullable = false)
    private String cityName;        //市名称
    @Column(name = "area_code", nullable = false)
    private String areaCode;        //所在县区编码
    @Column(name = "area_name", nullable = false)
    private String areaName;        //所在县区名称
    @Column(name = "street", nullable = false)
    private String street;        //所在街道名称

    @Column(name = "salt")
    private String salt; //加密种子
    @Column(name = "facility_used_count")
    private Integer facilityUsedCount;//设施使用次数

    @Column(name = "realname_authentication")
    private String realnameAuthentication;//实名认证
    @Column(name = "phone_authentication")
    private String phoneAuthentication;//手机认证
    @Column(name = "ijk_authentication")
    private String ijkAuthentication;//i健康认证


    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getImgRemotePath() {
        return imgRemotePath;
    }

    public void setImgRemotePath(String imgRemotePath) {
        this.imgRemotePath = imgRemotePath;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getFacilityUsedCount() {
        return facilityUsedCount;
    }

    public void setFacilityUsedCount(Integer facilityUsedCount) {
        this.facilityUsedCount = facilityUsedCount;
    }

    public String getActivatedContent() {
        return activatedContent;
    }

    public void setActivatedContent(String activatedContent) {
        this.activatedContent = activatedContent;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRealnameAuthentication() {
        return realnameAuthentication;
    }

    public void setRealnameAuthentication(String realnameAuthentication) {
        this.realnameAuthentication = realnameAuthentication;
    }

    public String getPhoneAuthentication() {
        return phoneAuthentication;
    }

    public void setPhoneAuthentication(String phoneAuthentication) {
        this.phoneAuthentication = phoneAuthentication;
    }

    public String getIjkAuthentication() {
        return ijkAuthentication;
    }

    public void setIjkAuthentication(String ijkAuthentication) {
        this.ijkAuthentication = ijkAuthentication;
    }

    @Transient
    public String getAddress(){
        String address ="";
        if (this.getCityName()!=null) {
            address += this.getCityName();
        }
        if (this.getAreaName()!=null) {
            address += this.getAreaName();
        }
        if (this.getStreet() !=null ){
            address += this.getStreet();
        }
        return address;
    }
}
