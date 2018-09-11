package com.yihu.jw.base.activemq;

import com.yihu.utils.context.SpringContextUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.jms.*;
import javax.jms.IllegalStateException;
import java.util.Set;

/**
 * Runner - 消息执行者
 * Created by progr1mmer on 2018/8/2.
 * @author progr1mmer
 */
public class ConsumerRunner implements Runnable, ExceptionListener {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final String topic;
    private Set<String> pushUrl;
    private QueueConnection connection;
    private Session session;
    private MessageConsumer consumer;
    private RestTemplate restTemplate;

    public ConsumerRunner(String topic, Set<String> pushUrl) {
        Assert.notNull(topic, "Topic cannot be null");
        this.topic = topic;
        this.pushUrl = pushUrl;
        init();
    }

    private void init() {
        try {
            ActiveMQConnectionFactory connectionFactory = SpringContextUtils.getService(ActiveMQConnectionFactory.class);
            // Create a Connection
            connection = connectionFactory.createQueueConnection();
            connection.start();
            connection.setExceptionListener(this);
            // Create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(topic);
            // Create a MessageConsumer from the Session to the Topic or Queue
            consumer = session.createConsumer(destination);
            restTemplate = new RestTemplate();
        } catch (JMSException e) {
            LOGGER.error("Failed to init ConsumerRunner", e);
        }
    }

    private void recover() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = SpringContextUtils.getService(ActiveMQConnectionFactory.class);
        // Create a Connection
        connection = connectionFactory.createQueueConnection();
        connection.start();
        connection.setExceptionListener(this);
        // Create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue(topic);
        // Create a MessageConsumer from the Session to the Topic or Queue
        consumer = session.createConsumer(destination);
        if (null == restTemplate) {
            restTemplate = new RestTemplate();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                // Wait for a message
                Message message = consumer.receive(1000);
                if (message != null) {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        for (String url : pushUrl) {
                            String result = restTemplate.postForObject(url, new HttpEntity<>(text), String.class);
                            LOGGER.info(result);
                        }
                    } else {
                        for (String url : pushUrl) {
                            String result = restTemplate.postForObject(url, new HttpEntity<>(message), String.class);
                            LOGGER.info(result);
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                if (e instanceof IllegalStateException) {
                    try {
                        recover();
                    } catch (JMSException jme) {
                        LOGGER.error("Failed to recover ConsumerRunner", jme);
                    }
                }
            }
        }
    }

    @Override
    public void onException(JMSException e) {
        LOGGER.error("ConsumerRunner onException", e);
    }
}
