package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 医生基础信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseDoctorVO", description = "医生基础信息")
public class BaseDoctorVO extends UuidIdentityVOWithOperator {

    /**
	机构id	*/
    private String orgId;
    /**
	密码	*/
    private String password;
    /**
		*/
    private String salt;
    /**
	姓名	*/
    private String name;
    /**
	性别（1男，2女） 用国家标准字典	*/
    private String sex;
    /**
	医生专长	*/
    private String expertise;
    /**
	医生介绍	*/
    private String introduce;
    /**
	 身份证	*/
    private String idcard;
    /**
	生日	*/
    private Date birthday;
    /**
	头像http地址	*/
    private String photo;
    /**
	手机号	*/
    private String mobile;
    /**
	医生二维码	*/
    private String qrcode;
    /**
	省代码	*/
    private String provinceCode;
    /**
	省名称	*/
    private String provinceName;
    /**
	市代码	*/
    private String cityCode;
    /**
	市名称	*/
    private String cityName;
    /**
	区县代码	*/
    private String townCode;
    /**
	区县名称	*/
    private String townName;
    /**
	街道代码	*/
    private String streetCode;
    /**
	街道名称	*/
    private String streetName;
    /**
	资格是否认证通过，1是，0否	*/
    private String iscertified;
    /**
	是否是名医，1是，0否	*/
    private String isFamous;
    /**
	是否提示设置密码  1 提示过 0未提示	*/
    private String isPasswordPrompt;
    /**
	名称拼音首字母	*/
    private String spell;
    /**
	CA证书过期时间	*/
    private Date certifiedOvertime;
    /**
	CA证书编号	*/
    private String certificateNum;
    /**
		*/
    private String openid;
    /**
	作废标识，1正常，0作废	*/
    private String del;

	@ApiModelProperty(value = "机构id", example = "模块1")
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

	@ApiModelProperty(value = "密码", example = "模块1")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

	@ApiModelProperty(value = "", example = "模块1")
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

	@ApiModelProperty(value = "姓名", example = "模块1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@ApiModelProperty(value = "性别（1男，2女） 用国家标准字典", example = "模块1")
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

	@ApiModelProperty(value = "医生专长", example = "模块1")
    public String getExpertise() {
        return expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

	@ApiModelProperty(value = "医生介绍", example = "模块1")
    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

	@ApiModelProperty(value = " 身份证", example = "模块1")
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

	@ApiModelProperty(value = "生日", example = "模块1")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

	@ApiModelProperty(value = "头像http地址", example = "模块1")
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

	@ApiModelProperty(value = "手机号", example = "模块1")
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	@ApiModelProperty(value = "医生二维码", example = "模块1")
    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

	@ApiModelProperty(value = "省代码", example = "模块1")
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

	@ApiModelProperty(value = "省名称", example = "模块1")
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

	@ApiModelProperty(value = "市代码", example = "模块1")
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

	@ApiModelProperty(value = "市名称", example = "模块1")
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

	@ApiModelProperty(value = "区县代码", example = "模块1")
    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

	@ApiModelProperty(value = "区县名称", example = "模块1")
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }

	@ApiModelProperty(value = "街道代码", example = "模块1")
    public String getStreetCode() {
        return streetCode;
    }
    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

	@ApiModelProperty(value = "街道名称", example = "模块1")
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

	@ApiModelProperty(value = "资格是否认证通过，1是，0否", example = "模块1")
    public String getIscertified() {
        return iscertified;
    }
    public void setIscertified(String iscertified) {
        this.iscertified = iscertified;
    }

	@ApiModelProperty(value = "是否是名医，1是，0否", example = "模块1")
    public String getIsFamous() {
        return isFamous;
    }
    public void setIsFamous(String isFamous) {
        this.isFamous = isFamous;
    }

	@ApiModelProperty(value = "是否提示设置密码  1 提示过 0未提示", example = "模块1")
    public String getIsPasswordPrompt() {
        return isPasswordPrompt;
    }
    public void setIsPasswordPrompt(String isPasswordPrompt) {
        this.isPasswordPrompt = isPasswordPrompt;
    }

	@ApiModelProperty(value = "名称拼音首字母", example = "模块1")
    public String getSpell() {
        return spell;
    }
    public void setSpell(String spell) {
        this.spell = spell;
    }

	@ApiModelProperty(value = "CA证书过期时间", example = "模块1")
    public Date getCertifiedOvertime() {
        return certifiedOvertime;
    }
    public void setCertifiedOvertime(Date certifiedOvertime) {
        this.certifiedOvertime = certifiedOvertime;
    }

	@ApiModelProperty(value = "CA证书编号", example = "模块1")
    public String getCertificateNum() {
        return certificateNum;
    }
    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

	@ApiModelProperty(value = "", example = "模块1")
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "模块1")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}