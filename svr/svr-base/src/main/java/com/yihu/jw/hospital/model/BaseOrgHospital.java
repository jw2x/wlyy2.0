package com.yihu.jw.hospital.model;// default package

import javax.persistence.*;
import java.util.Date;

/**
 * 医院扩展信息表
 */
@Entity
@Table(name = "base_org_hospital")
public class BaseOrgHospital implements java.io.Serializable {

	// Fields

	private Integer id;//非业务主键
	private String code;//业务主键
	private String orgCode;//机构代码 关联base_org
	private String roadCode;//街道编码
	private String centerSite;//中心/站点
	private String ascriptionType;//医院归属1.部属医院2.省属医院3.市属医院9：未知
	private String levelId;//医院等级id：..参考国家标准
	private String levelName;//医院等级名称：三甲..参考国家标准
	private Date createTime;//创建时间
	private Date updateTime;//修改时间

	// Constructors

	/** default constructor */
	public BaseOrgHospital() {
	}

	/** minimal constructor */
	public BaseOrgHospital(Integer id, String code) {
		this.id = id;
		this.code = code;
	}

	/** full constructor */
	public BaseOrgHospital(Integer id, String code, String orgCode,
			String roadCode, String centerSite, String ascriptionType,
			String levelId, String levelName) {
		this.id = id;
		this.code = code;
		this.orgCode = orgCode;
		this.roadCode = roadCode;
		this.centerSite = centerSite;
		this.ascriptionType = ascriptionType;
		this.levelId = levelId;
		this.levelName = levelName;
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

	@Column(name = "org_code", length = 100)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "road_code", length = 200)
	public String getRoadCode() {
		return this.roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	@Column(name = "center_site", length = 200)
	public String getCenterSite() {
		return this.centerSite;
	}

	public void setCenterSite(String centerSite) {
		this.centerSite = centerSite;
	}

	@Column(name = "ascription_type", length = 2)
	public String getAscriptionType() {
		return this.ascriptionType;
	}

	public void setAscriptionType(String ascriptionType) {
		this.ascriptionType = ascriptionType;
	}

	@Column(name = "level_id", length = 20)
	public String getLevelId() {
		return this.levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	@Column(name = "level_name", length = 20)
	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
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