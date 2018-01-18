package com.yihu.jw.restmodel.iot.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yeshijie on 2017/12/7.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "文件上传返回")
public class UploadVO implements Serializable{

    @ApiModelProperty("文件名字")
    private String fileName; //文件名字
    @ApiModelProperty("文件类型")
    private String fileType; //文件类型
    @ApiModelProperty("完整的url(包含http)")
    private String fullUrl; //完整的url http://172.19.103.13/healthArchiveGroup/M00/00/24/rBFuH1XdQC6AP3CDAAzodQCbVVc052.jpg
    @ApiModelProperty("完整的uri(不包含http)")
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
