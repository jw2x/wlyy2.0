package com.yihu.jw.entity.archives;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
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
    @Column(name = "saas_id")
    private String saasId; //saasid
    @Column(name = "patient_code")
    private String patientCode;//关联居民登录code
    @Column(name = "patient_name")
    private String patientName;//姓名
    @Column(name = "idcard")
    private String idcard;//身份证号
    @Column(name = "birthday")
    private Date birthday;//生日
    @Column(name = "sex")
    private String sex;//int(11) DEFAULT NULL COMMENT '性别，1男，2女
    @Column(name = "mobile")
    private String mobile;//手机号
    @Column(name = "ssc")
    private String ssc;//社保卡号
    @Column(name = "status")
    private String status;//int(11) DEFAULT NULL COMMENT '档案状态：1正常，0注销
    @Column(name = "cancel_resean_type")
    private String cancelReseanType;//注销原因：1死亡，2.重复
    @Column(name = "sick_village")
    private String sickVillage;//居委会代码（字典）
    @Column(name = "sick_village_name")
    private String sickVillageName;//居委会
    @Column(name = "resident_type")
    private String residentType;//常住类型 :1户籍，2非户籍
    @Column(name = "blood")
    private String blood;//血型：A，AB，O，B，N（不详）
    @Column(name = "RH")
    private String RH;//1，阴性;2阳性;0 不详
    @Column(name = "nation_code")
    private String nationCode;//民族code(字典)
    @Column(name = "nation")
    private String nation;//民族名称
    @Column(name = "work_place")
    private String workPlace;//工作地点
    @Column(name = "contact_people")
    private String contactPeople;//联系人
    @Column(name = "contact_mobile")
    private String contactMobile;//联系人电话
    @Column(name = "education_code")
    private String educationCode;//文化程度（字典）
    @Column(name = "education")
    private String education;//文化程度
    @Column(name = "profession_code")
    private String professionCode;//职业（字典）
    @Column(name = "profession")
    private String profession;//职业
    @Column(name = "marriage_code")// ·
    private String marriageCode;//婚姻状况（字典）
    @Column(name = "marriage")
    private String marriage;//婚姻状况
    @Column(name = "payment_code")
    private String paymentCode;//支付方式（字典）
    @Column(name = "payment")
    private String payment;// 支付方式


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
