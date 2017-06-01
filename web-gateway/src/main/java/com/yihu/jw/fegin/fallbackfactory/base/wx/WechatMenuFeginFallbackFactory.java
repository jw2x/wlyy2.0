package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.fegin.base.wx.WechatMenuFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Component
public class WechatMenuFeginFallbackFactory implements FallbackFactory<WechatMenuFegin> {
    @Override
    public WechatMenuFegin create(Throwable throwable) {
        return new WechatMenuFegin() {
            @Override
            public Envelop createWxMenu(String jsonData) {
                return null;
            }

            @Override
            public Envelop updateWxMenu(String jsonData) {
                return null;
            }

            @Override
            public Envelop deleteWxMenu(String code) {
                return null;
            }

            @Override
            public Envelop findByCode(String code) {
                return null;
            }

            @Override
            public Envelop getWxMenuNoPage(String fields, String filters, String sorts) {
                return null;
            }

            //@Override

            @Override
            public Envelop createWechatMenu(String wechatCode) {
                return null;
            }
            //public Envelop getWxMenus(String fields, String filters, String sorts, int size, int page, HttpServletRequest request, HttpServletResponse response) {
            //    return null;
            //}
        };
    }
}
