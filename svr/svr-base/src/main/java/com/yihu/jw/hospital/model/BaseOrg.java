package com.yihu.jw.hospital.model;// default package

import java.util.Date;
import javax.persistence.*;

/**
 * 机构表
 */
@Entity
@Table(name = "base_org")
public class BaseOrg implements java.io.Serializable {

	// Fields,
	private Integer id;//'业务无关主键'
	private String code;//'业务主键
	private String province;// '省份标识',
	private String provinceName;//'省份名称'
	private String city;//'城市标识'
	private String cityName;//城市名称
	private String town;//区标示
	private String townName;//区名称
	private String name;//机构名称
	private String alias;//'机构别名'
	private String spell;//'机构名称拼音首字母
	private String type;//'机构类型: 1.  医疗机构2.  企事业单位3.  政府机关4.  社会团体 5.药店 0.  部门 6.单位或者独立子公司7.基层机构 8.专业公共机构'
	private String address;//'机构详细地址'
	private String traffic;//'交通'
	private String photo;//'机构图片'
	private String saasId;//'saas配置id'
	private String longitude;//'经度'
	private String latitude;//'纬度'
	private String legalperson;//'法人'
	private String parentCode;//'上级机构code'
	private String combinationCode;//'医联体code'
	private String orgUrl;//'机构网址'
	private String del;//'作废标识，1正常，0作废'
	private String intro;//'机构简介'
	private Date createTime;//创建时间
	private Date updateTime;//修改时间

	// Constructors

	/** default constructor */
	public BaseOrg() {
	}

	/** minimal constructor */
	public BaseOrg(Integer id, String code, Date createTime, Date updateTime) {
		this.id = id;
		this.code = code;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public BaseOrg(Integer id, String code, String province,
			String provinceName, String city, String cityName, String town,
			String townName, String name, String alias, String spell,
			String type, String address, String traffic, String photo,
			String saasId, String longitude, String latitude,
			String legalperson, String parentCode, String combinationCode,
			String orgUrl, String del, String intro, Date createTime,
			Date updateTime) {
		this.id = id;
		this.code = code;
		this.province = province;
		this.provinceName = provinceName;
		this.city = city;
		this.cityName = cityName;
		this.town = town;
		this.townName = townName;
		this.name = name;
		this.alias = alias;
		this.spell = spell;
		this.type = type;
		this.address = address;
		this.traffic = traffic;
		this.photo = photo;
		this.saasId = saasId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.legalperson = legalperson;
		this.parentCode = parentCode;
		this.combinationCode = combinationCode;
		this.orgUrl = orgUrl;
		this.del = del;
		this.intro = intro;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "alias", length = 10)
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "spell", length = 20)
	public String getSpell() {
		return this.spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	@Column(name = "type", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "address", length = 300)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "traffic", length = 500)
	public String getTraffic() {
		return this.traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	@Column(name = "photo", length = 200)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "longitude", length = 10)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", length = 10)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "legalperson", length = 50)
	public String getLegalperson() {
		return this.legalperson;
	}

	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}

	@Column(name = "parent_code", length = 100)
	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Column(name = "combination_code", length = 100)
	public String getCombinationCode() {
		return this.combinationCode;
	}

	public void setCombinationCode(String combinationCode) {
		this.combinationCode = combinationCode;
	}

	@Column(name = "org_url", length = 200)
	public String getOrgUrl() {
		return this.orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	@Column(name = "del", length = 1)
	public String getDel() {
		return this.del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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