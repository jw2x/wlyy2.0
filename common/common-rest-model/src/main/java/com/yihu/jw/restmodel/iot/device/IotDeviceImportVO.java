package com.yihu.jw.restmodel.iot.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yeshijie on 2018/1/23.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "设备导入")
public class IotDeviceImportVO implements Serializable {

    @ApiModelProperty("sn码")
    private String sn;
    @ApiModelProperty("归属社区")
    private String hospital;
    @ApiModelProperty("sim卡号")
    private String sim;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }
}
