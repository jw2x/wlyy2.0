package com.yihu.jw.base.util.threadPool;

import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yeshijie on 2018/9/30.
 */
public class ThreadPoolUtil {

    /**
     * 线程池
     */
    private ThreadPoolExecutor threadPool;

    public ThreadPoolUtil(ThreadPoolExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public void execute(Runnable runnable) {
        threadPool.execute(runnable);
    }

    public void execute(FutureTask futureTask) {
        threadPool.execute(futureTask);
    }

    public void cancel(FutureTask futureTask) {
        futureTask.cancel(true);
    }

}
