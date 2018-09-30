package com.yihu.jw.restmodel.base.notice;

import com.yihu.jw.entity.UuidIdentityEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *
 * @author yeshijie on 2018/9/30.
 */
@ApiModel(value = "UserNoticeVO", description = "用户公告通知")
public class UserNoticeVO extends UuidIdentityEntity{

    @ApiModelProperty(value = "公告id", example = "标题")
    private String noticeId;
    @ApiModelProperty(value = "用户id", example = "标题")
    private String userId;
    @ApiModelProperty(value = "是否已读（1已读，0未读）", example = "标题")
    private Integer isRead;
    @ApiModelProperty(value = "删除标志（1正常，0删除）", example = "标题")
    private Integer del;
    @ApiModelProperty(value = "公告标题", example = "标题")
    private Integer status;
    @ApiModelProperty(value = "发布时间", example = "2019-01-01 12:20:59")
    private Date sendTime;
    @ApiModelProperty(value = "公告内容", example = "内容")
    private String content;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
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
}
