package com.yihu.jw.restmodel.iot.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 设备质检计划表
 * @author yeshijie on 2017/12/1.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "设备质检计划表", description = "设备质检计划表")
public class IotDeviceQualityInspectionPlanVO extends BaseVO implements Serializable{

    @ApiModelProperty("采购id")
    private String purchaseId;
    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("设备id")
    private String deviceId;
    @ApiModelProperty("设备名称")
    private String deviceName;
    @ApiModelProperty("采购数量")
    private Long purchaseNum;
    @ApiModelProperty("质检负责人")
    private String qualityLeader;
    @ApiModelProperty("质检负责人联系方式")
    private String qualityLeaderPhone;
    @ApiModelProperty("计划质检时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private String planTime;
    @ApiModelProperty("实际质检时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private String actualTime;
    @ApiModelProperty("质检状态(1未检，2已检)")
    private String status;
    @ApiModelProperty("质检状态(1未检，2已检)")
    private String statusName;

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Long getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Long purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getQualityLeader() {
        return qualityLeader;
    }

    public void setQualityLeader(String qualityLeader) {
        this.qualityLeader = qualityLeader;
    }

    public String getQualityLeaderPhone() {
        return qualityLeaderPhone;
    }

    public void setQualityLeaderPhone(String qualityLeaderPhone) {
        this.qualityLeaderPhone = qualityLeaderPhone;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
