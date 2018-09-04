package com.yihu.wlyy.statistics.etl.convert.wlyy.model;

/**
 * Created by zhangdan on 2017/10/26.
 */
public class PrescriptionDisease {
    private String healthProblem;

    private String prescriptionCode;

    public String getHealthProblem() {
        return healthProblem;
    }

    public void setHealthProblem(String healthProblem) {
        this.healthProblem = healthProblem;
    }

    public String getPrescriptionCode() {
        return prescriptionCode;
    }

    public void setPrescriptionCode(String prescriptionCode) {
        this.prescriptionCode = prescriptionCode;
    }
}
