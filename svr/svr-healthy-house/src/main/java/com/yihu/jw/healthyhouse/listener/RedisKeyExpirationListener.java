package com.yihu.jw.healthyhouse.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author HZY
 * @created 2018/10/8 15:11
 */
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
//      System.out.println(new String(message.getBody()));
//      System.out.println(new String(message.getChannel()));
//      System.out.println(new String(pattern));
//      super.onMessage(message, pattern);
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        System.out.println(expiredKey);
    }

}
