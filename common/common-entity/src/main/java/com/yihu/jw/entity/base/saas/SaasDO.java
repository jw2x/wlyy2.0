package com.yihu.jw.entity.base.saas;// default package


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - SAAS
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_saas")
public class SaasDO extends UuidIdentityEntityWithOperator {

    public enum Status {
        //待审核
        auditWait,
        //审核通过
        auditPassed,
        //审核不通过
        auditNotPassed,
        //已删除
        delete
    }

    private String orgCode; //机构编码
    private String name; //系统名称
    private Status status; //状态  0待审核 1审核通过 2审核不通过 3已删除
    private String remark; //备注
    private String logo; //远程fastDFS文件地址
    private Integer type; //类型
    private String manager; //管理员 - 关联user表id字段
    private String email; //管理员邮箱
    private String mobile; //管理员手机号码
    //审核不通过的原因
    private String auditFailedReason;
    //管理员姓名
    private String managerName;

    @Column(name = "org_code", nullable = false)
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status", nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Column(name = "type", nullable = false)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "manager", length = 50)
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "audit_failed_reason")
    public String getAuditFailedReason() {
        return auditFailedReason;
    }

    public void setAuditFailedReason(String auditFailedReason) {
        this.auditFailedReason = auditFailedReason;
    }

    @Column(name = "manager_name")
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}