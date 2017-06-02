package com.yihu.jw.fegin.fallbackfactory.wlyy.agreement;

import com.yihu.jw.fegin.wlyy.agreement.WlyySignFamilyFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public class WlyySignFamilyFeginFallbackFactory implements FallbackFactory<WlyySignFamilyFegin>{

    @Override
    public WlyySignFamilyFegin create(Throwable throwable) {
        return new WlyySignFamilyFegin() {
            @Override
            public Envelop create(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public Envelop update(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public Envelop delete(@RequestParam(value = "code") String code) {
                return null;
            }

            @Override
            public Envelop findByCode(@RequestParam(value = "code") String code) {
                return null;
            }

            @Override
            public Envelop queryPage(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts, @RequestParam(value = "size", required = false) int size, @RequestParam(value = "page", required = false) int page, HttpServletRequest request, HttpServletResponse response) {
                return null;
            }

            @Override
            public Envelop getList(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts) {
                return null;
            }
        };
    }
}
