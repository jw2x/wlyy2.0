package com.yihu.jw.restmodel.iot.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 * @author yeshijie on 2018/1/18.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "设备批量导入历史记录")
public class IotDeviceImportRecordVO implements Serializable {

    @ApiModelProperty("导入文件名")
    private String fileName;
    @ApiModelProperty("导入文件连接")
    private String fileUrl;
    @ApiModelProperty("导入结果连接")
    private String resultUrl;
    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("采购id")
    private String purchaseId;
    @ApiModelProperty("导入状态(1进行中，2已完成)")
    private String status;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
