package com.yihu.jw.base.listener;

import com.yihu.jw.base.activemq.MessageManager;
import com.yihu.jw.base.service.errorCode.ErrorCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Listener - 监听程序启动
 * Created by progr1mmer on 2018/8/31.
 */
@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private MessageManager messageManager;
    @Autowired
    private ErrorCodeService errorCodeService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //mq消息队列
        messageManager.initConsumer();
        //初始化错误码
        errorCodeService.initErrorCode();
    }

}
