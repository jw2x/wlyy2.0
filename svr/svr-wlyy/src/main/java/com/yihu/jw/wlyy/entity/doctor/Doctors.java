package com.yihu.jw.wlyy.entity.doctor;


import com.yihu.jw.wlyy.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
/**
 * Created by Administrator on 2017/6/7 0007.
 */
@Entity
public class Doctors extends IdEntity {

    private static final long serialVersionUID = 3138130150854187709L;

    private String userId;//云平台用户ID
    private String code;//业务code
    private String name;//姓名
    private String pyCode;//姓名首字母
    private String sex;//性别(1男,2女)
    private String photo;//医生头像
    private String skill;//医生专长
    private String workPortal;//医生门户首页
    private String email;//邮箱
    private String phone;//联系电话
    private String secondPhone;//备用电话
    private String familyTel;//家庭电话(固)
    private String officeTel;//办公电话(固)
    private String introduction;//简介
    private String jxzc;//教学职称
    private String lczc;//临床职称
    private String xlzc;//学历职称
    private String xzzc;//行政职称
    private Integer status;//状态:-1 删除 0 禁用 1可用
    private Date createTime;//
    private Date updateTime;


    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name = "py_code")
    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
    @Column(name = "skill")
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    
    @Column(name = "work_portal")
    public String getWorkPortal() {
        return workPortal;
    }

    public void setWorkPortal(String workPortal) {
        this.workPortal = workPortal;
    }

    
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    @Column(name = "second_phone")
    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    
    @Column(name = "family_tel")
    public String getFamilyTel() {
        return familyTel;
    }

    public void setFamilyTel(String familyTel) {
        this.familyTel = familyTel;
    }

    
    @Column(name = "office_tel")
    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    
    @Column(name = "jxzc")
    public String getJxzc() {
        return jxzc;
    }

    public void setJxzc(String jxzc) {
        this.jxzc = jxzc;
    }

    
    @Column(name = "lczc")
    public String getLczc() {
        return lczc;
    }

    public void setLczc(String lczc) {
        this.lczc = lczc;
    }

    
    @Column(name = "xlzc")
    public String getXlzc() {
        return xlzc;
    }

    public void setXlzc(String xlzc) {
        this.xlzc = xlzc;
    }

    
    @Column(name = "xzzc")
    public String getXzzc() {
        return xzzc;
    }

    public void setXzzc(String xzzc) {
        this.xzzc = xzzc;
    }

    
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
