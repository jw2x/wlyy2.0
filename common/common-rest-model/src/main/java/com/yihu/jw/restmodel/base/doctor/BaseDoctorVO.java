package com.yihu.jw.restmodel.base.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 医生基本信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseDoctorVO", description = "医生基本信息")
public class BaseDoctorVO extends UuidIdentityVOWithOperator {

    /**
	 * 机构id
	 */
	@ApiModelProperty(value = "机构id", example = "q8q39888jojuuasdnfadf8j8h736ljv")
    private String orgId;

    /**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", example = "s9i8ujjhy7gtgf5e455asddddda33eedbbfsq")
    private String password;

    /**
	 * 
	 */
	@ApiModelProperty(value = "", example = "")
    private String salt;

    /**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    /**
	 * 性别（1男，2女） 用国家标准字典
	 */
	@ApiModelProperty(value = "性别（1男，2女） 用国家标准字典", example = "1")
    private String sex;

    /**
	 * 医生专长
	 */
	@ApiModelProperty(value = "医生专长", example = "擅长....")
    private String expertise;

    /**
	 * 医生介绍
	 */
	@ApiModelProperty(value = "医生介绍", example = "....")
    private String introduce;

    /**
	 *  身份证
	 */
	@ApiModelProperty(value = " 身份证", example = "350.....")
    private String idcard;

    /**
	 * 生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "生日", example = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    /**
	 * 头像http地址
	 */
	@ApiModelProperty(value = "头像http地址", example = "")
    private String photo;

    /**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号", example = "")
    private String mobile;

    /**
	 * 医生二维码
	 */
	@ApiModelProperty(value = "医生二维码", example = "")
    private String qrcode;

    /**
	 * 省代码
	 */
	@ApiModelProperty(value = "省代码", example = "参考省代码")
    private String provinceCode;

    /**
	 * 省名称
	 */
	@ApiModelProperty(value = "省名称", example = "某某省")
    private String provinceName;

    /**
	 * 市代码
	 */
	@ApiModelProperty(value = "市代码", example = "参考市代码")
    private String cityCode;

    /**
	 * 市名称
	 */
	@ApiModelProperty(value = "市名称", example = "某某市")
    private String cityName;

    /**
	 * 区县代码
	 */
	@ApiModelProperty(value = "区县代码", example = "参考区县代码")
    private String townCode;

    /**
	 * 区县名称
	 */
	@ApiModelProperty(value = "区县名称", example = "某某区县")
    private String townName;

    /**
	 * 街道代码
	 */
	@ApiModelProperty(value = "街道代码", example = "参考街道代码")
    private String streetCode;

    /**
	 * 街道名称
	 */
	@ApiModelProperty(value = "街道名称", example = "某某街道")
    private String streetName;

    /**
	 * 资格是否认证通过，1是，0否
	 */
	@ApiModelProperty(value = "资格是否认证通过，1是，0否", example = "1")
    private String iscertified;

    /**
	 * 是否是名医，1是，0否
	 */
	@ApiModelProperty(value = "是否是名医，1是，0否", example = "0")
    private String isFamous;

    /**
	 * 是否提示设置密码  1 提示过 0未提示
	 */
	@ApiModelProperty(value = "是否提示设置密码  1 提示过 0未提示", example = "0")
    private String isPasswordPrompt;

    /**
	 * 名称拼音首字母
	 */
	@ApiModelProperty(value = "名称拼音首字母", example = "")
    private String spell;

    /**
	 * CA证书过期时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "CA证书过期时间", example = "yyyy-MM-dd HH:mm:ss")
    private Date certifiedOvertime;

    /**
	 * CA证书编号
	 */
	@ApiModelProperty(value = "CA证书编号", example = "实际证书编号")
    private String certificateNum;

    /**
	 * 用户微信openid
	 */
	@ApiModelProperty(value = "用户微信openid", example = "填上微信那边分配给用户的")
    private String openid;

    /**
	 * 作废标识，1正常，0作废
	 */
	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "1")
    private String del;


    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExpertise() {
        return expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
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

    public String getIscertified() {
        return iscertified;
    }
    public void setIscertified(String iscertified) {
        this.iscertified = iscertified;
    }

    public String getIsFamous() {
        return isFamous;
    }
    public void setIsFamous(String isFamous) {
        this.isFamous = isFamous;
    }

    public String getIsPasswordPrompt() {
        return isPasswordPrompt;
    }
    public void setIsPasswordPrompt(String isPasswordPrompt) {
        this.isPasswordPrompt = isPasswordPrompt;
    }

    public String getSpell() {
        return spell;
    }
    public void setSpell(String spell) {
        this.spell = spell;
    }

    public Date getCertifiedOvertime() {
        return certifiedOvertime;
    }
    public void setCertifiedOvertime(Date certifiedOvertime) {
        this.certifiedOvertime = certifiedOvertime;
    }

    public String getCertificateNum() {
        return certificateNum;
    }
    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }


}