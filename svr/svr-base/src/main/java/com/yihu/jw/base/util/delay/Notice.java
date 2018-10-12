package com.yihu.jw.base.util.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 消息公告的延迟队列
 * @author yeshijie on 2018/10/9.
 */
public class Notice implements Delayed {

    private String noticeId;
    private long startTime;

    public Notice(String noticeId, long timeout){
        this.noticeId = noticeId;
        this.startTime = timeout;
    }

    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }
        if (other instanceof Notice) {
            Notice otherRequest = (Notice) other;
            long otherStartTime = otherRequest.getStartTime();
            return (int) (this.startTime - otherStartTime);
        }
        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
