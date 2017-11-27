package com.yihu.jw.wlyy.doctor;// default package

import com.yihu.jw.IdEntityWithOperation;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseOrg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_org")
public class BaseOrgDO extends IdEntityWithOperation implements java.io.Serializable {

	// Fields

	private String province;
	private String provinceName;
	private String city;
	private String cityName;
	private String town;
	private String townName;
	private String name;
	private String alias;
	private String spell;
	private String type;
	private String address;
	private String traffic;
	private String photo;
	private String saasId;
	private String longitude;
	private String latitude;
	private String legalperson;
	private String parentCode;
	private String combinationCode;
	private String orgUrl;
	private String del;
	private String intro;

	// Constructors

	/** default constructor */
	public BaseOrgDO() {
	}


	/** full constructor */
	public BaseOrgDO(String id, String province, String provinceName,
			String city, String cityName, String town, String townName,
			String name, String alias, String spell, String type,
			String address, String traffic, String photo, String saasId,
			String longitude, String latitude, String legalperson,
			String parentCode, String combinationCode, String orgUrl,
			String del, String intro, Timestamp createTime, Timestamp updateTime) {
		this.id = id;
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
	}

	public void setId(String id) {
		this.id = id;
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


}