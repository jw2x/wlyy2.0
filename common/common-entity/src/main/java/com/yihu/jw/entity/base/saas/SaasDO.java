package com.yihu.jw.entity.base.saas;// default package


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WlyySaas entity. @author MyEclipse Persistence Tools
 *
 */
@Entity
@Table(name = "base_saas")
public class SaasDO extends UuidIdentityEntityWithOperator {

    public enum Status {
        auditWait,
        auditPassed,
        auditNotPassed,
		delete
    }

	public enum Type {
	    //家医
        familyDoctor,
        //等级医院
        gradeHospital,
        //混合型
        hybrid
    }

    private String orgCode; //机构编码
	private String name; //系统名称
	private Status status; //状态 0 已删除 1待审核 2审核通过 3审核不通过
	private String remark; //备注
	private String logo; //远程fastDFS文件地址
    private Integer theme; //主题ID
    private Type type; //类型

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

    @Column(name = "remark", length = 1000)
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

	@Column(name = "theme")
	public Integer getTheme() {
		return theme;
	}

	public void setTheme(Integer theme) {
		this.theme = theme;
	}

	@Column(name = "type", nullable = false)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}