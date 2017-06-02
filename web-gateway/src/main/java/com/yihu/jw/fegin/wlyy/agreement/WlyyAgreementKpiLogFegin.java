package com.yihu.jw.fegin.wlyy.agreement;

import com.yihu.jw.fegin.fallbackfactory.wlyy.agreement.WlyyAgreementKpiLogFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wlyy.WlyyContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_wlyy // name值是eurika的实例名字
        ,fallbackFactory  = WlyyAgreementKpiLogFeginFallbackFactory.class
)
@RequestMapping(WlyyContants.AgreementKpiLog.api_common)
public interface WlyyAgreementKpiLogFegin {

    @PostMapping(value = WlyyContants.AgreementKpiLog.api_create)
    Envelop create(@RequestBody String jsonData);

    @GetMapping(value =WlyyContants.AgreementKpiLog.api_getByCode)
    Envelop findByCode(@RequestParam(value = "code")String code);

    @RequestMapping(value =WlyyContants.AgreementKpiLog.api_queryPage, method = RequestMethod.GET)
    Envelop queryPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value =WlyyContants.AgreementKpiLog.api_getList)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
