package com.yihu.jw.restmodel.iot.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yeshijie on 2018/2/27.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(description = "系统字典表")
public class IotMaintenanceUnitVO implements Serializable{

    @ApiModelProperty("维护单位Id")
    private String maintenanceUnitId;
    @ApiModelProperty("维护单位名称")
    private String maintenanceUnitName;

    public String getMaintenanceUnitId() {
        return maintenanceUnitId;
    }

    public void setMaintenanceUnitId(String maintenanceUnitId) {
        this.maintenanceUnitId = maintenanceUnitId;
    }

    public String getMaintenanceUnitName() {
        return maintenanceUnitName;
    }

    public void setMaintenanceUnitName(String maintenanceUnitName) {
        this.maintenanceUnitName = maintenanceUnitName;
    }
}
