package com.yihu.jw.entity.base.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
* 居民信息（居民就是患者）实体
*
* @author Administrator on  2018年09月05日
*
*/
@Entity
@Table(name = "base_patient")
public class BasePatientDO extends UuidIdentityEntityWithOperator {

    /**
	 * saas配置id
	 */
	private String saasId;

    /**
	 * 身份证号
	 */
	private String idcard;

    /**
	 * 登录密码
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
	 * 生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date birthday;

    /**
	 * 性别，1男，2女
	 */
	private Integer sex;

    /**
	 * 手机号
	 */
	private String mobile;

    /**
	 * 联系电话
	 */
	private String phone;

    /**
	 * 社保卡号
	 */
	private String ssc;

    /**
	 * 头像http地址
	 */
	private String photo;

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
     * 居住省代码
     */
    private String liveProvinceCode;


    /**
     * 居住省名称
     */
    private String liveProvinceName;

    /**
     * 居住市代码
     */
    private String liveCityCode;

    /**
     * 居住市名称
     */
    private String liveCityName;

    /**
     * 居住区县代码
     */
    private String liveTownCode;

    /**
     * 居住区县名称
     */
    private String liveTownName;

    /**
     * 居住居委会代码
     */
    private String committeeCode;

    /**
     * 居住居委会名称
     */
    private String committeeName;


    /**
	 * 疾病类型，0健康，1高血压，2糖尿病，3高血压+糖尿病
	 */
	private String disease;

    /**
	 * 病情：0绿标，1黄标，2红标，3重点关注,
	 */
	private String diseaseCondition;

    /**
	 * 总积分
	 */
	private String points;

    /**
	 * 病历总数
	 */
	private String recordAmount;

    /**
	 * 微信编号
	 */
	private String openid;

    /**
	 * 用户状态：1正常，0禁用，-1恶意注册，2审核中
	 */
	private String patientStatus;

    /**
	 * 联系方式备注【基卫】
	 */
	private String mobileRemarks;

    /**
	 * 第一次添加open的时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date openidTime;

    /**
	 * 绑定电子社保卡主体（共济为操作人code）
	 */
	private String principalCode;

    /**
	 * 是否绑定电子社保卡 （0否 1是）
	 */
	private String sicardStatus;

    /**
	 * 电子社保卡绑定时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date sicardTime;

    /**
	 * 是否分配过微信标签
	 */
	private Integer isWxtag;

    /**
	 * 微信tagId
	 */
	private String wxtagid;

    /**
	 * 居民预警状态：0为标准，1为预警状态
	 */
	private Integer standardStatus;

    /**
	 * 医疗保险号
	 */
	private String medicareNumber;

    /**
	 * unionId 开发平台唯一标识
	 */
	private String unionid;

    /**
	 * 作废标识，1正常，0作废
	 */
	private String del;

	/**
	 * 备注
	 */
	private String remark;


	@Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }
    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

	@Column(name = "idcard")
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

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

	@Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

	@Column(name = "sex")
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }

	@Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	@Column(name = "phone")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

	@Column(name = "ssc")
    public String getSsc() {
        return ssc;
    }
    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

	@Column(name = "photo")
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getCommitteeCode() {
        return committeeCode;
    }

    public void setCommitteeCode(String committeeCode) {
        this.committeeCode = committeeCode;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    @Column(name = "disease")
    public String getDisease() {
        return disease;
    }
    public void setDisease(String disease) {
        this.disease = disease;
    }

	@Column(name = "disease_condition")
    public String getDiseaseCondition() {
        return diseaseCondition;
    }
    public void setDiseaseCondition(String diseaseCondition) {
        this.diseaseCondition = diseaseCondition;
    }

	@Column(name = "points")
    public String getPoints() {
        return points;
    }
    public void setPoints(String points) {
        this.points = points;
    }

	@Column(name = "record_amount")
    public String getRecordAmount() {
        return recordAmount;
    }
    public void setRecordAmount(String recordAmount) {
        this.recordAmount = recordAmount;
    }

	@Column(name = "openid")
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

	@Column(name = "patient_status")
    public String getPatientStatus() {
        return patientStatus;
    }
    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

	@Column(name = "mobile_remarks")
    public String getMobileRemarks() {
        return mobileRemarks;
    }
    public void setMobileRemarks(String mobileRemarks) {
        this.mobileRemarks = mobileRemarks;
    }

	@Column(name = "openid_time")
    public Date getOpenidTime() {
        return openidTime;
    }
    public void setOpenidTime(Date openidTime) {
        this.openidTime = openidTime;
    }

	@Column(name = "principal_code")
    public String getPrincipalCode() {
        return principalCode;
    }
    public void setPrincipalCode(String principalCode) {
        this.principalCode = principalCode;
    }

	@Column(name = "sicard_status")
    public String getSicardStatus() {
        return sicardStatus;
    }
    public void setSicardStatus(String sicardStatus) {
        this.sicardStatus = sicardStatus;
    }

	@Column(name = "sicard_time")
    public Date getSicardTime() {
        return sicardTime;
    }
    public void setSicardTime(Date sicardTime) {
        this.sicardTime = sicardTime;
    }

	@Column(name = "is_wxtag")
    public Integer getIsWxtag() {
        return isWxtag;
    }
    public void setIsWxtag(Integer isWxtag) {
        this.isWxtag = isWxtag;
    }

	@Column(name = "wxtagid")
    public String getWxtagid() {
        return wxtagid;
    }
    public void setWxtagid(String wxtagid) {
        this.wxtagid = wxtagid;
    }

	@Column(name = "standard_status")
    public Integer getStandardStatus() {
        return standardStatus;
    }
    public void setStandardStatus(Integer standardStatus) {
        this.standardStatus = standardStatus;
    }

	@Column(name = "medicare_number")
    public String getMedicareNumber() {
        return medicareNumber;
    }
    public void setMedicareNumber(String medicareNumber) {
        this.medicareNumber = medicareNumber;
    }

	@Column(name = "unionid")
    public String getUnionid() {
        return unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

	@Column(name = "del")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

    public String getRemark() {
        return remark;
    }
    @Column(name = "remark")
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLiveProvinceCode() {
        return liveProvinceCode;
    }

    public void setLiveProvinceCode(String liveProvinceCode) {
        this.liveProvinceCode = liveProvinceCode;
    }

    public String getLiveProvinceName() {
        return liveProvinceName;
    }

    public void setLiveProvinceName(String liveProvinceName) {
        this.liveProvinceName = liveProvinceName;
    }

    public String getLiveCityCode() {
        return liveCityCode;
    }

    public void setLiveCityCode(String liveCityCode) {
        this.liveCityCode = liveCityCode;
    }

    public String getLiveCityName() {
        return liveCityName;
    }

    public void setLiveCityName(String liveCityName) {
        this.liveCityName = liveCityName;
    }

    public String getLiveTownCode() {
        return liveTownCode;
    }

    public void setLiveTownCode(String liveTownCode) {
        this.liveTownCode = liveTownCode;
    }

    public String getLiveTownName() {
        return liveTownName;
    }

    public void setLiveTownName(String liveTownName) {
        this.liveTownName = liveTownName;
    }
}