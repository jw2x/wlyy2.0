package com.yihu.jw.entity.base.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
* 医生基本信息实体
*
* @author Administrator on  2018年09月05日
*
*/
@Entity
@Table(name = "base_doctor")
@Where(clause = "del = 1")
public class BaseDoctorDO extends UuidIdentityEntityWithOperator {

    /**
	 * 密码
	 */
	private String password;

    /**
	 * 
	 */
	private String salt;

    /**
	 * 姓名
	 */
	private String name;

    /**
	 * 性别（1男，2女） 用国家标准字典
	 */
	private Integer sex;

    /**
	 * 医生专长
	 */
	private String expertise;

    /**
	 * 医生介绍
	 */
	private String introduce;

    /**
	 *  身份证
	 */
	private String idcard;

    /**
	 * 生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date birthday;

    /**
	 * 头像http地址
	 */
	private String photo;

    /**
	 * 手机号
	 */
	private String mobile;

    /**
	 * 医生二维码
	 */
	private String qrcode;

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
	 * 资格是否认证通过，1是，0否
	 */
	private String iscertified;

    /**
	 * 是否是名医，1是，0否
	 */
	private Integer isFamous;

    /**
	 * 是否提示设置密码  1 提示过 0未提示
	 */
	private String isPasswordPrompt;

    /**
	 * 名称拼音首字母
	 */
	private String spell;

    /**
	 * CA证书过期时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date certifiedOvertime;

    /**
	 * CA证书编号
	 */
	private String certificateNum;

    /**
	 * 用户微信openid
	 */
	private String openid;

	/**
	 * 职称代码
	 */
	private String jobTitleCode;

	/**
	 * 职称名称
	 */
	private String jobTitleName;

    /**
	 * 作废标识，1正常，0作废
	 */
	private String del;

	@Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

	@Column(name = "salt")
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

	@Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "sex")
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }

	@Column(name = "expertise")
    public String getExpertise() {
        return expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

	@Column(name = "introduce")
    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

	@Column(name = "idcard")
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

	@Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

	@Column(name = "photo")
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

	@Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	@Column(name = "qrcode")
    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

	@Column(name = "province_code")
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

	@Column(name = "province_name")
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

	@Column(name = "city_code")
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

	@Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

	@Column(name = "town_code")
    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

	@Column(name = "town_name")
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }

	@Column(name = "street_code")
    public String getStreetCode() {
        return streetCode;
    }
    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

	@Column(name = "street_name")
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

	@Column(name = "iscertified")
    public String getIscertified() {
        return iscertified;
    }
    public void setIscertified(String iscertified) {
        this.iscertified = iscertified;
    }

	@Column(name = "is_famous")
    public Integer getIsFamous() {
        return isFamous;
    }
    public void setIsFamous(Integer isFamous) {
        this.isFamous = isFamous;
    }

	@Column(name = "is_password_prompt")
    public String getIsPasswordPrompt() {
        return isPasswordPrompt;
    }
    public void setIsPasswordPrompt(String isPasswordPrompt) {
        this.isPasswordPrompt = isPasswordPrompt;
    }

	@Column(name = "spell")
    public String getSpell() {
        return spell;
    }
    public void setSpell(String spell) {
        this.spell = spell;
    }

	@Column(name = "certified_overtime")
    public Date getCertifiedOvertime() {
        return certifiedOvertime;
    }
    public void setCertifiedOvertime(Date certifiedOvertime) {
        this.certifiedOvertime = certifiedOvertime;
    }

	@Column(name = "certificate_num")
    public String getCertificateNum() {
        return certificateNum;
    }
    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

	@Column(name = "openid")
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

	@Column(name = "del")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

    public String getJobTitleCode() {
        return jobTitleCode;
    }

    public void setJobTitleCode(String jobTitleCode) {
        this.jobTitleCode = jobTitleCode;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }
}