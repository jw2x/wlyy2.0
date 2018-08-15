package com.yihu.jw.entity.specialist;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Trick on 2018/4/24.
 */
@Entity
@Table(name = "wlyy_patient_hospital_record")
public class PatientHospitalRecordDO extends UuidIdentityEntityWithOperator implements Serializable {
    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "third_code")
    private String thirdCode;//智业记录code
    @Column(name = "event_code")
    private String eventCode; // 事件code
    @Column(name = "patient")
    private String patient;//患者
    @Column(name = "patient_name")
    private String patientName;//患者姓名
    @Column(name = "type")
    private String type;//出院：1；住院：0
    @Column(name = "hospital")
    private String hospital; //医院
    @Column(name = "hospital_name")
    private String hospitalName;
    @Column(name = "doctor")
    private String doctor; //医生
    @Column(name = "doctor_name")
    private String doctorName;
    @Column(name = "city")
    private String city; //城市
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "town")
    private String town; //城镇
    @Column(name = "town_name")
    private String townName;
    @Column(name = "remark")
    private String remark; //备注
    @Column(name = "diagnosis")
    private String diagnosis; //诊断
    @Column(name = "label")
    private String label; //标签
    @Column(name = "label_name")
    private String labelName;
    @Column(name = "dept")
    private String dept;//部门
    @Column(name = "dept_name")
    private String deptName;


    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }


    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }


    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }


    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }


    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }


    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }


    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }


    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }


    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
