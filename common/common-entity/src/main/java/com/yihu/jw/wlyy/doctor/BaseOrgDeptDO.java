package com.yihu.jw.wlyy.doctor;// default package

import com.yihu.jw.IdEntityWithOperation;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseOrgDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_org_dept")
public class BaseOrgDeptDO extends IdEntityWithOperation implements java.io.Serializable {

	// Fields
	private String orgId;
	private String parentId;
	private String name;
	private String spell;
	private String del;
	private String bigdepartmentSn;
	private String deptTel;
	private String gloryName;
	private String intro;
	private String floor;
	private Integer sort;

	// Constructors

	/** default constructor */
	public BaseOrgDeptDO() {
	}

	@Column(name = "org_id", nullable = false, length = 100)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "parent_id", nullable = false, length = 100)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "spell", length = 20)
	public String getSpell() {
		return this.spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	@Column(name = "del", length = 1)
	public String getDel() {
		return this.del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	@Column(name = "bigdepartment_sn", length = 22)
	public String getBigdepartmentSn() {
		return this.bigdepartmentSn;
	}

	public void setBigdepartmentSn(String bigdepartmentSn) {
		this.bigdepartmentSn = bigdepartmentSn;
	}

	@Column(name = "dept_tel", length = 30)
	public String getDeptTel() {
		return this.deptTel;
	}

	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}

	@Column(name = "glory_name", length = 500)
	public String getGloryName() {
		return this.gloryName;
	}

	public void setGloryName(String gloryName) {
		this.gloryName = gloryName;
	}

	@Column(name = "intro", length = 1000)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "floor", length = 20)
	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}