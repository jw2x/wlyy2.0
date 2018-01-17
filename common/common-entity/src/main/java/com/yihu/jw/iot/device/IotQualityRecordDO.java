package com.yihu.jw.iot.device;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 质检记录表
 * @author yeshijie on 2018/1/16.
 */
@Entity
@Table(name = "iot_quality_record")
public class IotQualityRecordDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "device_id")
    private String deviceId;//设备id
    @Column(name = "device_name")
    private String deviceName;//设备名称
    @Column(name = "purchase_num")
    private Long purchaseNum;//采购数量
    @Column(name = "quality_leader")
    private String qualityLeader;//质检负责人
    @Column(name = "quality_leader_phone")
    private String qualityLeaderPhone;//质检负责人联系方式
    @Column(name = "plan_time")
    private Date planTime;//计划质检时间
    @Column(name = "actual_time")
    private Date actualTime;//实际质检时间
    @Column(name = "status")
    private String status;//质检状态
    @Column(name = "del")
    private Integer del;//删除标志

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
