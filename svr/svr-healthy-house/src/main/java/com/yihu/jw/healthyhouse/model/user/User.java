package com.yihu.jw.healthyhouse.model.user;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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
    @Column(name = "birthday" )
    private Date birthday;
    @Column(name = "id_card_no", nullable = false)
    private String idCardNo;
    @Column(name = "telephone", nullable = false)
    private String telephone;
    @Column(name = "last_login_time", length = 0)
    private String lastLoginTime;
    @Column(name = "img_remote_path")
    private String imgRemotePath;   //头像地址
    @Column(name = "user_type", nullable = false)
    private String userType;         //用户类型
    @Column(name = "activated", nullable = false)
    private Integer activated;       //用户状态 0冻结，1激活
    @Column(name = "province_code", nullable = false)
    private String provinceCode;    //省编码
    @Column(name = "city_code", nullable = false)
    private String cityCode;        //市编码

    @Column(name = "salt")
    private String salt; //加密种子

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

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
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


}
