package com.yihu.jw.restmodel.archives;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Trick on 2018/2/8.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "居民档案基本信息", description = "居民档案基本信息")
public class PatientArchivesVO {

    @ApiModelProperty("saasid")
    private String saasId; //saasid
    @ApiModelProperty("关联居民登录code")
    private String patientCode;//关联居民登录code
    @ApiModelProperty("姓名")
    private String patientName;//姓名
    @ApiModelProperty("身份证号")
    private String idcard;//身份证号
    @ApiModelProperty("生日")
    private Date birthday;//生日
    @ApiModelProperty("性别，1男，2女")
    private Integer sex;//性别，1男，2女
    @ApiModelProperty("手机号")
    private String mobile;//手机号
    @ApiModelProperty("社保卡号")
    private String ssc;//社保卡号
    @ApiModelProperty("档案状态：1正常，0注销")
    private Integer status;//档案状态：1正常，0注销
    @ApiModelProperty("注销原因：1死亡，2.重复")
    private String cancelReseanType;//注销原因：1死亡，2.重复
    @ApiModelProperty("居委会代码（字典）")
    private String sickVillage;//居委会代码（字典）
    @ApiModelProperty("居委会")
    private String sickVillageName;//居委会
    @ApiModelProperty("常住类型 :1户籍，2非户籍")
    private String residentType;//常住类型 :1户籍，2非户籍
    @ApiModelProperty("血型：A，AB，O，B，N（不详）")
    private String blood;//血型：A，AB，O，B，N（不详）
    @ApiModelProperty("，阴性;2阳性;0 不详")
    private String RH;//1，阴性;2阳性;0 不详
    @ApiModelProperty("民族名称(字典)")
    private String nationCode;//民族名称(字典)
    @ApiModelProperty("民族名称")
    private String nation;//民族名称
    @ApiModelProperty("工作地点")
    private String workPlace;//工作地点
    @ApiModelProperty("联系人")
    private String contactPeople;//联系人
    @ApiModelProperty("联系人电话")
    private String contactMobile;//
    @ApiModelProperty("文化程度（字典）")
    private String educationCode;//文化程度（字典）
    @ApiModelProperty("文化程度")
    private String education;//文化程度
    @ApiModelProperty("职业（字典）")
    private String professionCode;//职业（字典）
    @ApiModelProperty("职业")
    private String profession;//职业·
    @ApiModelProperty("婚姻状况（字典）")
    private String marriageCode;//婚姻状况（字典）
    @ApiModelProperty("婚姻状况")
    private String marriage;//婚姻状况
    @ApiModelProperty("支付方式（字典）")
    private String paymentCode;//支付方式（字典）
    @ApiModelProperty("支付方式")
    private String payment;// 支付方式


    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
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

    public String getSsc() {
        return ssc;
    }

    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
}
