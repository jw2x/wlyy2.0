package com.yihu.iot.vo.common;

/**
 * @author yeshijie on 2017/12/7.
 */
public class UploadVO {

    private String fileName; //文件名字
    private String fileType; //文件类型
    private String fullUrl; //完整的url http://172.19.103.13/healthArchiveGroup/M00/00/24/rBFuH1XdQC6AP3CDAAzodQCbVVc052.jpg
    private String fullUri; //完整的uri healthArchiveGroup/M00/00/24/rBFuH1XdQC6AP3CDAAzodQCbVVc052.jpg

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getFullUri() {
        return fullUri;
    }

    public void setFullUri(String fullUri) {
        this.fullUri = fullUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
