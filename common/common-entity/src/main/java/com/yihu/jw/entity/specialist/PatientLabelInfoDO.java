package com.yihu.jw.entity.specialist;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Trick on 2018/5/29.
 */
@Entity
@Table(name = "wlyy_patient_label_info")
public class PatientLabelInfoDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "patient")
    private String patient;//居民code',
    @Column(name = "label_name")
    private String labelName;//标签名称',
    @Column(name = "label_code")
    private String labelCode;//标签code',
    @Column(name = "label_type")
    private String labelType;//标签类别',
    @Column(name = "team_code")
    private String teamCode;//团队code',


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


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }


    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }


    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }


    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }
}
