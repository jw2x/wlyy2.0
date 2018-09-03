package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 机构信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseOrgVO", description = "机构信息")
public class BaseOrgVO extends UuidIdentityVOWithOperator {

    /**
	saas化配置	*/
    private String saasid;
    /**
	省份标识	*/
    private String provinceCode;
    /**
	城市标识	*/
    private String cityCode;
    /**
	区县标识	*/
    private String townCode;
    /**
	机构名称	*/
    private String name;
    /**
	机构别名	*/
    private String alias;
    /**
	机构名称拼音首字母	*/
    private String spell;
    /**
	机构类型: 1.  医疗机构2.  企事业单位3.  政府机关4.  社会团体 5.药店 0.  部门 6.单位或者独立子公司7.基层机构 8.专业公共机构	*/
    private String type;
    /**
	机构简介	*/
    private String brief;
    /**
	机构详细地址	*/
    private String address;
    /**
	机构图片	*/
    private String photo;
    /**
	经度	*/
    private String longitude;
    /**
	纬度	*/
    private String latitude;
    /**
	法人	*/
    private String legalperson;
    /**
	机构管理员	*/
    private String orgAdmin;
    /**
	机构网址	*/
    private String orgUrl;
    /**
	机构简介	*/
    private String intro;
    /**
	机构二维码	*/
    private String qrcode;
    /**
	作废标识，1正常，0作废	*/
    private String del;

	@ApiModelProperty(value = "saas化配置", example = "模块1")
    public String getSaasid() {
        return saasid;
    }
    public void setSaasid(String saasid) {
        this.saasid = saasid;
    }

	@ApiModelProperty(value = "省份标识", example = "模块1")
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

	@ApiModelProperty(value = "城市标识", example = "模块1")
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

	@ApiModelProperty(value = "区县标识", example = "模块1")
    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

	@ApiModelProperty(value = "机构名称", example = "模块1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@ApiModelProperty(value = "机构别名", example = "模块1")
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

	@ApiModelProperty(value = "机构名称拼音首字母", example = "模块1")
    public String getSpell() {
        return spell;
    }
    public void setSpell(String spell) {
        this.spell = spell;
    }

	@ApiModelProperty(value = "机构类型: 1.  医疗机构2.  企事业单位3.  政府机关4.  社会团体 5.药店 0.  部门 6.单位或者独立子公司7.基层机构 8.专业公共机构", example = "模块1")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

	@ApiModelProperty(value = "机构简介", example = "模块1")
    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }

	@ApiModelProperty(value = "机构详细地址", example = "模块1")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

	@ApiModelProperty(value = "机构图片", example = "模块1")
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

	@ApiModelProperty(value = "经度", example = "模块1")
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

	@ApiModelProperty(value = "纬度", example = "模块1")
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

	@ApiModelProperty(value = "法人", example = "模块1")
    public String getLegalperson() {
        return legalperson;
    }
    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }

	@ApiModelProperty(value = "机构管理员", example = "模块1")
    public String getOrgAdmin() {
        return orgAdmin;
    }
    public void setOrgAdmin(String orgAdmin) {
        this.orgAdmin = orgAdmin;
    }

	@ApiModelProperty(value = "机构网址", example = "模块1")
    public String getOrgUrl() {
        return orgUrl;
    }
    public void setOrgUrl(String orgUrl) {
        this.orgUrl = orgUrl;
    }

	@ApiModelProperty(value = "机构简介", example = "模块1")
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }

	@ApiModelProperty(value = "机构二维码", example = "模块1")
    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "模块1")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}