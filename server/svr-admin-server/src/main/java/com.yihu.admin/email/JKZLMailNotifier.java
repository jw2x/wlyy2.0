package com.yihu.admin.email;

import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.notify.MailNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by chenweida on 2018/5/8 0008.
 * de.codecentric.boot.admin.config.NotifierConfiguration
 */
public class JKZLMailNotifier extends MailNotifier {


    private static final String DEFAULT_SUBJECT = "#{application.name} (#{application.id}) is #{to.status}";
    private static final String DEFAULT_TEXT = "#{application.name} (#{application.id})\nstatus changed from #{from.status} to #{to.status}\n\n#{application.healthUrl}";

    private final SpelExpressionParser parser = new SpelExpressionParser();

    private Logger logger = LoggerFactory.getLogger(JKZLMailNotifier.class);

    private final MailSender sender;

    /**
     * recipients of the mail
     */
    private String to[] = {"root@localhost"};

    /**
     * cc-recipients of the mail
     */
    private String cc[];

    /**
     * sender of the change
     */
    private String from = null;

    /**
     * Mail Text. SpEL template using event as root;
     */
    private Expression text;

    /**
     * Mail Subject. SpEL template using event as root;
     */
    private Expression subject;

    public JKZLMailNotifier(MailSender sender) {
        super(sender);
        logger.info("init email");
        this.sender = sender;
        this.subject = parser.parseExpression(DEFAULT_SUBJECT, ParserContext.TEMPLATE_EXPRESSION);
        this.text = parser.parseExpression(DEFAULT_TEXT, ParserContext.TEMPLATE_EXPRESSION);

    }

    @Override
    protected void doNotify(ClientApplicationEvent event) {
        logger.info("send email");
        EvaluationContext context = new StandardEvaluationContext(event);

        SimpleMailMessage message = new SimpleMailMessage();
        //发送给别人  支持多个接收人
        message.setTo(to);
        //发送人 可配置
        message.setFrom(from);
        //发送的邮件名称  默认是 微服务ID + 状态
        // 例如
        // SPRING-CLOUD-SVR-SIMPLE1 (9ac26abe) is OFFLINE
        message.setSubject(subject.getValue(context, String.class));
        //邮件内容  默认是微服务 +状态
        // 例如
        // SPRING-CLOUD-SVR-SIMPLE1 (9ac26abe) status changed from UNKNOWN to OFFLINE
        // http://MS-20180328WODJ:8080/health
        message.setText(text.getValue(context, String.class));
        // 抄送  为null  不需要填写
        message.setCc(cc);

        sender.send(message);
    }

    public void setTo(String[] to) {
        this.to = Arrays.copyOf(to, to.length);
    }

    public String[] getTo() {
        return Arrays.copyOf(to, to.length);
    }

    public void setCc(String[] cc) {
        this.cc = Arrays.copyOf(cc, cc.length);
    }

    public String[] getCc() {
        return Arrays.copyOf(cc, cc.length);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setSubject(String subject) {
        this.subject = parser.parseExpression(subject, ParserContext.TEMPLATE_EXPRESSION);
    }

    public String getSubject() {
        return subject.getExpressionString();
    }

    public void setText(String text) {
        this.text = parser.parseExpression(text, ParserContext.TEMPLATE_EXPRESSION);
    }

    public String getText() {
        return text.getExpressionString();
    }
}
