package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.fegin.base.wx.WechatFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class WechatFeginFallbackFactory implements FallbackFactory<WechatFegin> {
    @Override
    public WechatFegin create(Throwable e) {
        return new WechatFegin() {
            public Envelop createWechat(String jsonData) {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }
            public Envelop updateWechat(String jsonData) {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }
            public Envelop deleteWechat(String code){
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }

            public Envelop findByCode(String code){
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }

            public Envelop getWechats(String fields, String filters, String sorts, int size, int page){
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }

            public Envelop getWechatNoPage(String fields, String filters, String sorts){
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }
        };
    }

    public static void main(String[] args) {
        String a ="{\\\"appId\\\":\\\"aaa\\\",\\\"appSecret\\\":\\\"aaa\\\",\\\"baseUrl\\\":\\\"\\\",\\\"code\\\":\\\"d\\\",\\\"createTime\\\":null,\\\"createUser\\\":\\\"\\\",\\\"createUserName\\\":\\\"\\\",\\\"encType\\\":0,\\\"encodingAesKey\\\":\\\"\\\",\\\"id\\\":7,\\\"name\\\":\\\"健康之路i健康awef\\\",\\\"remark\\\":\\\"\\\",\\\"saasId\\\":\\\"1\\\",\\\"status\\\":2,\\\"token\\\":\\\"\\\",\\\"type\\\":\\\"2\\\",\\\"updateTime\\\":null,\\\"updateUser\\\":\\\"\\\",\\\"updateUserName\\\":\\\"\\\"}";
        System.out.println(a);
        a=a.replaceAll("\\\\\"","\"");
        System.out.println(a);
    }
}
