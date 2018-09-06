package com.yihu.jw.base.service.sms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.base.dao.sms.SmsDao;
import com.yihu.jw.base.dao.sms.SmsGatewayDao;
import com.yihu.jw.base.dao.sms.SmsTemplateDao;
import com.yihu.jw.entity.base.sms.SmsDO;
import com.yihu.jw.entity.base.sms.SmsGatewayDO;
import com.yihu.jw.entity.base.sms.SmsTemplateDO;
import com.yihu.jw.exception.ApiException;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.utils.network.HttpResponse;
import com.yihu.utils.network.HttpUtils;
import com.yihu.utils.network.IPInfoUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service - 短信网关
 * Created by progr1mmer on 2018/8/23.
 */
@Service
public class SmsGatewayService extends BaseJpaService<SmsGatewayDO, SmsGatewayDao> {

    private static final Pattern PATTERN  = Pattern.compile("\\$\\{[A-Za-z0-9]+\\}");

    @Autowired
    private SmsTemplateDao smsTemplateDao;
    @Autowired
    private SmsGatewayDao smsGatewayDao;
    @Autowired
    private SmsDao smsDao;
    @Autowired
    private ObjectMapper objectMapper;

    public SmsDO send(String clientId, SmsTemplateDO.Type type, String to) throws Exception {
        List<SmsGatewayDO> smsGatewayDOS = smsGatewayDao.findByClientIdAndStatus(clientId, SmsGatewayDO.Status.available);
        if (smsGatewayDOS.size() == 0) {
            throw new ApiException("no gateway available");
        }
        List<SmsTemplateDO> smsTemplateDOS = smsTemplateDao.findByClientIdAndType(clientId, type);
        if (smsTemplateDOS.size() == 0) {
            throw new ApiException("no template available");
        }
        Random random = new Random();
        SmsGatewayDO smsGatewayDO = smsGatewayDOS.get(random.nextInt(smsGatewayDOS.size()));
        SmsTemplateDO smsTemplateDO = smsTemplateDOS.get(random.nextInt(smsTemplateDOS.size()));
        //生成内容
        String rawContent = smsTemplateDO.getContent();
        /*
         * 0 - 验证码
         * 1 - 过期时间（分钟）
         */
        String [] contentDatas = new String[] {
                randomInt(6),
                smsGatewayDO.getExpireMin().toString()
        };
        Matcher contentMatcher = PATTERN.matcher(rawContent);
        int index = 0;
        while (contentMatcher.find()) {
            String matchWord = contentMatcher.group(0);
            rawContent = rawContent.replace(matchWord, contentDatas[index]);
            index ++;
        }
        String sendContent = smsTemplateDO.getHeader() + rawContent;
        //第三方API服务请求参数
        String rawCertificate = smsGatewayDO.getRequestCertificate();
        /*
         * 0 - 接收手机号码
         * 1 - 短信内容
         */
        String [] certificateDatas = new String[] {
                to,
                sendContent
        };
        Matcher certificateMatcher = PATTERN.matcher(rawCertificate);
        index = 0;
        while (certificateMatcher.find()) {
            String matchWord = certificateMatcher.group(0);
            rawCertificate = rawCertificate.replace(matchWord, certificateDatas[index]);
            index ++;
        }
        HttpResponse httpResponse = HttpUtils.doPost(smsGatewayDO.getRequestUrl(), objectMapper.readValue(rawCertificate, Map.class));
        if (httpResponse.isSuccessFlg()) {
            Map<String, Object> response = objectMapper.readValue(httpResponse.getContent(), Map.class);
            String responseCode = String.valueOf(response.get(smsGatewayDO.getResponseCode()));
            if (responseCode.equals(smsGatewayDO.getSuccessValue())) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                SmsDO smsDO = new SmsDO();
                smsDO.setClientId(clientId);
                smsDO.setSmsGatewayId(smsGatewayDO.getId());
                smsDO.setRequestIp(IPInfoUtils.getIPAddress(request));
                smsDO.setMobile(to);
                smsDO.setContent(sendContent);
                smsDO.setDeadline(DateUtils.addMinutes(new Date(), smsGatewayDO.getExpireMin()));
                smsDO.setCaptcha(contentDatas[0]);
                smsDO.setType(type);
                return smsDao.save(smsDO);
            } else {
                throw new ApiException(httpResponse.getContent());
            }
        } else {
            throw new ApiException(httpResponse.getContent());
        }
    }

    public static void main(String [] args) {
        Object obj = 1;
        System.out.println(obj.toString());
    }

}
