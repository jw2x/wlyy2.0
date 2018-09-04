package com.yihu.wlyy.statistics.vo;


import org.elasticsearch.common.collect.HppcMaps;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
public class DataModel {
    private String businessId;//业务id
    private String city;//城市代码 350200
    private String town;//区代码 350206
    private String hospital;//机构code
    private String team;//团队的code
    private String slaveKey1;//从维度  1级维度
    private String slaveKey2;//从维度  2级维度
    private String slaveKey3;//从维度  3级维度
    private String slaveKey4;//从维度  4级维度
    private String expensesStatus;//扣费状态
    private String patient;//患者code、
    private String idcard;// 身份证
    private String serverType;//服务类型
    private String healthLable;//健康标签
    private Double num = 1.0;//分数 如果是累加的计算 默认是1 如果是分数从数据库拿
    private String prescriptionCode;//处方code
    private String healthProblem;//诊断标签
    private Integer readStatus;//已读状态


    private Double result1 = 0.0; //从ES统计的时候的数目


    public String getHealthLable() {
        return healthLable;
    }

    public void setHealthLable(String healthLable) {
        this.healthLable = healthLable;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSlaveKey1() {
        return slaveKey1;
    }

    public void setSlaveKey1(String slaveKey1) {
        this.slaveKey1 = slaveKey1;
    }

    public String getSlaveKey2() {
        return slaveKey2;
    }

    public void setSlaveKey2(String slaveKey2) {
        this.slaveKey2 = slaveKey2;
    }

    public String getSlaveKey3() {
        return slaveKey3;
    }

    public void setSlaveKey3(String slaveKey3) {
        this.slaveKey3 = slaveKey3;
    }

    public String getSlaveKey4() {
        return slaveKey4;
    }

    public void setSlaveKey4(String slaveKey4) {
        this.slaveKey4 = slaveKey4;
    }

    public String getExpensesStatus() {
        return expensesStatus;
    }

    public void setExpensesStatus(String expensesStatus) {
        this.expensesStatus = expensesStatus;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String isNull() {
//        if (StringUtils.isEmpty(this.getCity())) {
//            return "city";
//        }
//        if (StringUtils.isEmpty(this.getTown())) {
//            return "towm";
//        }
//        if (StringUtils.isEmpty(this.getHospital())) {
//            return "hospital";
//        }
        if (StringUtils.isEmpty(this.getTeam())) {
            return "team";
        }

        return null;
    }


    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getPrescriptionCode() {
        return prescriptionCode;
    }

    public void setPrescriptionCode(String prescriptionCode) {
        this.prescriptionCode = prescriptionCode;
    }

    public String getHealthProblem() {
        return healthProblem;
    }

    public void setHealthProblem(String healthProblem) {
        this.healthProblem = healthProblem;
    }

    public Double getResult1() {
        return result1;
    }

    public void setResult1(Double result1) {
        this.result1 = result1;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }
}
