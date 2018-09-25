package com.yihu.jw.healthyhouse.model.dfs;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 文件记录
 * @author zdm
 * @version 1.0
 * @created 2018.09.25
 */
@Entity
@Table(name = "file_resource")
public class FileResource extends UuidIdentityEntityWithOperator {
    //文件地址
    @Column(name = "storage_path", nullable = false)
    private String storagePath;
    //模块
    @Column(name = "mime")
    private String mime;
    //模块-对象id
    @Column(name = "object_id", nullable = false)
    private String objectId ;
    //文件大小
    @Column(name = "file_size")
    private String fileSize;

    //文件名称
    @Column(name = "file_name")
    private String fileName;

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
