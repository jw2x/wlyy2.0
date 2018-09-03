package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 居民信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BasePatientVO", description = "居民信息")
public class BasePatientVO extends UuidIdentityVOWithOperator {

    /**
	saas配置id	*/
    private String saasId;
    /**
	身份证号	*/
    private String idcard;
    /**
	登录密码	*/
    private String password;
    /**
		*/
    private String salt;
    /**
	姓名	*/
    private String name;
    /**
	生日	*/
    private Date birthday;
    /**
	性别，1男，2女	*/
    private String sex;
    /**
	手机号	*/
    private String mobile;
    /**
	联系电话	*/
    private String phone;
    /**
	社保卡号	*/
    private String ssc;
    /**
	头像http地址	*/
    private String photo;
    /**
	省编码	*/
    private String provinceCode;
    /**
	市编码	*/
    private String cityCode;
    /**
	区县编码	*/
    private String townCode;
    /**
	街道编码	*/
    private String streetCode;
    /**
	具体详细地址	*/
    private String address;
    /**
	疾病类型，0健康，1高血压，2糖尿病，3高血压+糖尿病	*/
    private String disease;
    /**
	病情：0绿标，1黄标，2红标，3重点关注,	*/
    private String diseaseCondition;
    /**
	总积分	*/
    private String points;
    /**
	病历总数	*/
    private String recordAmount;
    /**
	微信编号	*/
    private String openid;
    /**
	用户状态：1正常，0禁用，-1恶意注册，2审核中	*/
    private String patientStatus;
    /**
	联系方式备注【基卫】	*/
    private String mobileRemarks;
    /**
	第一次添加open的时间	*/
    private Date openidTime;
    /**
	居委会代码	*/
    private String sickVillage;
    /**
		*/
    private String sickVillageName;
    /**
	绑定电子社保卡主体（共济为操作人code）	*/
    private String principalCode;
    /**
	是否绑定电子社保卡 （0否 1是）	*/
    private String sicardStatus;
    /**
	电子社保卡绑定时间	*/
    private Date sicardTime;
    /**
	是否分配过微信标签	*/
    private Integer isWxtag;
    /**
	微信tagId	*/
    private String wxtagid;
    /**
	居民预警状态：0为标准，1为预警状态	*/
    private Integer standardStatus;
    /**
	医疗保险号	*/
    private String medicareNumber;
    /**
	unionId 开发平台唯一标识	*/
    private String unionid;
    /**
	作废标识，1正常，0作废	*/
    private String del;

	@ApiModelProperty(value = "saas配置id", example = "模块1")
    public String getSaasId() {
        return saasId;
    }
    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

	@ApiModelProperty(value = "身份证号", example = "模块1")
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

	@ApiModelProperty(value = "登录密码", example = "模块1")
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

	@ApiModelProperty(value = "生日", example = "模块1")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

	@ApiModelProperty(value = "性别，1男，2女", example = "模块1")
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

	@ApiModelProperty(value = "手机号", example = "模块1")
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	@ApiModelProperty(value = "联系电话", example = "模块1")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

	@ApiModelProperty(value = "社保卡号", example = "模块1")
    public String getSsc() {
        return ssc;
    }
    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

	@ApiModelProperty(value = "头像http地址", example = "模块1")
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

	@ApiModelProperty(value = "省编码", example = "模块1")
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

	@ApiModelProperty(value = "市编码", example = "模块1")
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

	@ApiModelProperty(value = "区县编码", example = "模块1")
    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

	@ApiModelProperty(value = "街道编码", example = "模块1")
    public String getStreetCode() {
        return streetCode;
    }
    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

	@ApiModelProperty(value = "具体详细地址", example = "模块1")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

	@ApiModelProperty(value = "疾病类型，0健康，1高血压，2糖尿病，3高血压+糖尿病", example = "模块1")
    public String getDisease() {
        return disease;
    }
    public void setDisease(String disease) {
        this.disease = disease;
    }

	@ApiModelProperty(value = "病情：0绿标，1黄标，2红标，3重点关注,", example = "模块1")
    public String getDiseaseCondition() {
        return diseaseCondition;
    }
    public void setDiseaseCondition(String diseaseCondition) {
        this.diseaseCondition = diseaseCondition;
    }

	@ApiModelProperty(value = "总积分", example = "模块1")
    public String getPoints() {
        return points;
    }
    public void setPoints(String points) {
        this.points = points;
    }

	@ApiModelProperty(value = "病历总数", example = "模块1")
    public String getRecordAmount() {
        return recordAmount;
    }
    public void setRecordAmount(String recordAmount) {
        this.recordAmount = recordAmount;
    }

	@ApiModelProperty(value = "微信编号", example = "模块1")
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

	@ApiModelProperty(value = "用户状态：1正常，0禁用，-1恶意注册，2审核中", example = "模块1")
    public String getPatientStatus() {
        return patientStatus;
    }
    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

	@ApiModelProperty(value = "联系方式备注【基卫】", example = "模块1")
    public String getMobileRemarks() {
        return mobileRemarks;
    }
    public void setMobileRemarks(String mobileRemarks) {
        this.mobileRemarks = mobileRemarks;
    }

	@ApiModelProperty(value = "第一次添加open的时间", example = "模块1")
    public Date getOpenidTime() {
        return openidTime;
    }
    public void setOpenidTime(Date openidTime) {
        this.openidTime = openidTime;
    }

	@ApiModelProperty(value = "居委会代码", example = "模块1")
    public String getSickVillage() {
        return sickVillage;
    }
    public void setSickVillage(String sickVillage) {
        this.sickVillage = sickVillage;
    }

	@ApiModelProperty(value = "", example = "模块1")
    public String getSickVillageName() {
        return sickVillageName;
    }
    public void setSickVillageName(String sickVillageName) {
        this.sickVillageName = sickVillageName;
    }

	@ApiModelProperty(value = "绑定电子社保卡主体（共济为操作人code）", example = "模块1")
    public String getPrincipalCode() {
        return principalCode;
    }
    public void setPrincipalCode(String principalCode) {
        this.principalCode = principalCode;
    }

	@ApiModelProperty(value = "是否绑定电子社保卡 （0否 1是）", example = "模块1")
    public String getSicardStatus() {
        return sicardStatus;
    }
    public void setSicardStatus(String sicardStatus) {
        this.sicardStatus = sicardStatus;
    }

	@ApiModelProperty(value = "电子社保卡绑定时间", example = "模块1")
    public Date getSicardTime() {
        return sicardTime;
    }
    public void setSicardTime(Date sicardTime) {
        this.sicardTime = sicardTime;
    }

	@ApiModelProperty(value = "是否分配过微信标签", example = "模块1")
    public Integer getIsWxtag() {
        return isWxtag;
    }
    public void setIsWxtag(Integer isWxtag) {
        this.isWxtag = isWxtag;
    }

	@ApiModelProperty(value = "微信tagId", example = "模块1")
    public String getWxtagid() {
        return wxtagid;
    }
    public void setWxtagid(String wxtagid) {
        this.wxtagid = wxtagid;
    }

	@ApiModelProperty(value = "居民预警状态：0为标准，1为预警状态", example = "模块1")
    public Integer getStandardStatus() {
        return standardStatus;
    }
    public void setStandardStatus(Integer standardStatus) {
        this.standardStatus = standardStatus;
    }

	@ApiModelProperty(value = "医疗保险号", example = "模块1")
    public String getMedicareNumber() {
        return medicareNumber;
    }
    public void setMedicareNumber(String medicareNumber) {
        this.medicareNumber = medicareNumber;
    }

	@ApiModelProperty(value = "unionId 开发平台唯一标识", example = "模块1")
    public String getUnionid() {
        return unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "模块1")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}