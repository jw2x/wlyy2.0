package com.yihu.jw.feign;

import com.yihu.jw.commnon.Contants;
import com.yihu.jw.feign.fallbackfactory.PatientFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
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
        ,fallbackFactory  = PatientFeignFallbackFactory.class
//        ,configuration =   //可以配置当个fegin的配置 例如禁用单个feign的hystrix
)
public interface PatientFeign {

    @RequestMapping(value = Contants.patient.findByCode, method = RequestMethod.GET)
    String findByCode(@RequestParam(value = "code", required = true) String code);

}
