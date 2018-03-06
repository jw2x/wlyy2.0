package com.yihu.jw.restmodel.archives.dict;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wq on 2016/2/19.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "数据字典主表", description = "数据字典主表")
public class SystemDictVO {

    @ApiModelProperty("系统字典内部编码")
    private long id;
    @ApiModelProperty("系统字典名称")
    private String name;
    @ApiModelProperty("系统字典引用的标准")
    private String reference;
    @ApiModelProperty("创建者")
    private String authorId;
    @ApiModelProperty("字典名称拼间首字母")
    private String phoneticCode;
    @ApiModelProperty("创建时间")
    private String createDate;
    @ApiModelProperty("系统字典名称")
    private List<SystemDictEntryVO> child;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPhoneticCode() {
        return phoneticCode;
    }

    public void setPhoneticCode(String phoneticCode) {
        this.phoneticCode = phoneticCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<SystemDictEntryVO> getChild() {
        return child;
    }

    public void setChild(List<SystemDictEntryVO> child) {
        this.child = child;
    }
}
