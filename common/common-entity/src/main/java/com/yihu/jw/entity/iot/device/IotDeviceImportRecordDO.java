package com.yihu.jw.entity.iot.device;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 设备批量导入历史记录
 * @author yeshijie on 2018/1/18.
 */
@Entity
@Table(name = "iot_device_import_record")
public class IotDeviceImportRecordDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "file_name")
    private String fileName;//导入文件名
    @Column(name = "file_url")
    private String fileUrl;//导入文件连接
    @Column(name = "result_url")
    private String resultUrl;//导入结果连接
    @Column(name = "order_id")
    private String orderId;//订单id
    @Column(name = "purchase_id")
    private String purchaseId;//采购id
    @Column(name = "status")
    private String status;//导入状态(1进行中，2已完成)
    @Column(name = "del")
    private Integer del;//删除标志

    public enum DeviceImportRecordStatus {
        create("进行中", "1"),
        complete("已完成", "2");
        private String name;
        private String value;

        DeviceImportRecordStatus(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
