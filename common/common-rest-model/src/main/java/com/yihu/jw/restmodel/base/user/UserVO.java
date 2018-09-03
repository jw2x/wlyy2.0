package com.yihu.jw.restmodel.base.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * VO - 后台管理员
 * Created by progr1mmer on 2018/8/20.
 */
@ApiModel(value = "UserVO", description = "角色")
public class UserVO extends UuidIdentityVOWithOperator {

    //用户名
    @ApiModelProperty(value = "用户名", example = "wangxiaoming")
    private String username;
    //密码
    @ApiModelProperty(value = "密码", example = "25f9e794323b453885f5181f1b624d0b")
    private String password;
    //密码散列值
    @ApiModelProperty(value = "密码散列值", example = "Wsd2")
    private String salt;
    //姓名
    @ApiModelProperty(value = "姓名", example = "王小明")
    private String name;
    //性别
    @ApiModelProperty(value = "性别", example = "male")
    private UserDO.Gender gender;
    //身份证号码
    @ApiModelProperty(value = "身份证号码", example = "42210119750809601X")
    private String idcard;
    //手机号码
    @ApiModelProperty(value = "手机号码", example = "18888888888")
    private String mobile;
    //邮箱
    @ApiModelProperty(value = "邮箱", example = "wxm@jkzl.com")
    private String email;
    //是否可用
    @ApiModelProperty(value = "是否可用", example = "1")
    private Boolean enabled;
    //是否锁定
    @ApiModelProperty(value = "是否锁定", example = "1")
    private Boolean locked;
    //锁定时间
    @ApiModelProperty(value = "锁定时间", example = "2018-08-27 13:23:32")
    private Date lockedDate;
    //最后登陆时间
    @ApiModelProperty(value = "最后登陆时间", example = "2018-08-26 12:33:22")
    private Date loginDate;
    //登陆失败次数
    @ApiModelProperty(value = "登陆失败次数", example = "0")
    private Integer loginFailureCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDO.Gender getGender() {
        return gender;
    }

    public void setGender(UserDO.Gender gender) {
        this.gender = gender;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(Integer loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }
}
