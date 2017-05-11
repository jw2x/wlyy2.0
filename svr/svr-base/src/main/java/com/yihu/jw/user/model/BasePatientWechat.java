package com.yihu.jw.user.model;// default package

import java.util.Date;
import javax.persistence.*;

/**
 * BasePatientWechat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_patient_wechat")
public class BasePatientWechat implements java.io.Serializable {

	// Fields
	private Integer id;//'主键'
	private String code;//'业务主键'
	private String saasId;///saas配置
	private String wechatCode;//关联的微信code 关联表 Wx_Wechat
	private String patientCode;//'关联wlyy_patient表code'
	private String openid;//
	private String unionid;//'微信union_id'
	private Date createTime;//'创建时间'

	// Constructors

	/** default constructor */
	public BasePatientWechat() {
	}

	/** minimal constructor */
	public BasePatientWechat(Integer id, Date createTime) {
		this.id = id;
		this.createTime = createTime;
	}

	/** full constructor */
	public BasePatientWechat(Integer id, String code, String saasId,
			String wechatCode, String patientCode, String openid,
			String unionid, Date createTime) {
		this.id = id;
		this.code = code;
		this.saasId = saasId;
		this.wechatCode = wechatCode;
		this.patientCode = patientCode;
		this.openid = openid;
		this.unionid = unionid;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "saas_id", length = 64)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "wechat_code", length = 10)
	public String getWechatCode() {
		return this.wechatCode;
	}

	public void setWechatCode(String wechatCode) {
		this.wechatCode = wechatCode;
	}

	@Column(name = "patient_code", length = 100)
	public String getPatientCode() {
		return this.patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	@Column(name = "openid", length = 50)
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "unionid", length = 50)
	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}