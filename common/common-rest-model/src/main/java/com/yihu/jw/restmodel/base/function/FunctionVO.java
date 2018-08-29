package com.yihu.jw.restmodel.base.function;


import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * VO - 功能
 * Created by progr1mmer on 2018/8/14.
 */
@ApiModel(value = "FunctionVO", description = "功能")
public class FunctionVO extends UuidIdentityVOWithOperator {

    //关联 base_module ID
    @ApiModelProperty(value = "关联 base_module ID", example = "0dae0003590016e5b3865e377b2f8615")
    private String moduleId;
    //功能名称
    @ApiModelProperty(value = "功能名称", example = "功能1")
    private String name;
    //网关url前缀
    @ApiModelProperty(value = "网关url前缀", example = "/base")
    private String prefix;
    //功能对应的后台url路径
    @ApiModelProperty(value = "功能对应的后台url路径", example = "/function/list")
    private String url;
    //备注
    @ApiModelProperty(value = "备注", example = "我是备注")
    private String remark;
    //用于jstree显示
    @ApiModelProperty(value = "用于jstree显示")
    private String text;

    public String getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getText() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }
}