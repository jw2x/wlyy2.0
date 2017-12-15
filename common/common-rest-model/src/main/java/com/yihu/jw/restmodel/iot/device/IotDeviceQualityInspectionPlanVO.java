package com.yihu.jw.restmodel.iot.device;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yeshijie on 2017/12/1.
 */
public class IotDeviceQualityInspectionPlanVO implements Serializable{

    private String saasId;
    private String purchaseCode;//采购code
    private String orderCode;//订单code
    private String orderNo;//订单编号
    private String deviceCode;//设备code
    private String deviceName;//设备名称
    private String deviceModel;//设备型号
    private String deviceType;//设备种类
    private Long purchaseNum;//采购数量
    private String qualityLeader;//质检负责人
    private String qualityLeaderPhone;//质检负责人联系方式
    private Date planTime;//计划质检时间
    private Date actualTime;//实际质检时间
    private String status;//质检状态(1未检，2已检)
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
