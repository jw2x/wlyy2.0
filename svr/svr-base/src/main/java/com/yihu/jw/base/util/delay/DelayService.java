package com.yihu.jw.base.util.delay;

import com.yihu.jw.base.util.threadPool.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;

/**
 * @author yeshijie on 2018/9/29.
 */
@Component
public class DelayService {
    private final Logger log = LoggerFactory.getLogger(DelayService.class);

    @Autowired
    private ThreadPoolUtil threadPoolUtil;

    private boolean start;
    private OnDelayedListener listener;
    public DelayQueue<Notice> delayQueue = new DelayQueue<Notice>();

    public void start(OnDelayedListener listener) {
        if (start) {
            return;
        }
        log.info("DelayService 启动");
        start = true;
        this.listener = listener;
        threadPoolUtil.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Notice notice = delayQueue.take();
                        if (DelayService.this.listener != null) {
                            DelayService.this.listener.onDelayedArrived(notice);
                        }
                        log.info("执行任务+"+notice.getNoticeId()+","+notice.getStartTime());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void add(Notice notice) {
        delayQueue.put(notice);
    }

    public boolean remove(Notice notice) {
        return delayQueue.remove(notice);
    }

    public void add(String noticeId,long startTime) {
        delayQueue.put(new Notice(noticeId, startTime));
    }

    public void remove(String noticeId) {
        Notice[] array = delayQueue.toArray(new Notice[]{});
        if (array == null || array.length <= 0) {
            return;
        }
        Notice target = null;
        for (Notice notice : array) {
            if (notice.getNoticeId().equals(noticeId)) {
                target = notice;
                break;
            }
        }
        if (target != null) {
            delayQueue.remove(target);
        }
    }

}
