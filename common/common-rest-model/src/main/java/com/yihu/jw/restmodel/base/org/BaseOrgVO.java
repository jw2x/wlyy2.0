package com.yihu.jw.restmodel.base.org;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 机构信息（医院）vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseOrgVO", description = "机构信息（医院）")
public class BaseOrgVO extends UuidIdentityVOWithOperator {

    /**
     * 机构代码
     */
    @ApiModelProperty(value = "机构代码", example = "DGREFVDVD")
    private String code;


    /**
	 * saas化配置
	 */
	@ApiModelProperty(value = "saas化配置", example = "402803ee656498890165649ad2da1112")
    private String saasid;

    /**
	 * 省份标识
	 */
	@ApiModelProperty(value = "省份标识", example = "参考省代码")
    private String provinceCode;

    /**
	 * 城市标识
	 */
	@ApiModelProperty(value = "城市标识", example = "参考城市代码")
    private String cityCode;

    /**
	 * 区县标识
	 */
	@ApiModelProperty(value = "区县标识", example = "参考区县代码")
    private String townCode;

    /**
	 * 机构名称
	 */
	@ApiModelProperty(value = "机构名称", example = "")
    private String name;

    /**
	 * 机构别名
	 */
	@ApiModelProperty(value = "机构别名", example = "")
    private String alias;

    /**
	 * 机构名称拼音首字母
	 */
	@ApiModelProperty(value = "机构名称拼音首字母", example = "")
    private String spell;

    /**
	 * 机构类型: 1.  医疗机构2.  企事业单位3.  政府机关4.  社会团体 5.药店 0.  部门 6.单位或者独立子公司7.基层机构 8.专业公共机构
	 */
	@ApiModelProperty(value = "机构类型: 1.  医疗机构2.  企事业单位3.  政府机关4.  社会团体 5.药店 0.  部门 6.单位或者独立子公司7.基层机构 8.专业公共机构", example = "1")
    private String type;

    /**
	 * 机构简介
	 */
	@ApiModelProperty(value = "机构简介", example = "")
    private String brief;

    /**
	 * 机构详细地址
	 */
	@ApiModelProperty(value = "机构详细地址", example = "")
    private String address;

    /**
	 * 机构图片
	 */
	@ApiModelProperty(value = "机构图片", example = "")
    private String photo;

    /**
	 * 经度
	 */
	@ApiModelProperty(value = "经度", example = "")
    private String longitude;

    /**
	 * 纬度
	 */
	@ApiModelProperty(value = "纬度", example = "")
    private String latitude;

    /**
	 * 法人
	 */
	@ApiModelProperty(value = "法人", example = "")
    private String legalperson;

    /**
	 * 机构管理员
	 */
	@ApiModelProperty(value = "机构管理员", example = "")
    private String orgAdmin;

    /**
	 * 机构网址
	 */
	@ApiModelProperty(value = "机构网址", example = "")
    private String orgUrl;

    /**
	 * 机构简介
	 */
	@ApiModelProperty(value = "机构简介", example = "")
    private String intro;

    /**
	 * 机构二维码
	 */
	@ApiModelProperty(value = "机构二维码", example = "")
    private String qrcode;

    /**
	 * 作废标识，1正常，0作废
	 */
	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "1")
    private String del;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSaasid() {
        return saasid;
    }
    public void setSaasid(String saasid) {
        this.saasid = saasid;
    }

    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSpell() {
        return spell;
    }
    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLegalperson() {
        return legalperson;
    }
    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }

    public String getOrgAdmin() {
        return orgAdmin;
    }
    public void setOrgAdmin(String orgAdmin) {
        this.orgAdmin = orgAdmin;
    }

    public String getOrgUrl() {
        return orgUrl;
    }
    public void setOrgUrl(String orgUrl) {
        this.orgUrl = orgUrl;
    }

    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }


}