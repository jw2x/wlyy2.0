package com.yihu.jw.restmodel.archives;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Trick on 2018/2/7.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "测试")
public class Test {

    @ApiModelProperty("文件名字")
    private String fileName; //文件名字
    @ApiModelProperty("文件类型")
    private String fileType; //文件类型

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
