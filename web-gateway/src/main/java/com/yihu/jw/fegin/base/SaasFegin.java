package com.yihu.jw.fegin.base;

import com.yihu.jw.fegin.fallbackfactory.base.SaasFeginFallbackFactory;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = SaasFeginFallbackFactory.class
)
@RequestMapping(value = BaseContants.Saas.api_common)
public interface SaasFegin {

    @GetMapping(value = BaseContants.Saas.api_getSaassNoPage)
    Envelop getList(@RequestParam(value = "fields",required = false)String fields,@RequestParam(value = "filters",required = false) String filters,@RequestParam(value = "sorts",required = false) String sorts);
}
