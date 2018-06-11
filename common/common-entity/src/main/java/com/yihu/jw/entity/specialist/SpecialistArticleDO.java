//package com.yihu.jw.entity.specialist;
//
//import com.yihu.jw.IdEntityWithOperation;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.io.Serializable;
//
///**
// * Created by Trick on 2018/4/24.
// */
//@Entity
//@Table(name = "wlyy_specialist_Article")
//public class SpecialistArticleDO extends IdEntityWithOperation implements Serializable {
//
//    @Column(name = "saas_id")
//    private String saasId;
//    @Column(name = "patient")
//    private String patient;//居民
//    @Column(name = "patient_name")
//    private String patientName;//居民
//    @Column(name = "doctor")
//    private String doctor;//医生
//    @Column(name = "doctor_name")
//    private String doctorName;//医生
//    @Column(name = "article")
//    private String article;//文章
//
//
//    public String getSaasId() {
//        return saasId;
//    }
//
//    public void setSaasId(String saasId) {
//        this.saasId = saasId;
//    }
//
//
//    public String getPatient() {
//        return patient;
//    }
//
//    public void setPatient(String patient) {
//        this.patient = patient;
//    }
//
//
//    public String getPatientName() {
//        return patientName;
//    }
//
//    public void setPatientName(String patientName) {
//        this.patientName = patientName;
//    }
//
//
//    public String getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(String doctor) {
//        this.doctor = doctor;
//    }
//
//
//    public String getDoctorName() {
//        return doctorName;
//    }
//
//    public void setDoctorName(String doctorName) {
//        this.doctorName = doctorName;
//    }
//
//
//    public String getArticle() {
//        return article;
//    }
//
//    public void setArticle(String article) {
//        this.article = article;
//    }
//}
