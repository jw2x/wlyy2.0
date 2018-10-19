package com.yihu.jw.entity.base.user;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity - 后台管理员
 * Created by progr1mmer on 2018/8/20.
 */
@Entity
@Table(name = "base_user")
public class UserDO extends UuidIdentityEntityWithOperator {

    /**
     * 性别
     */
    public enum Gender {
        //男
        male,
        //女
        female
    }

    //用户名
    private String username;
    //密码
    private String password;
    //密码散列值
    private String salt;
    //姓名
    private String name;
    //性别
    private Gender gender;
    //身份证号码
    private String idcard;
    //手机号码
    private String mobile;
    //邮箱
    private String email;
    /**
     * 简化模式获取token所需要的凭证
     */
    private String ak;
    //是否可用
    private Boolean enabled;
    //是否锁定
    private Boolean locked;
    //锁定时间
    private Date lockedDate;
    //最后登陆时间
    private Date loginDate;
    //登陆失败次数
    private Integer loginFailureCount;
    //saas化的id
    private String  saasId;

    public UserDO(){}

    public UserDO(String username,String password,String mobile){
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "salt", nullable = false)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "idcard")
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Column(name = "mobile", length = 200)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "email", length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ak", length = 200)
    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    @Column(name = "enabled", nullable = false)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "locked", nullable = false)
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Column(name = "locked_date")
    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    @Column(name = "login_date")
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Column(name = "login_failure_count")
    public Integer getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(Integer loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }
    @Column(name = "saas_id",nullable = false)
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }
}
