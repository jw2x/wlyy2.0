package com.yihu.jw.restmodel.base.notice;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *
 * @author yeshijie on 2018/9/30.
 */
@ApiModel(value = "NoticeVO", description = "公告通知")
public class NoticeVO extends UuidIdentityVOWithOperator {

    @ApiModelProperty(value = "公告标题", example = "标题")
    private String title;
    @ApiModelProperty(value = "状态（1待发布，2已发布）", example = "1")
    private Integer status;
    @ApiModelProperty(value = "发布时间", example = "2019-01-01 12:20:59")
    private Date sendTime;
    @ApiModelProperty(value = "公告内容", example = "内容")
    private String content;
    @ApiModelProperty(value = "发布方式（1手动发布，2自动发布）", example = "1")
    private Integer sendType;
    @ApiModelProperty(value = "删除标志（1正常，0删除）", example = "1")
    private Integer del;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
