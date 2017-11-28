package com.yihu.jw.base.user;

import com.yihu.jw.IdEntityWithOperation;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseEmploy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_employ")
public class BaseEmployDO extends IdEntityWithOperation implements java.io.Serializable {

	// Fields

	private String saasId;
	private String name;
	private String pyCode;
	private String sex;
	private String photo;
	private String skill;
	private String workPortal;
	private String email;
	private String phone;
	private String secondPhone;
	private String familyTel;
	private String officeTel;
	private String introduction;
	private String jxzc;
	private String lczc;
	private String xlzc;
	private String xzzc;
	private Integer status;

	// Constructors

	/** default constructor */
	public BaseEmployDO() {
	}

	/** minimal constructor */
	public BaseEmployDO(String id, String saasId) {
		this.id = id;
		this.saasId = saasId;
	}


	@Column(name = "saas_id", nullable = false, length = 50)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "py_code", length = 50)
	public String getPyCode() {
		return this.pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "photo", length = 256)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "skill", length = 100)
	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	@Column(name = "work_portal", length = 256)
	public String getWorkPortal() {
		return this.workPortal;
	}

	public void setWorkPortal(String workPortal) {
		this.workPortal = workPortal;
	}

	@Column(name = "email", length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "second_phone", length = 20)
	public String getSecondPhone() {
		return this.secondPhone;
	}

	public void setSecondPhone(String secondPhone) {
		this.secondPhone = secondPhone;
	}

	@Column(name = "family_tel", length = 20)
	public String getFamilyTel() {
		return this.familyTel;
	}

	public void setFamilyTel(String familyTel) {
		this.familyTel = familyTel;
	}

	@Column(name = "office_tel", length = 20)
	public String getOfficeTel() {
		return this.officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	@Column(name = "introduction", length = 256)
	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "jxzc", length = 50)
	public String getJxzc() {
		return this.jxzc;
	}

	public void setJxzc(String jxzc) {
		this.jxzc = jxzc;
	}

	@Column(name = "lczc", length = 50)
	public String getLczc() {
		return this.lczc;
	}

	public void setLczc(String lczc) {
		this.lczc = lczc;
	}

	@Column(name = "xlzc", length = 50)
	public String getXlzc() {
		return this.xlzc;
	}

	public void setXlzc(String xlzc) {
		this.xlzc = xlzc;
	}

	@Column(name = "xzzc", length = 50)
	public String getXzzc() {
		return this.xzzc;
	}

	public void setXzzc(String xzzc) {
		this.xzzc = xzzc;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}