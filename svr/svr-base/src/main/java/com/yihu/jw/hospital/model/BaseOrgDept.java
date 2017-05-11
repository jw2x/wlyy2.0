package com.yihu.jw.hospital.model;// default package

import java.util.Date;
import javax.persistence.*;

/**
 * 机构科室表
 */
@Entity
@Table(name = "base_org_dept")
public class BaseOrgDept implements java.io.Serializable {

	// Fields

	private Integer id;//'非业务主键'
	private String code;//'科室标识'
	private String name;//'科室名称'
	private String spell;//'科室名称拼音首字母
	private String del;//'删除标识，1正常，0删除'
	private String orgCode;//'关联 base_org code'
	private String bigdepartmentSn;//'国家一级标准科室SN'
	private String deptTel;//'科室电话'
	private String gloryName;//'科室荣誉字典(多选)国家重点科室,省级重点科室,医院特色专科'
	private String intro;//'科室介绍'
	private String floor;//'楼层号'
	private Integer sort;//'排序权重 默认0'
	private String parentCode;//'父科室code'
	private Date createTime;//创建时间
	private Date updateTime;//修改时间

	// Constructors

	/** default constructor */
	public BaseOrgDept() {
	}

	/** minimal constructor */
	public BaseOrgDept(Integer id, String code, String name, Date createTime,
			Date updateTime) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public BaseOrgDept(Integer id, String code, String name, String spell,
			String del, String orgCode, String bigdepartmentSn, String deptTel,
			String gloryName, String intro, String floor, Integer sort,
			String parentCode, Date createTime, Date updateTime) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.spell = spell;
		this.del = del;
		this.orgCode = orgCode;
		this.bigdepartmentSn = bigdepartmentSn;
		this.deptTel = deptTel;
		this.gloryName = gloryName;
		this.intro = intro;
		this.floor = floor;
		this.sort = sort;
		this.parentCode = parentCode;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", nullable = false, length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Column(name = "org_code", length = 100)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

	@Column(name = "parent_code", length = 100)
	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}