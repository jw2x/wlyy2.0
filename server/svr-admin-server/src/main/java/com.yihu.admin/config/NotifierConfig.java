package com.yihu.admin.config;

import com.yihu.admin.email.JKZLMailNotifier;
import de.codecentric.boot.admin.config.NotifierConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

/**
 * Created by chenweida on 2018/5/10 0010.
 */
@Configuration
@AutoConfigureAfter({MailSenderAutoConfiguration.class})
@AutoConfigureBefore({NotifierConfiguration.MailNotifierConfiguration.class})
public class NotifierConfig {
    @Autowired
    private MailSender mailSender;

    public NotifierConfig() {
        System.out.println("初始化数据");
    }

    @Bean("mailNotifier")
    @ConfigurationProperties("spring.boot.admin.notify.mail")
    public JKZLMailNotifier mailNotifier() {
        return new JKZLMailNotifier(mailSender);
    }
}
