package com.yihu.jw.iot.data_input;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table("iot_base_data_def")
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

}
