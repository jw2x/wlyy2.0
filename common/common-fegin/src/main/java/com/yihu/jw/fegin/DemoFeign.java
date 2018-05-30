package com.yihu.jw.fegin;

import com.yihu.jw.common.Contants;
import com.yihu.jw.fegin.fallbackfactory.DemoFeignFallbackFactory;
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
        ,fallbackFactory  = DemoFeignFallbackFactory.class
//        ,configuration =   //可以配置当个fegin的配置 例如禁用单个feign的hystrix
)
public interface DemoFeign {

    @RequestMapping(value = "/demo/findByCode", method = RequestMethod.GET)
    String findByCode(@RequestParam(value = "code", required = true) String code);

}
