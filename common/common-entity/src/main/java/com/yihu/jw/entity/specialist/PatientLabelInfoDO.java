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

    private String saasId;
    private String patient;//居民code',
    private String labelName;//标签名称',
    private String labelCode;//标签code',
    private String labelType;//标签类别',
    private String teamCode;//团队code',

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "patient")
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Column(name = "label_name")
    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Column(name = "label_code")
    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    @Column(name = "label_type")
    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    @Column(name = "team_code")
    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }
}
