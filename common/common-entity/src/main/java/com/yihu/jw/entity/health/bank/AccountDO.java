package com.yihu.jw.entity.health.bank;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by wang zhinan on 2018/4/26.
 */
@Entity
@Table(name = "wlyy_health_bank_account")
public class AccountDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "total")
    private int total;

    @Column(name = "password")
    private String password;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "hospital")
    private String hospital;

    @Transient
    private String sum;

    @Transient
    private int usedTotal;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getUsedTotal() {
        return usedTotal;
    }

    public void setUsedTotal(int usedTotal) {
        this.usedTotal = usedTotal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
