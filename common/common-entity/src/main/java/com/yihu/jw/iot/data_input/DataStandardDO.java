package com.yihu.jw.iot.data_input;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "iot_base_data_def")
public class DataStandardDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "base_name")
    private String baseName;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "required")
    private String required;

    @Column(name = "item_value_min")
    private String itemValueMin;

    @Column(name = "item_value_max")
    private String itemValueMax;

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getItemValueMin() {
        return itemValueMin;
    }

    public void setItemValueMin(String itemValueMin) {
        this.itemValueMin = itemValueMin;
    }

    public String getItemValueMax() {
        return itemValueMax;
    }

    public void setItemValueMax(String itemValueMax) {
        this.itemValueMax = itemValueMax;
    }
}
