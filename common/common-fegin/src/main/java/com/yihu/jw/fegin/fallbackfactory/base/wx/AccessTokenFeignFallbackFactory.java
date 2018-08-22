package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.fegin.base.wx.AccessTokenFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Component
public class AccessTokenFeignFallbackFactory implements FallbackFactory<AccessTokenFeign> {
    @Override
    public AccessTokenFeign create(Throwable throwable) {
        return new AccessTokenFeign() {
            @Override
            public MixEnvelop getWxAccessToken(@RequestParam(value = "wechatCode") String wechatCode) {
                return null;
            }
        };

    }
}
