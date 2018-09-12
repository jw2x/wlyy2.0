package com.yihu.jw.entity.iot.datainput;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "iot_base_data_def")
public class DataStandardDO extends UuidIdentityEntity implements Serializable {


    private String baseName;
    private String itemCode;
    private String itemName;
    private String itemType;
    private String required;
    private String itemValueMin;
    private String itemValueMax;

    @Column(name = "base_name")
    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    @Column(name = "item_code")
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Column(name = "item_name")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Column(name = "item_type")
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Column(name = "required")
    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    @Column(name = "item_value_min")
    public String getItemValueMin() {
        return itemValueMin;
    }

    public void setItemValueMin(String itemValueMin) {
        this.itemValueMin = itemValueMin;
    }

    @Column(name = "item_value_max")
    public String getItemValueMax() {
        return itemValueMax;
    }

    public void setItemValueMax(String itemValueMax) {
        this.itemValueMax = itemValueMax;
    }
}
