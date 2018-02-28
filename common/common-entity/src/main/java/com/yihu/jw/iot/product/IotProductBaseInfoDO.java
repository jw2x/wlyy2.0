package com.yihu.jw.iot.product;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 产品基本信息表
 * @author yeshijie on 2018/1/16.
 */
@Entity
@Table(name = "iot_product_base_info")
public class IotProductBaseInfoDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "parent_id")
    private String parentId;//父类ID
    @Column(name = "product_classify")
    private String productClassify;//产品分类(1自有产品，2代理产品)
    @Column(name = "supplier_name")
    private String supplierName;//厂商名称
    @Column(name = "supplier_id")
    private String supplierId;//厂商id
    @Column(name = "agent_name")
    private String agentName;//代理商名称
    @Column(name = "agent_id")
    private String agentId;//代理商id
    @Column(name = "type")
    private String type;//产品类型
    @Column(name = "product_subclass")
    private String productSubclass;//产品小类
    @Column(name = "instrument_classify")
    private String instrumentClassify;//68分类/器械分类
    @Column(name = "register_certificate")
    private String registerCertificate;//注册证号
    @Column(name = "register_certificate_img")
    private String registerCertificateImg;//注册证扫描件
    @Column(name = "start_time")
    private Date startTime;//有效期开始时间
    @Column(name = "end_time")
    private Date endTime;//有效期结束时间
    @Column(name = "name")
    private String name;//产品名称
    @Column(name = "alias")
    private String alias;//别名
    @Column(name = "brand_name")
    private String brandName;//品牌名称
    @Column(name = "origin_place")
    private String originPlace;//产地
    @Column(name = "origin_type")
    private Integer originType;//产地类型
    @Column(name = "is_cold_chain")
    private Integer isColdChain;//是否需要冷链
    @Column(name = "certificate_id")
    private String certificateId;//授权id
    @Column(name = "certificate_name")
    private String certificateName;//授权书名称
    @Column(name = "del")
    private Integer del;//删除标志

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
