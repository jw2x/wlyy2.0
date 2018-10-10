package com.yihu.jw.entity.base.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 公告通知
 * @author yeshijie on 2018/9/30.
 */
@Entity
@Table(name = "base_notice")
public class NoticeDO extends UuidIdentityEntityWithOperator{

    public enum Status{
        wait("待发布",1),
        released("已发布",2);
        private String name;
        private Integer value;

        Status(String name, Integer value) {
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

    public enum SendType{
        manual_release("手动发布",1),
        automatic_release("自动发布",2);
        private String name;
        private Integer value;

        SendType(String name, Integer value) {
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

    private String title;//公告标题
    private Integer status;//状态（1待发布，2已发布）
    private Date sendTime;//发布时间
    private String content;//公告内容
    private Integer sendType;//发布方式（1手动发布，2自动发布）
    private Integer del;//删除标志（1正常，0删除）

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "send_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Column(name = "send_type")
    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    @Column(name = "del")
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "NoticeDO{" +
                "title='" + title + '\'' +
                ", status=" + status +
                ", sendTime=" + sendTime +
                ", content='" + content + '\'' +
                ", sendType=" + sendType +
                ", del=" + del +
                '}';
    }
}
