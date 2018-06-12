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
//@Table(name = "wlyy_specialist")
//public class SpecialistDO extends IdEntityWithOperation implements Serializable {
//    @Column(name = "saas_id")
//    private String saasId;
//    @Column(name = "name")
//    private String name; //专科医生姓名
//    @Column(name = "profession")
//    private String profession; //专业
//    @Column(name = "profession_name")
//    private String professionName; // 专业名称
//    @Column(name = "dept")
//    private String dept; //科室
//    @Column(name = "dept_name")
//    private String deptName; //科室名称
//
//    @Column(name = "hospital")
//    private String hospital;//医院
//    @Column(name = "hospital_name")
//    private String hospitalName;//医院名称
//    @Column(name = "del")
//    private String del;//1：有效；0：删除
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public String getProfession() {
//        return profession;
//    }
//
//    public void setProfession(String profession) {
//        this.profession = profession;
//    }
//
//
//    public String getProfessionName() {
//        return professionName;
//    }
//
//
//    public void setProfessionName(String professionName) {
//        this.professionName = professionName;
//    }
//
//
//    public String getDept() {
//        return dept;
//    }
//
//    public void setDept(String dept) {
//        this.dept = dept;
//    }
//
//
//    public String getDeptName() {
//        return deptName;
//    }
//
//    public void setDeptName(String deptName) {
//        this.deptName = deptName;
//    }
//
//    public String getHospital() {
//        return hospital;
//    }
//
//    public void setHospital(String hospital) {
//        this.hospital = hospital;
//    }
//
//
//    public String getHospitalName() {
//        return hospitalName;
//    }
//
//    public void setHospitalName(String hospitalName) {
//        this.hospitalName = hospitalName;
//    }
//
//
//    public String getDel() {
//        return del;
//    }
//
//    public void setDel(String del) {
//        this.del = del;
//    }
//}
