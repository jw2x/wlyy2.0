package com.yihu.jw.iot.device;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 设备质检计划表（质检计划是针对采购清单的）
 * @author yeshijie on 2017/12/1.
 */
@Entity
@Table(name = "iot_device_quality_inspection_plan")
public class IotDeviceQualityInspectionPlanDO extends IdEntityWithOperation implements Serializable{

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "purchase_id")
    private String purchaseId;//采购code

    @Column(name = "order_id")
    private String orderId;//订单code

    @Column(name = "order_no")
    private String orderNo;//订单编号

    @Column(name = "device_id")
    private String deviceId;//产品id

    @Column(name = "device_name")
    private String deviceName;//设备名称

//    @Column(name = "device_model")
//    private String deviceModel;//设备型号
//
//    @Column(name = "device_type")
//    private String deviceType;//设备种类

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

    public enum QualityPlanStatus {
        create("未完成", "1"),
        complete("已完成", "2");
        private String name;
        private String value;

        QualityPlanStatus(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

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

//    public String getDeviceModel() {
//        return deviceModel;
//    }
//
//    public void setDeviceModel(String deviceModel) {
//        this.deviceModel = deviceModel;
//    }
//
//    public String getDeviceType() {
//        return deviceType;
//    }
//
//    public void setDeviceType(String deviceType) {
//        this.deviceType = deviceType;
//    }

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
