package com.yihu.jw.restmodel.base.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 居民信息（居民就是患者）vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BasePatientVO", description = "居民信息（居民就是患者）")
public class BasePatientVO extends UuidIdentityVOWithOperator {

    /**
	 * saas配置id
	 */
	@ApiModelProperty(value = "saas配置id", example = "402803ee656498890165649ad2da1112")
    private String saasId;

    /**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号", example = "18位身份证号")
    private String idcard;

    /**
	 * 登录密码
	 */
	@ApiModelProperty(value = "登录密码", example = "dfsvgre23223dec343434")
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
	 * 生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "生日", example = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    /**
	 * 性别，1男，2女
	 */
	@ApiModelProperty(value = "性别，1男，2女", example = "1")
    private Integer sex;

    /**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号", example = "")
    private String mobile;

    /**
	 * 联系电话
	 */
	@ApiModelProperty(value = "联系电话", example = "")
    private String phone;

    /**
	 * 社保卡号
	 */
	@ApiModelProperty(value = "社保卡号", example = "")
    private String ssc;

    /**
	 * 头像http地址
	 */
	@ApiModelProperty(value = "头像http地址", example = "")
    private String photo;

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
     * 居住省代码
     */
    @ApiModelProperty(value = "居住省名称", example = "参考省代码")
    private String liveProvinceCode;


    /**
     * 居住省名称
     */
    @ApiModelProperty(value = "居住省名称", example = "参考省名称")
    private String liveProvinceName;

    /**
     * 居住市代码
     */
    @ApiModelProperty(value = "居住市代码", example = "参考市代码")
    private String liveCityCode;

    /**
     * 居住市名称
     */
    @ApiModelProperty(value = "居住市名称", example = "参考市名称")
    private String liveCityName;

    /**
     * 居住区县代码
     */
    @ApiModelProperty(value = "居住区县代码", example = "参考区县代码")
    private String liveTownCode;

    /**
     * 居住区县名称
     */
    @ApiModelProperty(value = "居住区县名称", example = "参考区县名称")
    private String liveTownName;

    /**
     * 居住街道代码
     */
    @ApiModelProperty(value = "居住街道代码", example = "参考居住街道代码")
    private String liveStreetCode;

    /**
     * 居住街道名称
     */
    @ApiModelProperty(value = "居住街道名称", example = "参考居住街道名称")
    private String liveStreetName;
    /**
     * 居委会代码
     */
    @ApiModelProperty(value = "居委会代码", example = "参考居委会代码")
    private String committeeCode;

    /**
     * 居委会名称
     */
    @ApiModelProperty(value = "居委会名称", example = "某某居委会")
    private String committeeName;

    /**
	 * 疾病类型，0健康，1高血压，2糖尿病，3高血压+糖尿病
	 */
	@ApiModelProperty(value = "疾病类型，0健康，1高血压，2糖尿病，3高血压+糖尿病", example = "0")
    private String disease;

    /**
	 * 病情：0绿标，1黄标，2红标，3重点关注,
	 */
	@ApiModelProperty(value = "病情：0绿标，1黄标，2红标，3重点关注,", example = "0")
    private String diseaseCondition;

    /**
	 * 总积分
	 */
	@ApiModelProperty(value = "总积分", example = "")
    private String points;

    /**
	 * 病历总数
	 */
	@ApiModelProperty(value = "病历总数", example = "")
    private String recordAmount;

    /**
	 * 微信编号
	 */
	@ApiModelProperty(value = "微信编号", example = "")
    private String openid;

    /**
	 * 用户状态：1正常，0禁用，-1恶意注册，2审核中
	 */
	@ApiModelProperty(value = "用户状态：1正常，0禁用，-1恶意注册，2审核中", example = "1")
    private String patientStatus;

    /**
	 * 联系方式备注【基卫】
	 */
	@ApiModelProperty(value = "联系方式备注【基卫】", example = "模块1")
    private String mobileRemarks;

    /**
	 * 第一次添加open的时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "第一次添加open的时间", example = "yyyy-MM-dd HH:mm:ss")
    private Date openidTime;

    /**
	 * 绑定电子社保卡主体（共济为操作人code）
	 */
	@ApiModelProperty(value = "绑定电子社保卡主体（共济为操作人code）", example = "")
    private String principalCode;

    /**
	 * 是否绑定电子社保卡 （0否 1是）
	 */
	@ApiModelProperty(value = "是否绑定电子社保卡 （0否 1是）", example = "1")
    private String sicardStatus;

    /**
	 * 电子社保卡绑定时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "电子社保卡绑定时间", example = "yyyy-MM-dd HH:mm:ss")
    private Date sicardTime;

    /**
	 * 是否分配过微信标签
	 */
	@ApiModelProperty(value = "是否分配过微信标签", example = "")
    private Integer isWxtag;

    /**
	 * 微信tagId
	 */
	@ApiModelProperty(value = "微信tagId", example = "")
    private String wxtagid;

    /**
	 * 居民预警状态：0为标准，1为预警状态
	 */
	@ApiModelProperty(value = "居民预警状态：0为标准，1为预警状态", example = "0")
    private Integer standardStatus;

    /**
	 * 医疗保险号
	 */
	@ApiModelProperty(value = "医疗保险号", example = "医保号码")
    private String medicareNumber;

    /**
	 * unionId 开发平台唯一标识
	 */
	@ApiModelProperty(value = "unionId 开发平台唯一标识", example = "")
    private String unionid;

    /**
	 * 作废标识，1正常，0作废
	 */
	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "1")
    private String del;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", example = "某某备注")
    private String remark;


    public String getSaasId() {
        return saasId;
    }
    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
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

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSsc() {
        return ssc;
    }
    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getStreetCode() {
        return streetCode;
    }
    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
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

    public String getDisease() {
        return disease;
    }
    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiseaseCondition() {
        return diseaseCondition;
    }
    public void setDiseaseCondition(String diseaseCondition) {
        this.diseaseCondition = diseaseCondition;
    }

    public String getPoints() {
        return points;
    }
    public void setPoints(String points) {
        this.points = points;
    }

    public String getRecordAmount() {
        return recordAmount;
    }
    public void setRecordAmount(String recordAmount) {
        this.recordAmount = recordAmount;
    }

    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPatientStatus() {
        return patientStatus;
    }
    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

    public String getMobileRemarks() {
        return mobileRemarks;
    }
    public void setMobileRemarks(String mobileRemarks) {
        this.mobileRemarks = mobileRemarks;
    }

    public Date getOpenidTime() {
        return openidTime;
    }
    public void setOpenidTime(Date openidTime) {
        this.openidTime = openidTime;
    }

    public String getPrincipalCode() {
        return principalCode;
    }
    public void setPrincipalCode(String principalCode) {
        this.principalCode = principalCode;
    }

    public String getSicardStatus() {
        return sicardStatus;
    }
    public void setSicardStatus(String sicardStatus) {
        this.sicardStatus = sicardStatus;
    }

    public Date getSicardTime() {
        return sicardTime;
    }
    public void setSicardTime(Date sicardTime) {
        this.sicardTime = sicardTime;
    }

    public Integer getIsWxtag() {
        return isWxtag;
    }
    public void setIsWxtag(Integer isWxtag) {
        this.isWxtag = isWxtag;
    }

    public String getWxtagid() {
        return wxtagid;
    }
    public void setWxtagid(String wxtagid) {
        this.wxtagid = wxtagid;
    }

    public Integer getStandardStatus() {
        return standardStatus;
    }
    public void setStandardStatus(Integer standardStatus) {
        this.standardStatus = standardStatus;
    }

    public String getMedicareNumber() {
        return medicareNumber;
    }
    public void setMedicareNumber(String medicareNumber) {
        this.medicareNumber = medicareNumber;
    }

    public String getUnionid() {
        return unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

    public String getRemark() {
        return remark;
    }

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

    public String getLiveStreetCode() {
        return liveStreetCode;
    }

    public void setLiveStreetCode(String liveStreetCode) {
        this.liveStreetCode = liveStreetCode;
    }

    public String getLiveStreetName() {
        return liveStreetName;
    }

    public void setLiveStreetName(String liveStreetName) {
        this.liveStreetName = liveStreetName;
    }
}