package com.yihu.ehr.iot.model.ehr;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yeshijie on 2018/2/28.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "ehr数据元")
public class MRsMetadata {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("业务领域")
    private String domain;
    @ApiModelProperty("业务领域名称")
    private String domainName;
    @ApiModelProperty("数据元名称")
    private String name;
    @ApiModelProperty("内部标识符")
    private String stdCode;
    @ApiModelProperty("展示代码")
    private String displayCode;
    @ApiModelProperty("类型")
    private String columnType;
    @ApiModelProperty("类型名称")
    private String columnTypeName;
    @ApiModelProperty("是否可为空  默认可为空1")
    private String nullAble;
    @ApiModelProperty("关联字典RS_DICTIONARY编码")
    private String dictCode;
    @ApiModelProperty("关联字典RS_DICTIONARY名称")
    private String dictName;
    @ApiModelProperty("说明")
    private String description;
    @ApiModelProperty("是否有效（0否 1是 默认1）")
    private String valid;
    @ApiModelProperty("dictId")
    private int dictId;
    @ApiModelProperty("数据 来源1 档案数据 2 指标统计")
    private Integer dataSource;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStdCode() {
        return stdCode;
    }
    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

    public String getDisplayCode() {
        return displayCode;
    }
    public void setDisplayCode(String displayCode) {
        this.displayCode = displayCode;
    }

    public String getDictCode() {
        return dictCode;
    }
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getColumnType() {
        return columnType;
    }
    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getNullAble() {
        return nullAble;
    }
    public void setNullAble(String nullAble) {
        this.nullAble = nullAble;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getValid() {
        return valid;
    }
    public void setValid(String valid) {
        this.valid = valid;
    }

    public int getDictId() {
        return dictId;
    }
    public void setDictId(int dictId) {
        this.dictId = dictId;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
