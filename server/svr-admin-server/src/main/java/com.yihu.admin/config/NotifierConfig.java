package com.yihu.admin.config;

import com.yihu.admin.email.JKZLMailNotifier;
import de.codecentric.boot.admin.config.NotifierConfiguration;
import de.codecentric.boot.admin.notify.RemindingNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * Created by chenweida on 2018/5/10 0010.
 */
@Configuration
@AutoConfigureAfter({MailSenderAutoConfiguration.class})
@AutoConfigureBefore({NotifierConfiguration.MailNotifierConfiguration.class})
public class NotifierConfig {
    @Autowired
    private MailSender mailSender;

    @Bean
    @Primary
    public RemindingNotifier remindingNotifier() {
        RemindingNotifier remindingNotifier = new RemindingNotifier(mailNotifier());
        //默认情况下，每5分钟发送一次提醒
        remindingNotifier.setReminderPeriod(TimeUnit.MINUTES.toMillis(5));
        return remindingNotifier;
    }
    

    public NotifierConfig() {
        System.out.println("初始化数据");
    }

    @Bean("mailNotifier")
    @ConfigurationProperties("spring.boot.admin.notify.mail")
    public JKZLMailNotifier mailNotifier() {
        return new JKZLMailNotifier(mailSender);
    }
}
