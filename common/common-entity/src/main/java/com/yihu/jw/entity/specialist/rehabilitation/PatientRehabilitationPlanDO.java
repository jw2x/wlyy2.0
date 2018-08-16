package com.yihu.jw.entity.specialist.rehabilitation;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by humingfen on 2018/8/10.
 */
@Entity
@Table(name = "wlyy_patient_rehabilitation_plan")
public class PatientRehabilitationPlanDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "patient")
    private String patient;//患者标识
    @Column(name = "name")
    private String name;//患者姓名
    @Column(name = "title")
    private String title;//康复服务套餐名称
    @Column(name = "payment")
    private Integer payment;//支付方式（1立即支付，2按服务支付）
    @Column(name = "total_expense")
    private Float totalExpense;//套餐总金额
    @Column(name = "plan_type")
    private Integer planType;//安排类型（1康复计划，2转社区医院，3转家庭病床）
    @Column(name = "service_package_id")
    private String servicePackageId;//服务包id

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

    public Float getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Float totalExpense) {
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
}
