package com.yihu.jw.feign.fallbackfactory.base.wx;

import com.yihu.jw.feign.base.wx.AccessTokenFeign;
import com.yihu.jw.restmodel.common.Envelop;
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
            public Envelop getWxAccessToken(@RequestParam(value = "wechatCode") String wechatCode) {
                return null;
            }
        };

    }
}
