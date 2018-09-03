package com.yihu.jw.base.activemq;

import com.yihu.utils.context.SpringContext;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.jms.*;
import java.util.Set;

/**
 * Runner - 消息执行者
 * Created by progr1mmer on 2018/8/2.
 */
public class ConsumerRunner implements Runnable, ExceptionListener {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final String topic;
    private Set<String> pushUrl;
    private TopicConnection topicConnection;
    private MessageConsumer consumer;
    private RestTemplate restTemplate;

    public ConsumerRunner(String topic, Set<String> pushUrl) throws Exception {
        Assert.notNull(topic, "Topic cannot be null");
        this.topic = topic;
        this.pushUrl = pushUrl;
        init();
    }

    private void init() throws Exception {
        ActiveMQConnectionFactory connectionFactory = SpringContext.getService(ActiveMQConnectionFactory.class);
        // Create a Connection
        topicConnection = connectionFactory.createTopicConnection();
        topicConnection.start();
        topicConnection.setExceptionListener(this);
        // Create a Session
        Session session = topicConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Create the destination (Topic or Queue)
        Destination destination = session.createTopic(topic);
        // Create a MessageConsumer from the Session to the Topic or Queue
        consumer = session.createConsumer(destination);
        restTemplate = new RestTemplate();
    }

    @Override
    public void run() {
        while (true) {
            try {
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
            }
        }
    }

    @Override
    public void onException(JMSException e) {
        LOGGER.error("Trying to recover from JMS Connection exception: " + e);
        try {
            topicConnection.close();
            topicConnection.start();
        } catch (Exception ex) {
            LOGGER.error("Failed to recover JMS Connection", ex);
        }
    }
}
