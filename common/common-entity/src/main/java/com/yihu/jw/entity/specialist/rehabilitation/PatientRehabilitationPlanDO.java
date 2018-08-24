package com.yihu.jw.entity.specialist.rehabilitation;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by humingfen on 2018/8/10.
 */
@Entity
@Table(name = "wlyy_patient_rehabilitation_plan")
public class PatientRehabilitationPlanDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "patient")
    private String patient;//患者标识
    @Column(name = "name")
    private String name;//患者姓名
    @Column(name = "title")
    private String title;//康复服务套餐名称
    @Column(name = "disease")
    private String disease;//疾病
    @Column(name = "disease_name")
    private String diseaseName;//疾病名称
    @Column(name = "payment")
    private Integer payment;//支付方式（1立即支付，2按服务支付）
    @Column(name = "total_expense")
    private Integer totalExpense;//套餐总金额
    @Column(name = "plan_type")
    private Integer planType;//安排类型（1康复计划，2转社区医院，3转家庭病床）
    @Column(name = "service_package_id")
    private String servicePackageId;//服务包id
    @Column(name = "service_qr_code")
    private String serviceQrCode;//服务码
    @Column(name = "status")
    private Integer status;//状态（0已中止，1进行中，2已完成）

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Integer totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(String servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getServiceQrCode() {
        return serviceQrCode;
    }

    public void setServiceQrCode(String serviceQrCode) {
        this.serviceQrCode = serviceQrCode;
    }
}
