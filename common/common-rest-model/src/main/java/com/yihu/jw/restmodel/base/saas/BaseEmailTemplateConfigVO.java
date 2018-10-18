package com.yihu.jw.restmodel.base.saas;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zdm on 2018/10/16.
 */
@ApiModel(value = "BaseEmailTemplateConfigVO", description = "租户审核-邮件模板")
public class BaseEmailTemplateConfigVO extends UuidIdentityEntityWithOperator {
    @ApiModelProperty(value = "模板名称", example = "审核通过模板")
    private String templateName; // T '自定义模板名称
    @ApiModelProperty(value = "邮件首行", example = "您好！")
    private String first; //
    @ApiModelProperty(value = "转链接", example = "http://www.baidu.com")
    private String url ; // 转链接
    @ApiModelProperty(value = "备注", example = "备注")
    private String remark ; //
    @ApiModelProperty(value = "自定义文本", example = "自定义文本1")
    private String keyword1; // 自定义文本1
    @ApiModelProperty(value = "自定义文本", example = "自定义文本2")
    private String keyword2 ; // 自定义文本2
    @ApiModelProperty(value = "自定义文本", example = "自定义文本3")
    private String keyword3; // 自定义文本3
    @ApiModelProperty(value = "自定义文本", example = "自定义文本4")
    private String keyword4; // 自定义文本4
    @ApiModelProperty(value = "自定义文本", example = "自定义文本5")
    private String keyword5 ; // 自定义文本5
    @ApiModelProperty(value = "自定义文本", example = "自定义文本6")
    private String keyword6; // 自定义文本6
    @ApiModelProperty(value = "自定义文本", example = "自定义文本7")
    private String keyword7; // 自定义文本7
    @ApiModelProperty(value = "使用状态", example = "状态 1：正常 0：删除")
    private Integer status; // 状态 1：正常 0：删除

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    public String getKeyword6() {
        return keyword6;
    }

    public void setKeyword6(String keyword6) {
        this.keyword6 = keyword6;
    }

    public String getKeyword7() {
        return keyword7;
    }

    public void setKeyword7(String keyword7) {
        this.keyword7 = keyword7;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
