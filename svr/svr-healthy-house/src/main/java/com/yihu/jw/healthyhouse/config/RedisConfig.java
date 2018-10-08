package com.yihu.jw.healthyhouse.config;

import com.yihu.jw.healthyhouse.cache.Receiver;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * @author HZY
 * @created 2018/10/8 15:12
 */
@Configuration
public class RedisConfig  {
    /*
     * Redis消息监听器容器
     * 这个容器加载了RedisConnectionFactory和消息监听器
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("sprinboot-redis-messaage"));
        return container;
    }

    /*
     * 将Receiver注册为一个消息监听器，并指定消息接收的方法（receiveMessage）
     * 如果不指定消息接收的方法，消息监听器会默认的寻找Receiver中的handleMessage这个方法作为消息接收的方法
     */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /*
     * Receiver实例
     */
    @Bean
    Receiver receiver(CountDownLatch latch){
        return new Receiver(latch);
    }

    @Bean
    CountDownLatch latch(){
        return new CountDownLatch(1);
    }

    /*
     * Redis Template 用来发送消息
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }


}
