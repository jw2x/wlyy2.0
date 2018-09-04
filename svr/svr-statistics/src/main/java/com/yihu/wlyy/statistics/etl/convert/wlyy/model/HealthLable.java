package com.yihu.wlyy.statistics.etl.convert.wlyy.model;

/**
 * Created by chenweida on 2017/7/1.
 * SELECT
 * spli.label AS health_lable,
 * spli.patient
 * FROM
 * wlyy_sign_patient_label_info spli
 * WHERE
 * spli.label_type = '2'
 * AND spli. STATUS = '1'
 */
public class HealthLable {
    private String healthLable;
    private String patient;

    public String getHealthLable() {
        return healthLable;
    }

    public void setHealthLable(String healthLable) {
        this.healthLable = healthLable;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
