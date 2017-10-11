package com.yihu.jw.manage.model.version;// default package

import javax.persistence.*;

/**
 * BaseUserUrlVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_user_url_version")
public class BaseUserUrlVersion  implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String code;
	private String saasId;//saasid 关联 base_saas
	private String userCode;//关联用户
	private String usvCode;//关联base_url_server_version code
	private String moduleCode;//关联模块表 base_modile code
	private String functionCode;//关联功能表 base_function code
	private String url;//后台url
	private Integer versionInt;//接口的版本
	private Integer status;//1: 正常 0：不可用  -1删除

	// Constructors

	/** default constructor */
	public BaseUserUrlVersion() {
	}

	/** minimal constructor */
	public BaseUserUrlVersion(Integer id, String code) {
		this.code = code;
	}

	/** full constructor */
	public BaseUserUrlVersion(Integer id, String code, String saasId,
			String userCode, String usvCode, String moduleCode,
			String functionCode, String url, Integer versionInt, Integer status) {
		this.code = code;
		this.saasId = saasId;
		this.userCode = userCode;
		this.usvCode = usvCode;
		this.moduleCode = moduleCode;
		this.functionCode = functionCode;
		this.url = url;
		this.versionInt = versionInt;
		this.status = status;
	}

	@Column(name = "code", nullable = false, length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "user_code", length = 100)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "usv_code", length = 100)
	public String getUsvCode() {
		return this.usvCode;
	}

	public void setUsvCode(String usvCode) {
		this.usvCode = usvCode;
	}

	@Column(name = "module_code", length = 100)
	public String getModuleCode() {
		return this.moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	@Column(name = "function_code", length = 100)
	public String getFunctionCode() {
		return this.functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	@Column(name = "url", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "version_int")
	public Integer getVersionInt() {
		return this.versionInt;
	}

	public void setVersionInt(Integer versionInt) {
		this.versionInt = versionInt;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}