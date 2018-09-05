package com.yihu.jw.entity.base.servicePackage;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 服务包签约记录表
 * @author yeshijie on 2018/8/17.
 */
@Entity
@Table(name = "base_service_package_sign_record")
public class ServicePackageSignRecordDO extends UuidIdentityEntity implements Serializable {

    public enum Status {
        create("新建", "1"),
        complete("已完成", "2");
        private String name;
        private String value;

        Status(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private String saasId;
    private String servicePackageId;//服务包id
    private String servicePackageName;//服务包名称
    private String patient;//居民code
    private String name;//居民姓名
    private String idcard;//居民身份证
    private String ssc;//社保卡号
    private String signDoctor;//签约医生code
    private String signDoctorName;//签约医生名称
    private String hospital;//医院code
    private String hospitalName;//医院名称
    private Long adminTeamCode;//行政团队id
    private Long price;//服务总价
    private Date startTime;//服务开始时间
    private Date endTime;//服务结束时间
    private String status;//状态（1新建，2已完成）
    private String doctorTeamCode;//医生服务团队code
    private Date createTime;//创建时间

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "service_package_id")
    public String getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(String servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    @Column(name = "service_package_name")
    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSsc() {
        return ssc;
    }

    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

    @Column(name = "sign_doctor")
    public String getSignDoctor() {
        return signDoctor;
    }

    public void setSignDoctor(String signDoctor) {
        this.signDoctor = signDoctor;
    }

    @Column(name = "sign_doctor_name")
    public String getSignDoctorName() {
        return signDoctorName;
    }

    public void setSignDoctorName(String signDoctorName) {
        this.signDoctorName = signDoctorName;
    }

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

    @Column(name = "admin_team_code")
    public Long getAdminTeamCode() {
        return adminTeamCode;
    }

    public void setAdminTeamCode(Long adminTeamCode) {
        this.adminTeamCode = adminTeamCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "doctor_team_code")
    public String getDoctorTeamCode() {
        return doctorTeamCode;
    }

    public void setDoctorTeamCode(String doctorTeamCode) {
        this.doctorTeamCode = doctorTeamCode;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
