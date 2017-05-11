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
 * 患者表
 */
@Entity
@Table(name = "base_patient")
public class BasePatient implements java.io.Serializable {

	// Fields
	private Integer id;//'业务无关主键'
	private String code;//'业务主键'
	private String accountCode;//'关联wlyy_login_account账号code'
	private String idcard;//'身份证号'
	private String name;//'姓名'
	private Date birthday;//'生日'
	private String sex;//'参考国家标准'
	private String mobile;//'手机号'
	private String phone;//''联系电话'
	private String ssc;//'社保卡号'
	private String photo;//default '../../../images/p-female.png' comment '头像http地址'
	private String province;//省
	private String provinceName;//省名称
	private String city;//市
	private String cityName;//市名称
	private String town;//区
	private String townName;//区名称
	private String street;//街道
	private String streetName;//街道名称
	private String address;//详细地址
	private Integer status;//'用户状态：1正常，0禁用，-1恶意注册，2审核中'
	private Date updateTime;//修改时间
	private Date createTime;//创建时间
	private String saasId;//'saas配置id
	private String email;//'邮箱'
	private String nation;//'籍贯'
	private String spell;//'名称拼音首字母'

	// Constructors

	/** default constructor */
	public BasePatient() {
	}

	/** minimal constructor */
	public BasePatient(Integer id, String code, String idcard, String photo,
			Date updateTime, Date createTime) {
		this.id = id;
		this.code = code;
		this.idcard = idcard;
		this.photo = photo;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	/** full constructor */
	public BasePatient(Integer id, String code, String accountCode,
			String idcard, String name, Date birthday, String sex,
			String mobile, String phone, String ssc, String photo,
			String province, String provinceName, String city, String cityName,
			String town, String townName, String street, String streetName,
			String address, Integer status, Date updateTime, Date createTime,
			String saasId, String email, String nation, String spell) {
		this.id = id;
		this.code = code;
		this.accountCode = accountCode;
		this.idcard = idcard;
		this.name = name;
		this.birthday = birthday;
		this.sex = sex;
		this.mobile = mobile;
		this.phone = phone;
		this.ssc = ssc;
		this.photo = photo;
		this.province = province;
		this.provinceName = provinceName;
		this.city = city;
		this.cityName = cityName;
		this.town = town;
		this.townName = townName;
		this.street = street;
		this.streetName = streetName;
		this.address = address;
		this.status = status;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.saasId = saasId;
		this.email = email;
		this.nation = nation;
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

	@Column(name = "code", nullable = false, length = 50)
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

	@Column(name = "idcard", unique = true, nullable = false, length = 50)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 0)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "mobile", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "phone", length = 200)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ssc", length = 50)
	public String getSsc() {
		return this.ssc;
	}

	public void setSsc(String ssc) {
		this.ssc = ssc;
	}

	@Column(name = "photo", nullable = false, length = 100)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	@Column(name = "street", length = 50)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "street_name", length = 100)
	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "nation", length = 50)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "spell", length = 10)
	public String getSpell() {
		return this.spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

}