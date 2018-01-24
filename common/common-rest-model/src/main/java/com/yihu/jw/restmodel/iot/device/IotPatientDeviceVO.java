package com.yihu.jw.restmodel.iot.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 居民设备绑定表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "设备质检计划表", description = "设备质检计划表")
public class IotPatientDeviceVO extends BaseVO implements Serializable {

    @ApiModelProperty("居民code")
    private String patient;
    @ApiModelProperty("居民姓名")
    private String patientName;
    @ApiModelProperty("居民身份证")
    private String idcard;
    @ApiModelProperty("设备id")
    private String deviceId;
    @ApiModelProperty("设备名称")
    private String deviceName;
    @ApiModelProperty("设备sn码")
    private String deviceSn;
    @ApiModelProperty("质检负责人")
    private String doctor;
    @ApiModelProperty("质检负责人联系方式")
    private String agent;
    @ApiModelProperty("质检状态")
    private String userType;

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

}
