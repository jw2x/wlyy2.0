package com.yihu.jw.iot.datainput;

import com.yihu.jw.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "iot_data_process_log")
public class DataProcessLogDO extends UuidIdentityEntity implements Serializable {

    @Column(name = "data_id")
    private String dataId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_absoulte_path")
    private String fileAbsoultePath;

    @Column(name = "data_source")
    private String dataSource;

    @Column(name = "receive_time")
    private String receiveTime;

    @Column(name = "upload_time")
    private String uploadTime;

    @Column(name = "process_type")
    private String processType;

    @Column(name = "process_status")
    private String processStatus;

    @Column(name = "process_interface")
    private String processInterface;

    @Column(name = "process_des")
    private String processDes;

    @Column(name = "fail_count")
    private int fileCount;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAbsoultePath() {
        return fileAbsoultePath;
    }

    public void setFileAbsoultePath(String fileAbsoultePath) {
        this.fileAbsoultePath = fileAbsoultePath;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getProcessDes() {
        return processDes;
    }

    public void setProcessDes(String processDes) {
        this.processDes = processDes;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public String getProcessInterface() {
        return processInterface;
    }

    public void setProcessInterface(String processInterface) {
        this.processInterface = processInterface;
    }
}
