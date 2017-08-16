package com.yihu.jw.version.model;// default package

import com.yihu.jw.base.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BaseServerVersion entity. @author MyEclipse Persistence Tools
 * 后台版本model
 */


@Entity
@Table(name = "base_server_version")
public class BaseServerVersion  extends IdEntity implements java.io.Serializable {
 
	private String saasId;//关联base_saas code
	private String name; //版本名称
	private Integer versionInt;//版本号
	private Integer status;////-1 删除 0 禁用 可用
	private String remark;
	@Transient
	private List<BaseServerUrlVersion> children = new ArrayList<>();

	//children长度为0时    state  “open”表示是子节点，“closed”表示为父节点；
	// children长度>0时,  state   “open,closed”表示是节点的打开关闭
	@Transient
	private String state;

	// Constructors

	/** default constructor */
	public BaseServerVersion() {
	}

	/** minimal constructor */
	public BaseServerVersion(String code, String name, Date createTime,
			Date updateTime) {
		this.code = code;
		this.name = name;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public BaseServerVersion(String code, String saasId, String userCode,
			String name, Integer versionInt, Date createTime,
			String createUser, String createUserName, Date updateTime,
			String updateUser, String updateUserName, Integer status,
			String remark) {
		this.code = code;
		this.saasId = saasId;
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

	@Column(name = "name", nullable = false, length = 10)
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

	@Column(name = "status", length = 1)
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

	public List<BaseServerUrlVersion> getChildren() {
		return children;
	}

	public void setChildren(List<BaseServerUrlVersion> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}