package com.yihu.jw.fegin.fallbackfactory.iot.common;

import com.yihu.jw.fegin.iot.common.IotSystemDictFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.dict.IotSystemDictVO;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2018/1/20.
 */
@Component
public class IotSystemDictFallbackFactory implements FallbackFactory<IotSystemDictFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public IotSystemDictFeign create(Throwable e) {
        return new IotSystemDictFeign() {
            @Override
            public MixEnvelop<IotSystemDictVO> getList(@RequestParam(value = "dictName", required = true) String dictName) throws Exception {
                tracer.getCurrentSpan().logEvent("获取字典失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("dictName:" + dictName);
                return null;
            }

        };
    }


}
