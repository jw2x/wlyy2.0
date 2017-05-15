package com.yihu.jw.fegin.fallbackfactory;

import com.yihu.jw.fegin.PatientFegin;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenweida on 2017/5/15.
 */
@Component
public class PatientFeginFallbackFactory implements FallbackFactory<PatientFegin> {
    @Override
    public PatientFegin create(Throwable e) {
        return new PatientFegin() {
            public String findByCode(String code) {
                return "启动断路器"+e.getMessage() ;
            }

        };
    }
}
