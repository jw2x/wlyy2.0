package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.fegin.base.wx.AccessTokenFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Component
public class AccessTokenFeginFallbackFactory implements FallbackFactory< AccessTokenFegin> {
    @Override
    public AccessTokenFegin create(Throwable throwable) {
        return new AccessTokenFegin() {
            @Override
            public Envelop getWxAccessToken(@RequestParam(value = "wechatCode") String wechatCode) {
                return null;
            }
        };

    }
}
