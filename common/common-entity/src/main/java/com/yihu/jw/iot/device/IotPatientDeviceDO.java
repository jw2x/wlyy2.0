package com.yihu.jw.iot.device;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 居民设备绑定表
 * @author yeshijie on 2018/1/16.
 */
@Entity
@Table(name = "iot_patient_device")
public class IotPatientDeviceDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "patient")
    private String patient;//居民code
    @Column(name = "patient_name")
    private String patientName;//居民姓名
    @Column(name = "idcard")
    private String idcard;//居民身份证
    @Column(name = "device_id")
    private String deviceId;//设备id
    @Column(name = "device_name")
    private String deviceName;//设备名称
    @Column(name = "device_sn")
    private String deviceSn;//设备sn码
    @Column(name = "doctor")
    private String doctor;//质检负责人
    @Column(name = "agent")
    private String agent;//质检负责人联系方式
    @Column(name = "user_type")
    private String userType;//质检状态
    @Column(name = "del")
    private Integer del;//删除标志

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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
