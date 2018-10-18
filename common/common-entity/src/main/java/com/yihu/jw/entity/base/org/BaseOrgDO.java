package com.yihu.jw.entity.base.org;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* 机构信息（医院）实体
*
* @author Administrator on  2018年09月05日
*
*/
@Entity
@Table(name = "base_org")
public class BaseOrgDO extends UuidIdentityEntityWithOperator {

    /**
	 * saas化配置
	 */
	private String saasid;

    /**
     * 机构代码
     */
    private String code;

    /**
     * 省代码
     */
    private String provinceCode;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市代码
     */
    private String cityCode;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区县代码
     */
    private String townCode;

    /**
     * 区县名称
     */
    private String townName;

    /**
     * 街道代码
     */
    private String streetCode;

    /**
     * 街道名称
     */
    private String streetName;

    /**
	 * 机构名称
	 */
	private String name;

    /**
	 * 机构别名
	 */
	private String alias;

    /**
	 * 机构名称拼音首字母
	 */
	private String spell;

    /**
	 * 机构类型: 1.  医疗机构2.  企事业单位3.  政府机关4.  社会团体 5.药店 0.  部门 6.单位或者独立子公司7.基层机构 8.专业公共机构
	 */
	private String type;

    /**
	 * 机构简介
	 */
	private String brief;

    /**
	 * 机构详细地址
	 */
	private String address;

    /**
	 * 机构图片
	 */
	private String photo;

    /**
	 * 经度
	 */
	private String longitude;

    /**
	 * 纬度
	 */
	private String latitude;

    /**
	 * 法人
	 */
	private String legalperson;

    /**
	 * 机构管理员
	 */
	private String orgAdmin;

    /**
	 * 机构网址
	 */
	private String orgUrl;

    /**
	 * 机构简介
	 */
	private String intro;

    /**
	 * 机构二维码
	 */
	private String qrcode;

    /**
	 * 作废标识，1正常，0作废
	 */
	private String del;

    public BaseOrgDO() {
    }

    public BaseOrgDO(String provinceCode, String provinceName, String cityCode, String cityName, String townCode, String townName, String code, String name ){
	    this.provinceCode = provinceCode;
	    this.provinceName = provinceName;
	    this.cityCode = cityCode;
	    this.cityName = cityName;
	    this.townCode = townCode;
	    this.townName = townName;
	    this.code = code;
	    this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "saasid")
    public String getSaasid() {
        return saasid;
    }
    public void setSaasid(String saasid) {
        this.saasid = saasid;
    }

	@Column(name = "province_code")
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

	@Column(name = "city_code")
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

	@Column(name = "town_code")
    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "alias")
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

	@Column(name = "spell")
    public String getSpell() {
        return spell;
    }
    public void setSpell(String spell) {
        this.spell = spell;
    }

	@Column(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

	@Column(name = "brief")
    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }

	@Column(name = "address")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

	@Column(name = "photo")
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

	@Column(name = "longitude")
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

	@Column(name = "latitude")
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

	@Column(name = "legalperson")
    public String getLegalperson() {
        return legalperson;
    }
    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }

	@Column(name = "org_admin")
    public String getOrgAdmin() {
        return orgAdmin;
    }
    public void setOrgAdmin(String orgAdmin) {
        this.orgAdmin = orgAdmin;
    }

	@Column(name = "org_url")
    public String getOrgUrl() {
        return orgUrl;
    }
    public void setOrgUrl(String orgUrl) {
        this.orgUrl = orgUrl;
    }

	@Column(name = "intro")
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }

	@Column(name = "qrcode")
    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

	@Column(name = "del")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}