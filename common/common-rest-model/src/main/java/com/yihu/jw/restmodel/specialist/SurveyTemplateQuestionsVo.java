package com.yihu.jw.restmodel.specialist;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Reece on 2018/7/8.
 */
public class SurveyTemplateQuestionsVo{
    @ApiModelProperty("问题编码")
    private String code;
    @ApiModelProperty("问题标题")
    private String title;
    @ApiModelProperty("问题说明（可为null）")
    private String questionComment;
    @ApiModelProperty("问题类型（0单选 1多选 2问答）")
    private Integer questionType;
    @ApiModelProperty("模板编码")
    private String templateCode;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("删除标志（1正常，0删除）")
    private Integer del;

    public SurveyTemplateQuestionsVo() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionComment() {
        return questionComment;
    }

    public void setQuestionComment(String questionComment) {
        this.questionComment = questionComment;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
