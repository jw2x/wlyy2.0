package com.yihu.jw.restmodel.iot.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 质检记录表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "质检记录表", description = "质检记录表")
public class IotQualityRecordVO extends BaseVO implements Serializable {

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
    private Date planTime;
    @ApiModelProperty("实际质检时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date actualTime;
    @ApiModelProperty("质检状态")
    private String status;

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

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
