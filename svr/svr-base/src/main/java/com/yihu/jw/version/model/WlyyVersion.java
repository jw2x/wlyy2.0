package com.yihu.jw.version.model;// default package

import com.yihu.jw.base.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * WlyyVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wlyy_version")
public class WlyyVersion extends IdEntity implements java.io.Serializable {

	// Fields

	private String saasId; //saas code
	private String code;
	private String name;
	private Double versionInt;
	private String versionStr;
	private String url;//app下载的路径
	private String info;//app更新的信息
	private Double size;//大小 MB
	private Date createTime;
	private String createUser;
	private String createUserName;
	private Date updateTime;
	private String updateUser;
	private String updateUserName;
	private Integer status;////-1 删除 0 禁用 可用

	// Constructors

	/** default constructor */
	public WlyyVersion() {
	}

	/** minimal constructor */
	public WlyyVersion(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/** full constructor */
	public WlyyVersion(String saasId, String code, String name,
			Double versionInt, String versionStr, String url, String info,
			Double size) {
		this.saasId = saasId;
		this.code = code;
		this.name = name;
		this.versionInt = versionInt;
		this.versionStr = versionStr;
		this.url = url;
		this.info = info;
		this.size = size;
	}


	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "code", nullable = false, length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "version_int", precision = 22, scale = 0)
	public Double getVersionInt() {
		return this.versionInt;
	}

	public void setVersionInt(Double versionInt) {
		this.versionInt = versionInt;
	}

	@Column(name = "version_str", length = 10)
	public String getVersionStr() {
		return this.versionStr;
	}

	public void setVersionStr(String versionStr) {
		this.versionStr = versionStr;
	}

	@Column(name = "url", length = 300)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "info", length = 1000)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "size", precision = 22, scale = 0)
	public Double getSize() {
		return this.size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}