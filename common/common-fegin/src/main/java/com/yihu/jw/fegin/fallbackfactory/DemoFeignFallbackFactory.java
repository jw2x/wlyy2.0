package com.yihu.jw.fegin.fallbackfactory;

import com.yihu.jw.fegin.DemoFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2017/5/15.
 */
@Component
public class DemoFeignFallbackFactory implements FallbackFactory<DemoFeign> {
    @Override
    public DemoFeign create(Throwable e) {
        return new DemoFeign() {
            public String findByCode(String code) {
                return "启动断路器"+e.getMessage() ;
            }

        };
    }
}
