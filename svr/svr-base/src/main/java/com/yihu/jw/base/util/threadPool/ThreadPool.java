package com.yihu.jw.base.util.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yeshijie on 2018/10/10.
 */
@Configuration
public class ThreadPool {
    /**
     * 线程池核心线程数
     */
    private int CORE_POOL_SIZE = 5;
    /**
     * 线程池最大线程数
     */
    private int MAX_POOL_SIZE = 100;
    /**
     * 额外线程空状态生存时间
     */
    private int KEEP_ALIVE_TIME = 10000;
    /**
     * 额外线程空状态生存时间单位秒
     */
    private TimeUnit unit = TimeUnit.SECONDS;
    /**
     * 阻塞队列。当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程。
     */
    private BlockingQueue workQueue = new ArrayBlockingQueue(10);

    /**
     * 线程池对拒绝任务(无线程可用)的处理策略 ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
     */
    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 线程工厂
     */
    private ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "myThreadPool thread:" + integer.getAndIncrement());
        }
    };

    @Bean
    ThreadPoolUtil threadPoolUtil(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                unit, workQueue, threadFactory ,rejectedExecutionHandler);
        ThreadPoolUtil threadPoolUtil = new ThreadPoolUtil(threadPool);
        return threadPoolUtil;
    }

}
