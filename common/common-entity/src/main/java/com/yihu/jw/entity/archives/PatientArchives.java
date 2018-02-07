package com.yihu.jw.entity.archives;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Trick on 2018/2/7.
 */
@Entity
@Table(name = "wlyy_patient_archives")
public class PatientArchives extends IdEntityWithOperation implements Serializable {

    private String code;//档案编号
    private String saasId; //saasid
    private String patientCode;//关联居民登录code
    private String patientName;//姓名
    private String idcard;//身份证号
    private Date birthday;//生日
    private String sex;//int(11) DEFAULT NULL COMMENT '性别，1男，2女
    private String mobile;//手机号
    private String ssc;//社保卡号
    private String status;//int(11) DEFAULT NULL COMMENT '档案状态：1正常，0注销
    private String cancelReseanType;//注销原因：1死亡，2.重复
    private String sickVillage;//居委会代码（字典）
    private String sickVillageName;//居委会
    private String residentType;//常住类型 :1户籍，2非户籍
    private String blood;//血型：A，AB，O，B，N（不详）
    private String RH;//1，阴性;2阳性;0 不详
    private String nationCode;//民族code(字典)
    private String nation;//民族名称
    private String workPlace;//工作地点
    private String contactPeople;//联系人
    private String contactMobile;//联系人电话
    private String educationCode;//文化程度（字典）
    private String education;//文化程度
    private String professionCode;//职业（字典）
    private String profession;//职业·
    private String marriageCode;//婚姻状况（字典）
    private String marriage;//婚姻状况
    private String paymentCode;//支付方式（字典）
    private String payment;// 支付方式

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSsc() {
        return ssc;
    }

    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCancelReseanType() {
        return cancelReseanType;
    }

    public void setCancelReseanType(String cancelReseanType) {
        this.cancelReseanType = cancelReseanType;
    }

    public String getSickVillage() {
        return sickVillage;
    }

    public void setSickVillage(String sickVillage) {
        this.sickVillage = sickVillage;
    }

    public String getSickVillageName() {
        return sickVillageName;
    }

    public void setSickVillageName(String sickVillageName) {
        this.sickVillageName = sickVillageName;
    }

    public String getResidentType() {
        return residentType;
    }

    public void setResidentType(String residentType) {
        this.residentType = residentType;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getRH() {
        return RH;
    }

    public void setRH(String RH) {
        this.RH = RH;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMarriageCode() {
        return marriageCode;
    }

    public void setMarriageCode(String marriageCode) {
        this.marriageCode = marriageCode;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }
}
