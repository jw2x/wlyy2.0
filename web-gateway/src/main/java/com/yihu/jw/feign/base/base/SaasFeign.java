package com.yihu.jw.feign.base.base;

import com.yihu.jw.feign.fallbackfactory.base.base.SaasFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = SaasFeignFallbackFactory.class
)
@RequestMapping(value = BaseRequestMapping.api_base_common)
public interface SaasFeign {

    @GetMapping(value = BaseRequestMapping.Saas.api_getSaassNoPage)
    Envelop getList(@RequestParam(value = "fields",required = false)String fields,@RequestParam(value = "filters",required = false) String filters,@RequestParam(value = "sorts",required = false) String sorts);
}
