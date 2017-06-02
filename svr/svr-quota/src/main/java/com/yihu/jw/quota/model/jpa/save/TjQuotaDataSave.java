package com.yihu.jw.quota.model.jpa.save;// default package

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TjQuotaDataSave entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tj_quota_data_save")
public class TjQuotaDataSave implements java.io.Serializable {

	// Fields

	private Integer id;
	private String quotaCode;
	private String saveCode;
	private String jsonConfig;
	private String type;//1:mysql数据库 2:redis 3ES

	// Constructors

	/** default constructor */
	public TjQuotaDataSave() {
	}

	/** full constructor */
	public TjQuotaDataSave(String quotaCode, String saveCode, String jsonConfig) {
		this.quotaCode = quotaCode;
		this.saveCode = saveCode;
		this.jsonConfig = jsonConfig;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "quota_code", length = 100)
	public String getQuotaCode() {
		return this.quotaCode;
	}

	public void setQuotaCode(String quotaCode) {
		this.quotaCode = quotaCode;
	}

	@Column(name = "save_code", length = 100)
	public String getSaveCode() {
		return this.saveCode;
	}

	public void setSaveCode(String saveCode) {
		this.saveCode = saveCode;
	}

	@Column(name = "json_config", length = 1500)
	public String getJsonConfig() {
		return this.jsonConfig;
	}

	public void setJsonConfig(String jsonConfig) {
		this.jsonConfig = jsonConfig;
	}
	@Transient
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}