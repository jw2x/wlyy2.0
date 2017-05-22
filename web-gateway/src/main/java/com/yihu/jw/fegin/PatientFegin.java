package com.yihu.jw.fegin;

import com.yihu.jw.commnon.Contants;
import com.yihu.jw.fegin.fallback.PatientFeginFallback;
import com.yihu.jw.fegin.fallbackfactory.PatientFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenweida on 2017/5/10.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
       // ,fallback = PatientFeginFallback.class// fallback是请求超时或者错误的回调函数
        ,fallbackFactory  =PatientFeginFallbackFactory.class
//        ,configuration =   //可以配置当个fegin的配置 例如禁用单个feign的hystrix
)
public interface PatientFegin {

    @RequestMapping(value = Contants.patient.findByCode, method = RequestMethod.GET)
    String findByCode(@RequestParam(value = "code", required = true) String code);

}
