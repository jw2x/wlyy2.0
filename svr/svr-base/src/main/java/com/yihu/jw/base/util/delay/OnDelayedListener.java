package com.yihu.jw.base.util.delay;

/**
 * @author yeshijie on 2018/10/9.
 */
public interface OnDelayedListener {
    /**
     * 延迟时间到了，执行处理订单
     * @param notice
     */
    void onDelayedArrived(Notice notice);
}
