package com.yihu.jw.iot.label;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 设备标签表
 * @author yeshijie on 2017/12/28.
 */
@Entity
@Table(name = "iot_device_label")
public class IotDeviceLabelDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "label_code")
    private String labelCode;//标签标识
    @Column(name = "label_name")
    private String labelName;//标签名称
    @Column(name = "label_type")
    private String labelType;//标签类型
    @Column(name = "is_system")
    private Integer isSystem;//是否系统标签（1是、0否）
    @Column(name = "supplier_code")
    private String supplierCode;//供应商code
    @Column(name = "sort")
    private Long sort;//排序

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public Integer getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Integer isSystem) {
        this.isSystem = isSystem;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}
