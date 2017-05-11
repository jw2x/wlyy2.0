package com.yihu.jw.user.model;// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * 员工表
 */
@Entity
@Table(name = "base_employee")
public class BaseEmployee implements java.io.Serializable {

	// Fields
	private Integer id;//'业务无关主键
	private String code;//'业务标识'
	private String accountCode;//'关联wlyy_login_account账号code'
	private String name;//'姓名'
	private String sex;//'性别（1男，2女） 用国家标准字典'
	private String idcard;//' 身份证'
	private Date birthday;//'生日'
	private String photo;//'../../../images/d-male.png' comment '头像http地址
	private String mobile;//'手机号'
	private String province;//省
	private String provinceName;//省名称
	private String city;//市
	private String cityName;//市名称
	private String town;//区
	private String townName;//区名称
	private String hospital;//机构
	private String hospitalName;//机构名称
	private String dept;//科室
	private String deptName;//科室名称
	private String expertise;//'医生专长'
	private String introduce;//'医生介绍'
	private Date createTime;//创建时间
	private Date updateTime;//修改时间
	private String iscertified;//'资格是否认证通过，1是，0否'
	private String qrcode;//二维码地址
	private String del;//'1: 正常 0： 删除'
	private String identityType;//'身份类型 1 医生 2其他'
	private String isFamous;//'是否是名医 1是  0 不是'
	private String isPasswordPrompt;//'是否提示设置密码  1 提示过 0未提示'
	private String saasId;//'saas配置id
	private String administrativeName;//'行政职位名称'
	private String administrativeCode;//'行政职位code'
	private String job;//'职称
	private String jobName;//'职称名称
	private String spell;//'名称拼音首字母'

	// Constructors

	/** default constructor */
	public BaseEmployee() {
	}

	/** minimal constructor */
	public BaseEmployee(Integer id, String code, String mobile,
			Date updateTime, String iscertified) {
		this.id = id;
		this.code = code;
		this.mobile = mobile;
		this.updateTime = updateTime;
		this.iscertified = iscertified;
	}

	/** full constructor */
	public BaseEmployee(Integer id, String code, String accountCode,
			String name, String sex, String idcard, Date birthday,
			String photo, String mobile, String province, String provinceName,
			String city, String cityName, String town, String townName,
			String hospital, String hospitalName, String dept, String deptName,
			String expertise, String introduce, Date updateTime,
			String iscertified, String qrcode, String del, String identityType,
			String isFamous, String isPasswordPrompt, String saasId,
			String administrativeName, String administrativeCode, String job,
			String jobName, String spell) {
		this.id = id;
		this.code = code;
		this.accountCode = accountCode;
		this.name = name;
		this.sex = sex;
		this.idcard = idcard;
		this.birthday = birthday;
		this.photo = photo;
		this.mobile = mobile;
		this.province = province;
		this.provinceName = provinceName;
		this.city = city;
		this.cityName = cityName;
		this.town = town;
		this.townName = townName;
		this.hospital = hospital;
		this.hospitalName = hospitalName;
		this.dept = dept;
		this.deptName = deptName;
		this.expertise = expertise;
		this.introduce = introduce;
		this.updateTime = updateTime;
		this.iscertified = iscertified;
		this.qrcode = qrcode;
		this.del = del;
		this.identityType = identityType;
		this.isFamous = isFamous;
		this.isPasswordPrompt = isPasswordPrompt;
		this.saasId = saasId;
		this.administrativeName = administrativeName;
		this.administrativeCode = administrativeCode;
		this.job = job;
		this.jobName = jobName;
		this.spell = spell;
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

	@Column(name = "code", unique = true, nullable = false, length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "account_code", length = 100)
	public String getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "idcard", length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 0)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "photo", length = 100)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "mobile", nullable = false, length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "province", length = 50)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "province_name", length = 50)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "city_name", length = 50)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "town", length = 50)
	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Column(name = "town_name", length = 50)
	public String getTownName() {
		return this.townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	@Column(name = "hospital", length = 50)
	public String getHospital() {
		return this.hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	@Column(name = "hospital_name", length = 50)
	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	@Column(name = "dept", length = 50)
	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "dept_name", length = 50)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "expertise", length = 300)
	public String getExpertise() {
		return this.expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	@Column(name = "introduce", length = 1500)
	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 0)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "iscertified", nullable = false, length = 1)
	public String getIscertified() {
		return this.iscertified;
	}

	public void setIscertified(String iscertified) {
		this.iscertified = iscertified;
	}

	@Column(name = "qrcode", length = 30)
	public String getQrcode() {
		return this.qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	@Column(name = "del", length = 1)
	public String getDel() {
		return this.del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	@Column(name = "identity_type", length = 1)
	public String getIdentityType() {
		return this.identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	@Column(name = "is_famous", length = 1)
	public String getIsFamous() {
		return this.isFamous;
	}

	public void setIsFamous(String isFamous) {
		this.isFamous = isFamous;
	}

	@Column(name = "is_password_prompt", length = 1)
	public String getIsPasswordPrompt() {
		return this.isPasswordPrompt;
	}

	public void setIsPasswordPrompt(String isPasswordPrompt) {
		this.isPasswordPrompt = isPasswordPrompt;
	}

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "administrative_name", length = 10)
	public String getAdministrativeName() {
		return this.administrativeName;
	}

	public void setAdministrativeName(String administrativeName) {
		this.administrativeName = administrativeName;
	}

	@Column(name = "administrative_code", length = 10)
	public String getAdministrativeCode() {
		return this.administrativeCode;
	}

	public void setAdministrativeCode(String administrativeCode) {
		this.administrativeCode = administrativeCode;
	}

	@Column(name = "job", length = 50)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "job_name", length = 50)
	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Column(name = "spell", length = 10)
	public String getSpell() {
		return this.spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

}