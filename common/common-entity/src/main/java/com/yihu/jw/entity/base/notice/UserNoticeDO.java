package com.yihu.jw.entity.base.notice;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户公告通知
 * @author yeshijie on 2018/9/30.
 */
@Entity
@Table(name = "base_user_notice")
public class UserNoticeDO extends UuidIdentityEntity{

    public enum Read{
        unRead("未读",0),
        read("已读",1);
        private String name;
        private Integer value;

        Read(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    private String noticeId;//公告id
    private String userId;//用户id
    private Integer isRead;//是否已读（1已读，0未读）
    private Integer del;//删除标志（1正常，0删除）

    @Column(name = "notice_id")
    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "is_read")
    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    @Column(name = "del")
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
