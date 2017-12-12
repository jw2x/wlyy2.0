package com.yihu.jw.iot.device;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yeshijie on 2017/12/1.
 */
@Entity
@Table(name = "iot_device_quality_inspection_plan")
public class IotDeviceQualityInspectionPlanDO extends IdEntityWithOperation implements Serializable{

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "purchase_code")
    private String purchaseCode;//采购code

    @Column(name = "order_code")
    private String orderCode;//订单code

    @Column(name = "order_no")
    private String orderNo;//订单编号

    @Column(name = "device_code")
    private String deviceCode;//设备code

    @Column(name = "device_name")
    private String deviceName;//设备名称

    @Column(name = "device_model")
    private String deviceModel;//设备型号

    @Column(name = "device_type")
    private String deviceType;//设备种类

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
    private String status;//质检状态(1未检，2已检)

    @Column(name = "del")
    private Integer del;//删除标志

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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
