package com.yihu.jw.restmodel.iot.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 产品基本信息表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "产品基本信息表")
public class IotProductBaseInfoVO extends BaseVO implements Serializable {

    @ApiModelProperty("父类ID")
    private String parentId;
    @ApiModelProperty("产品分类")
    private String productClassify;
    @ApiModelProperty("厂商名称")
    private String supplierName;
    @ApiModelProperty("厂商id")
    private String supplierId;
    @ApiModelProperty("代理商名称")
    private String agentName;
    @ApiModelProperty("代理商id")
    private String agentId;
    @ApiModelProperty("产品类型")
    private String type;
    @ApiModelProperty("产品类型名称")
    private String typeName;
    @ApiModelProperty("产品小类")
    private String productSubclass;
    @ApiModelProperty("产品小类名称")
    private String productSubclassName;
    @ApiModelProperty("68分类/器械分类")
    private String instrumentClassify;
    @ApiModelProperty("68分类/器械分类名称")
    private String instrumentClassifyName;
    @ApiModelProperty("注册证号")
    private String registerCertificate;
    @ApiModelProperty("注册证扫描件")
    private String registerCertificateImg;
    @ApiModelProperty("有效期开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private String startTime;
    @ApiModelProperty("有效期结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private String endTime;
    @ApiModelProperty("产品名称")
    private String name;
    @ApiModelProperty("别名")
    private String alias;
    @ApiModelProperty("品牌名称")
    private String brandName;
    @ApiModelProperty("产地")
    private String originPlace;
    @ApiModelProperty("产地类型(1国产，2进口)")
    private Integer originType;
    @ApiModelProperty("是否需要冷链(1是，0否)")
    private Integer isColdChain;
    @ApiModelProperty("授权id")
    private String certificateId;
    @ApiModelProperty("授权书名称")
    private String certificateName;
    @ApiModelProperty("数据传输方式")
    private List<IotProductDataTransmissionVO> dataTransmissionVOList;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProductClassify() {
        return productClassify;
    }

    public void setProductClassify(String productClassify) {
        this.productClassify = productClassify;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductSubclass() {
        return productSubclass;
    }

    public void setProductSubclass(String productSubclass) {
        this.productSubclass = productSubclass;
    }

    public String getInstrumentClassify() {
        return instrumentClassify;
    }

    public void setInstrumentClassify(String instrumentClassify) {
        this.instrumentClassify = instrumentClassify;
    }

    public String getRegisterCertificate() {
        return registerCertificate;
    }

    public void setRegisterCertificate(String registerCertificate) {
        this.registerCertificate = registerCertificate;
    }

    public String getRegisterCertificateImg() {
        return registerCertificateImg;
    }

    public void setRegisterCertificateImg(String registerCertificateImg) {
        this.registerCertificateImg = registerCertificateImg;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public Integer getOriginType() {
        return originType;
    }

    public void setOriginType(Integer originType) {
        this.originType = originType;
    }

    public Integer getIsColdChain() {
        return isColdChain;
    }

    public void setIsColdChain(Integer isColdChain) {
        this.isColdChain = isColdChain;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public List<IotProductDataTransmissionVO> getDataTransmissionVOList() {
        return dataTransmissionVOList;
    }

    public void setDataTransmissionVOList(List<IotProductDataTransmissionVO> dataTransmissionVOList) {
        this.dataTransmissionVOList = dataTransmissionVOList;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProductSubclassName() {
        return productSubclassName;
    }

    public void setProductSubclassName(String productSubclassName) {
        this.productSubclassName = productSubclassName;
    }

    public String getInstrumentClassifyName() {
        return instrumentClassifyName;
    }

    public void setInstrumentClassifyName(String instrumentClassifyName) {
        this.instrumentClassifyName = instrumentClassifyName;
    }
}
