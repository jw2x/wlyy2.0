package com.yihu.jw.restmodel.base.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ApiModelProperty(value = "公告id", example = "公告id")
    private String noticeId;
    @ApiModelProperty(value = "用户id", example = "用户id")
    private String userId;
    @ApiModelProperty(value = "是否已读（1已读，0未读）", example = "1")
    private Integer isRead;
    @ApiModelProperty(value = "删除标志（1正常，0删除）", example = "1")
    private Integer del;
    @ApiModelProperty(value = "公告标题", example = "标题")
    private String title;
    @ApiModelProperty(value = "发布时间", example = "2019-01-01 12:20:59")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date sendTime;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
