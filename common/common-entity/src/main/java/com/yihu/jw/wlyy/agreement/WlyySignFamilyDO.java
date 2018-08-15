package com.yihu.jw.wlyy.agreement;


import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Entity
@Table(name = "wlyy_sign_family")
public class WlyySignFamilyDO extends UuidIdentityEntityWithOperation {

    private static final long serialVersionUID = -6759565631854462880L;
    private String saasId;
    private int type;//签约类型：1三师签约，2家庭签约
    private String patient;//患者标识
    private String openid;//患者微信公众号openid
    private String name;//患者姓名
    private String idcard;//身份证号
    private String ssc;//社保号
    private String mobile;//手机号
    private String emerMobile;//紧急联系人手机号
    private String hospital;//签约医院标识
    private String hospitalName;//签约医院名称
    private String doctor;//全科医生code
    private String doctorName;//全科医生姓名
    private Date begin;//签约开始日期
    private Date end;//签约结束日期
    private String images;//签约图片附件URL，多图以逗号分隔
    private String groupCode;//分组标识
    private Integer status;//签约状态(-1患者已取消，-2已拒绝，-3已解约，-4已到期，0待签约，1已签约，2患者申请取消签约，3医生申请取消签约
    private String reason;//解决原因
    private Date czrq;//操作时间
    private String teamCode;//所属团队code 关联wlyy_doctor_team
    private String signType;//1 用户自己申请  2医生手工带签
    private Date applyDate;//签约时间
    private String releaseSpeak;//解约说明
    private String doctorHealthName;//健康管理师名字
    private String doctorHealth;//健康管理师code
    private String familyCode;//家庭签约标识（年份（两位:例如2016就填写 16）+街道编码（行政区代码）+中心/站（2位）+人数（6位））
    private Date patientApplyDate;//患者发起的签约时间
    private Double expenses;//签约费用
    private String expensesStatus;//扣费状态 【0未扣费 1已扣费 2已退费】
    private String signSource;//签约来源【1 社区签约 2 移动签约】
    private String signDoctorCode;//签约人code
    private String signDoctorName;//签约人名
    private String signDoctorLevel;//1专科 2全科 3健康管理师
    private Date patientApplyUnsginDate;//患者发起的解约时间
    private Date applyUnsignDate;//节约同意时间
    private String expensesType;//补贴类型
    private String signYear;//签约年度
    private String medicalInsuranceNum;//医保流水号
    private String agentDoctorCode;//代签的健康管理师code
    private String agentDoctorName;//代签的健康管理师
    private String agentDoctorLevel;//代签的健康管理师
    private Integer adminTeamCode;//行政团队
    private Date expensesTime;//缴费时间
    private String agreementId;//协议code
    private String criticalPeopleMobile;//紧急联系人电话
    private String criticalPeople;//紧急联系人


    @Column(name="saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column(name = "patient")
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "idcard")
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Column(name = "ssc")
    public String getSsc() {
        return ssc;
    }

    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "emer_mobile")
    public String getEmerMobile() {
        return emerMobile;
    }

    public void setEmerMobile(String emerMobile) {
        this.emerMobile = emerMobile;
    }

    @Column(name = "hospital")
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Column(name = "hospital_name")
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Column(name = "doctor")
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Column(name = "doctor_name")
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "begin")
    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    @Column(name = "end")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Column(name = "images")
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Column(name = "group_code")
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(name = "czrq")
    public Date getCzrq() {
        return czrq;
    }

    public void setCzrq(Date czrq) {
        this.czrq = czrq;
    }

    @Column(name = "team_code")
    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    @Column(name = "sign_type")
    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    
    @Column(name = "apply_date")
    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    
    @Column(name = "release_speak")
    public String getReleaseSpeak() {
        return releaseSpeak;
    }

    public void setReleaseSpeak(String releaseSpeak) {
        this.releaseSpeak = releaseSpeak;
    }

    
    @Column(name = "doctor_health_name")
    public String getDoctorHealthName() {
        return doctorHealthName;
    }

    public void setDoctorHealthName(String doctorHealthName) {
        this.doctorHealthName = doctorHealthName;
    }

    
    @Column(name = "doctor_health")
    public String getDoctorHealth() {
        return doctorHealth;
    }

    public void setDoctorHealth(String doctorHealth) {
        this.doctorHealth = doctorHealth;
    }

    
    @Column(name = "family_code")
    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    
    @Column(name = "patient_apply_date")
    public Date getPatientApplyDate() {
        return patientApplyDate;
    }

    public void setPatientApplyDate(Date patientApplyDate) {
        this.patientApplyDate = patientApplyDate;
    }

    
    @Column(name = "expenses")
    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    
    @Column(name = "expenses_status")
    public String getExpensesStatus() {
        return expensesStatus;
    }

    public void setExpensesStatus(String expensesStatus) {
        this.expensesStatus = expensesStatus;
    }

    
    @Column(name = "sign_source")
    public String getSignSource() {
        return signSource;
    }

    public void setSignSource(String signSource) {
        this.signSource = signSource;
    }

    
    @Column(name = "sign_doctor_code")
    public String getSignDoctorCode() {
        return signDoctorCode;
    }

    public void setSignDoctorCode(String signDoctorCode) {
        this.signDoctorCode = signDoctorCode;
    }

    
    @Column(name = "sign_doctor_name")
    public String getSignDoctorName() {
        return signDoctorName;
    }

    public void setSignDoctorName(String signDoctorName) {
        this.signDoctorName = signDoctorName;
    }

    
    @Column(name = "sign_doctor_level")
    public String getSignDoctorLevel() {
        return signDoctorLevel;
    }

    public void setSignDoctorLevel(String signDoctorLevel) {
        this.signDoctorLevel = signDoctorLevel;
    }

    
    @Column(name = "patient_apply_unsgin_date")
    public Date getPatientApplyUnsginDate() {
        return patientApplyUnsginDate;
    }

    public void setPatientApplyUnsginDate(Date patientApplyUnsginDate) {
        this.patientApplyUnsginDate = patientApplyUnsginDate;
    }

    
    @Column(name = "apply_unsign_date")
    public Date getApplyUnsignDate() {
        return applyUnsignDate;
    }

    public void setApplyUnsignDate(Date applyUnsignDate) {
        this.applyUnsignDate = applyUnsignDate;
    }

    
    @Column(name = "expenses_type")
    public String getExpensesType() {
        return expensesType;
    }

    public void setExpensesType(String expensesType) {
        this.expensesType = expensesType;
    }

    
    @Column(name = "sign_year")
    public String getSignYear() {
        return signYear;
    }

    public void setSignYear(String signYear) {
        this.signYear = signYear;
    }

    
    @Column(name = "medical_insurance_num")
    public String getMedicalInsuranceNum() {
        return medicalInsuranceNum;
    }

    public void setMedicalInsuranceNum(String medicalInsuranceNum) {
        this.medicalInsuranceNum = medicalInsuranceNum;
    }

    
    @Column(name = "agent_doctor_code")
    public String getAgentDoctorCode() {
        return agentDoctorCode;
    }

    public void setAgentDoctorCode(String agentDoctorCode) {
        this.agentDoctorCode = agentDoctorCode;
    }

    
    @Column(name = "agent_doctor_name")
    public String getAgentDoctorName() {
        return agentDoctorName;
    }

    public void setAgentDoctorName(String agentDoctorName) {
        this.agentDoctorName = agentDoctorName;
    }

    
    @Column(name = "agent_doctor_level")
    public String getAgentDoctorLevel() {
        return agentDoctorLevel;
    }

    public void setAgentDoctorLevel(String agentDoctorLevel) {
        this.agentDoctorLevel = agentDoctorLevel;
    }

    
    @Column(name = "admin_team_code")
    public Integer getAdminTeamCode() {
        return adminTeamCode;
    }

    public void setAdminTeamCode(Integer adminTeamCode) {
        this.adminTeamCode = adminTeamCode;
    }

    
    @Column(name = "expenses_time")
    public Date getExpensesTime() {
        return expensesTime;
    }

    public void setExpensesTime(Date expensesTime) {
        this.expensesTime = expensesTime;
    }



    @Column(name = "agreement_id")
    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }
    
    @Column(name = "critical_people_mobile")
    public String getCriticalPeopleMobile() {
        return criticalPeopleMobile;
    }

    public void setCriticalPeopleMobile(String criticalPeopleMobile) {
        this.criticalPeopleMobile = criticalPeopleMobile;
    }

    
    @Column(name = "critical_people")
    public String getCriticalPeople() {
        return criticalPeople;
    }

    public void setCriticalPeople(String criticalPeople) {
        this.criticalPeople = criticalPeople;
    }

}
