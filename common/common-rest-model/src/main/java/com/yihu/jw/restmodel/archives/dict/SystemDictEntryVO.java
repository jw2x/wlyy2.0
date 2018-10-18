package com.yihu.jw.restmodel.archives.dict;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wq on 2016/2/19.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "数据字典系表", description = "数据字典系表")
public class SystemDictEntryVO {
    @ApiModelProperty("系统字典内部编码 - 关联SystemDict")
    private long dictId;
    @ApiModelProperty("系统字典元编码")
    private String code;
    @ApiModelProperty("saasId")
    private String saasId;
    @ApiModelProperty("系统字典元值")
    private String value;
    @ApiModelProperty("系统字典元排序")
    private Integer sort;
    @ApiModelProperty("系统字典元分类")
    private String catalog;
    @ApiModelProperty("系统字典元拼间首字母")
    private String phoneticCode;

    public long getDictId() {
        return dictId;
    }

    public void setDictId(long dictId) {
        this.dictId = dictId;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPhoneticCode() {
        return phoneticCode;
    }

    public void setPhoneticCode(String phoneticCode) {
        this.phoneticCode = phoneticCode;
    }

}
