package com.yihu.jw.version.model;// default package

import com.yihu.jw.base.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * BaseServerUrlVersion entity. @author MyEclipse Persistence Tools
 * 功能版本
 */
@Entity
@Table(name = "base_server_url_version")
public class BaseServerUrlVersion extends IdEntity implements java.io.Serializable {

	// Fields

	private String saasId;
	private String functionCode;//关联功能表 base_function code
	@Transient
	private String functionName;//功能名字
	private String serverCode;//关联 base_server_version code
	@Transient
	private String serverName;//base_server_version name
	private String name;//版本名称
	private Integer versionInt;//数字版本号
	private Integer status;//1: 正常 0：不可用  -1删除
	private String remark;

	// Constructors

	/** default constructor */
	public BaseServerUrlVersion() {
	}

	/** minimal constructor */
	public BaseServerUrlVersion(String code, String name, Date createTime,
			Date updateTime) {
		this.code = code;
		this.name = name;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public BaseServerUrlVersion(String code, String saasId,
			String functionCode, String serverCode, String name,
			Integer versionInt, Date createTime, String createUser,
			String createUserName, Date updateTime, String updateUser,
			String updateUserName, Integer status, String remark) {
		this.code = code;
		this.saasId = saasId;
		this.functionCode = functionCode;
		this.serverCode = serverCode;
		this.name = name;
		this.versionInt = versionInt;
		this.createTime = createTime;
		this.createUser = createUser;
		this.createUserName = createUserName;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.updateUserName = updateUserName;
		this.status = status;
		this.remark = remark;
	}

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "function_code", length = 100)
	public String getFunctionCode() {
		return this.functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	@Column(name = "server_code", length = 100)
	public String getServerCode() {
		return this.serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	@Column(name = "name",  length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "remark", length = 1500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
}