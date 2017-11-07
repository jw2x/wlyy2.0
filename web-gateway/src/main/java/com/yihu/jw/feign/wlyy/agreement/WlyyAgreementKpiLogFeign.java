package com.yihu.jw.feign.wlyy.agreement;

import com.yihu.jw.feign.fallbackfactory.wlyy.agreement.WlyyAgreementKpiLogFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_wlyy // name值是eurika的实例名字
        ,fallbackFactory  = WlyyAgreementKpiLogFeignFallbackFactory.class
)
@RequestMapping(WlyyRequestMapping.api_common)
public interface WlyyAgreementKpiLogFeign {

    @PostMapping(value = WlyyRequestMapping.AgreementKpiLog.api_create)
    Envelop create(@RequestBody String jsonData);

    @GetMapping(value = WlyyRequestMapping.AgreementKpiLog.api_getByCode)
    Envelop findByCode(@RequestParam(value = "code")String code);

    @RequestMapping(value = WlyyRequestMapping.AgreementKpiLog.api_queryPage, method = RequestMethod.GET)
    Envelop queryPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value = WlyyRequestMapping.AgreementKpiLog.api_getList)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
