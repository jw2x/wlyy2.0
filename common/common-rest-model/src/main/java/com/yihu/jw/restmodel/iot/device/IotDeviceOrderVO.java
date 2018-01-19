package com.yihu.jw.restmodel.iot.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyTypeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 设备订单表
 * @author yeshijie on 2017/12/1.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "设备订单表", description = "设备订单表")
public class IotDeviceOrderVO extends BaseVO implements Serializable{

    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("订单状态")
    private String orderStatus;
    @ApiModelProperty("采购时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date purchaseTime;
    @ApiModelProperty("订单合同名称")
    private String orderContractName;
    @ApiModelProperty("订单合同链接")
    private String orderContractUrl;
    @ApiModelProperty("采购单位编码")
    private String purchaseUnitCode;
    @ApiModelProperty("采购单位名称")
    private String purchaseUnitName;
    @ApiModelProperty("采购负责人")
    private String purchaserName;
    @ApiModelProperty("采购负责人联系方式")
    private String purchaserPhone;
    @ApiModelProperty("供应商id")
    private String supplierId;
    @ApiModelProperty("供应商名称")
    private String supplierName;
    @ApiModelProperty("供应商负责人")
    private String supplierLeader;
    @ApiModelProperty("供应商负责人联系方式")
    private String supplierLeaderPhone;
    @ApiModelProperty("订单说明")
    private String instruction;

    @ApiModelProperty("类型")
    private List<IotCompanyTypeVO> typeList;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getOrderContractName() {
        return orderContractName;
    }

    public void setOrderContractName(String orderContractName) {
        this.orderContractName = orderContractName;
    }

    public String getOrderContractUrl() {
        return orderContractUrl;
    }

    public void setOrderContractUrl(String orderContractUrl) {
        this.orderContractUrl = orderContractUrl;
    }

    public String getPurchaseUnitCode() {
        return purchaseUnitCode;
    }

    public void setPurchaseUnitCode(String purchaseUnitCode) {
        this.purchaseUnitCode = purchaseUnitCode;
    }

    public String getPurchaseUnitName() {
        return purchaseUnitName;
    }

    public void setPurchaseUnitName(String purchaseUnitName) {
        this.purchaseUnitName = purchaseUnitName;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    public void setPurchaserPhone(String purchaserPhone) {
        this.purchaserPhone = purchaserPhone;
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

    public String getSupplierLeader() {
        return supplierLeader;
    }

    public void setSupplierLeader(String supplierLeader) {
        this.supplierLeader = supplierLeader;
    }

    public String getSupplierLeaderPhone() {
        return supplierLeaderPhone;
    }

    public void setSupplierLeaderPhone(String supplierLeaderPhone) {
        this.supplierLeaderPhone = supplierLeaderPhone;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<IotCompanyTypeVO> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<IotCompanyTypeVO> typeList) {
        this.typeList = typeList;
    }
}
