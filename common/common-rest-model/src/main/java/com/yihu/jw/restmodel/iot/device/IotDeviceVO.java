package com.yihu.jw.restmodel.iot.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import com.yihu.jw.restmodel.iot.product.IotProductDataTransmissionVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 设备表
 * @author yeshijie on 2017/12/1.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "设备表", description = "设备表")
public class IotDeviceVO extends BaseVO implements Serializable {

    @ApiModelProperty("产品id")
    private String productId;
    @ApiModelProperty("设备名称")
    private String name;
    @ApiModelProperty("设备sn码")
    private String deviceSn;
    @ApiModelProperty("是否复合型(1是，0否即设备为单一功能)")
    private Integer isComposite;
    @ApiModelProperty("是否绑定（1已绑定，2未绑定）")
    private Integer isBinding;
    @ApiModelProperty("是否绑定（1已绑定，2未绑定）")
    private String isBindingName;
    @ApiModelProperty("是否平台型(1是，0否)")
    private Integer isPlatform;
    @ApiModelProperty("设备来源(1采购订单关联,2居民绑定,3管理员新增)")
    private String deviceSource;
    @ApiModelProperty("设备来源(1采购订单关联,2居民绑定,3管理员新增)")
    private String deviceSourceName;
    @ApiModelProperty("供应商code")
    private String supplierId;
    @ApiModelProperty("供应商名称")
    private String supplierName;
    @ApiModelProperty("厂商code")
    private String manufacturerId;
    @ApiModelProperty("厂商名称")
    private String manufacturerName;
    @ApiModelProperty("归属社区")
    private String hospital;
    @ApiModelProperty("归属社区名称")
    private String hospitalName;
    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("设备状态(1正常、2报废、3检修)")
    private String status;
    @ApiModelProperty("下次质检时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date nextQualityTime;
    @ApiModelProperty("采购id")
    private String purchaseId;
    @ApiModelProperty("sim卡号")
    private String simNo;
    @ApiModelProperty("质检状态")
    private String qualityStatus;
    @ApiModelProperty("数据传输方式")
    private List<IotProductDataTransmissionVO> dataTransmissionVOList;
    @ApiModelProperty("关联居民")
    private List<IotPatientDeviceVO> patientDeviceVOList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Integer getIsComposite() {
        return isComposite;
    }

    public void setIsComposite(Integer isComposite) {
        this.isComposite = isComposite;
    }

    public Integer getIsPlatform() {
        return isPlatform;
    }

    public void setIsPlatform(Integer isPlatform) {
        this.isPlatform = isPlatform;
    }

    public String getDeviceSource() {
        return deviceSource;
    }

    public void setDeviceSource(String deviceSource) {
        this.deviceSource = deviceSource;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getNextQualityTime() {
        return nextQualityTime;
    }

    public void setNextQualityTime(Date nextQualityTime) {
        this.nextQualityTime = nextQualityTime;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getSimNo() {
        return simNo;
    }

    public void setSimNo(String simNo) {
        this.simNo = simNo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Integer isBinding) {
        this.isBinding = isBinding;
    }

    public String getIsBindingName() {
        return isBindingName;
    }

    public void setIsBindingName(String isBindingName) {
        this.isBindingName = isBindingName;
    }

    public String getDeviceSourceName() {
        return deviceSourceName;
    }

    public void setDeviceSourceName(String deviceSourceName) {
        this.deviceSourceName = deviceSourceName;
    }

    public List<IotProductDataTransmissionVO> getDataTransmissionVOList() {
        return dataTransmissionVOList;
    }

    public void setDataTransmissionVOList(List<IotProductDataTransmissionVO> dataTransmissionVOList) {
        this.dataTransmissionVOList = dataTransmissionVOList;
    }

    public String getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(String qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public List<IotPatientDeviceVO> getPatientDeviceVOList() {
        return patientDeviceVOList;
    }

    public void setPatientDeviceVOList(List<IotPatientDeviceVO> patientDeviceVOList) {
        this.patientDeviceVOList = patientDeviceVOList;
    }
}
