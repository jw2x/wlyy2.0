package com.yihu.jw.fegin.fallbackfactory.base.base;

import com.yihu.jw.fegin.base.login.LoginFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.status.EnvelopStatus;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

/**
 * Created by 刘文彬 on 2018/5/8.
 */
@Component
public class LoginFeignFallbackFactory implements FallbackFactory<LoginFeign> {

    @Autowired
    private Tracer tracer;
    @Override
    public LoginFeign create(Throwable e) {

        return new LoginFeign() {
            /**
             * 注册校验姓名、身份证、医保卡号信息
             * @param ssc
             * @param idcard
             * @return
             */
            @Override
            public Envelop checkoutInfo(String ssc, String idcard) {
                tracer.getCurrentSpan().logEvent("校验医保卡、身份证、手机号接口错误:原因:"+e.getMessage());
                return MixEnvelop.getError(e.getMessage(), EnvelopStatus.system_error.value);
            }

            /**
             *注册账号
             * @param mobilePhone
             * @param saasId
             * @param type
             * @param captcha
             * @param name
             * @param password
             * @param idcard
             * @param ssc
             * @return
             */
            @Override
            public MixEnvelop register(String mobilePhone, String saasId, int type, String captcha, String name, String password, String idcard, String ssc) {
                tracer.getCurrentSpan().logEvent("注册账号接口错误:原因:"+e.getMessage());
                return MixEnvelop.getError(e.getMessage(), EnvelopStatus.system_error.value);
            }

            /**
             * 登录账号
             * @param mobilePhone
             * @param password
             * @param saasId
             * @param captcha
             * @return
             */
            @Override
            public MixEnvelop login(String mobilePhone, String password, String saasId, String captcha) {
                tracer.getCurrentSpan().logEvent("登录账号接口错误:原因:"+e.getMessage());
                return MixEnvelop.getError(e.getMessage(), EnvelopStatus.system_error.value);
            }
        };
    }
}
