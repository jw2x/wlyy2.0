package com.yihu.jw.fegin.fallback;

import com.yihu.jw.fegin.PatientFegin;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenweida on 2017/5/13.
 */
@Component
public class PatientFeginFallback implements PatientFegin {

    @Override
    public String findByCode(String code) {
        return "断路器启动";
    }
}
