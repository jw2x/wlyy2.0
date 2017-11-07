package com.yihu.jw.feign.fallback;

import com.yihu.jw.feign.PatientFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenweida on 2017/5/13.
 */
@Component
public class PatientFeignFallback implements PatientFeign {

    private Logger logger = LoggerFactory.getLogger(PatientFeignFallback.class);

    @Override
    public String findByCode( @RequestParam(value = "code", required = true) String code) {
        logger.info("进入断路器");
        return "断路器启动";
    }
}
