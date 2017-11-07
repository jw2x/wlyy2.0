package com.yihu.jw.feign.fallbackfactory;

import com.yihu.jw.feign.PatientFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2017/5/15.
 */
@Component
public class PatientFeignFallbackFactory implements FallbackFactory<PatientFeign> {
    @Override
    public PatientFeign create(Throwable e) {
        return new PatientFeign() {
            public String findByCode(String code) {
                return "启动断路器"+e.getMessage() ;
            }

        };
    }
}
