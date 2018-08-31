package com.yihu.jw.entity.base.patient;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Trick on 2018/8/31.
 */
@Entity
@Table(name = "base_patient")
public class BasePatientDO extends UuidIdentityEntityWithOperator {

    private String cityCode;//市编码',
    private String townCode;//区县编码',
    private String streetCode;//街道编码',
    private String address;//具体详细地址',
    private String disease;//疾病类型，0健康，1高血压，2糖尿病，3高血压+糖尿病',
    private String diseaseCondition;//病情：0绿标，1黄标，2红标，3重点关注,',
    private String points;//总积分',
    private String recordAmount;//病历总数',
    private String openid;//微信编号',
    private String patientStatus;//用户状态：1正常，0禁用，-1恶意注册，2审核中',
    private String mobileRemarks;//联系方式备注【基卫】',
    private Date openidTime;//'第一次添加open的时间',
    private String sickVillage;//居委会代码',
    private String sickVillageName;//,
    private String principalCode;//绑定电子社保卡主体（共济为操作人code）',
    private String sicardStatus;//是否绑定电子社保卡 （0否 1是）',
    private Date sicardTime;//电子社保卡绑定时间',
    private Integer isWxtag;//是否分配过微信标签',
    private String wxtagid;//微信tagId',
    private String standardStatus;//居民预警状态：0为标准，1为预警状态',
    private String medicareNumber;//医疗保险号',
    private String unionid;//开发平台唯一标识',
    private String del;//作废标识，1正常，0作废'

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

    @Column(name = "street_code")
    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Column(name = "sick_village")
    public String getSickVillage() {
        return sickVillage;
    }

    public void setSickVillage(String sickVillage) {
        this.sickVillage = sickVillage;
    }

    @Column(name = "sick_village_name")
    public String getSickVillageName() {
        return sickVillageName;
    }

    public void setSickVillageName(String sickVillageName) {
        this.sickVillageName = sickVillageName;
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
    public String getStandardStatus() {
        return standardStatus;
    }

    public void setStandardStatus(String standardStatus) {
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
}
