package com.yihu.ehr.iot.model.ehr;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yeshijie on 2018/2/28.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "ehr标准数据集")
public class MStdDataSet {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("所属的文档ID. 暂时为空")
    private Long documentId;
    @ApiModelProperty("类别/业务领域")
    private Integer catalog;
    @ApiModelProperty("hashCode")
    private Integer hashCode;
    @ApiModelProperty("lang")
    private Integer lang;
    @ApiModelProperty("标准发布者")
    private Integer publisher;
    @ApiModelProperty("multiRecord")
    private Boolean multiRecord;
    @ApiModelProperty("标准参考")
    private String reference;
    @ApiModelProperty("标准规范中的版本")
    private String stdVersion;
    @ApiModelProperty("数据集编码")
    private String code;
    @ApiModelProperty("数据集名称")
    private String name;
    @ApiModelProperty("摘要")
    private String summary;
    @ApiModelProperty("innerVersionId")
    private String innerVersionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Integer getCatalog() {
        return catalog;
    }

    public void setCatalog(Integer catalog) {
        this.catalog = catalog;
    }

    public Integer getHashCode() {
        return hashCode;
    }

    public void setHashCode(Integer hashCode) {
        this.hashCode = hashCode;
    }

    public Integer getLang() {
        return lang;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public Integer getPublisher() {
        return publisher;
    }

    public void setPublisher(Integer publisher) {
        this.publisher = publisher;
    }

    public Boolean getMultiRecord() {
        return multiRecord;
    }

    public void setMultiRecord(Boolean multiRecord) {
        this.multiRecord = multiRecord;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStdVersion() {
        return stdVersion;
    }

    public void setStdVersion(String stdVersion) {
        this.stdVersion = stdVersion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getInnerVersionId() {
        return innerVersionId;
    }

    public void setInnerVersionId(String innerVersionId) {
        this.innerVersionId = innerVersionId;
    }
}
