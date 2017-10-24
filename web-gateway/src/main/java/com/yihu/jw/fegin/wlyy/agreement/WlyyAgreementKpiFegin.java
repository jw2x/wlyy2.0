package com.yihu.jw.fegin.wlyy.agreement;

import com.yihu.jw.fegin.fallbackfactory.wlyy.agreement.WlyyAgreementKpiFeginFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_wlyy // name值是eurika的实例名字
        ,fallbackFactory  = WlyyAgreementKpiFeginFallbackFactory.class
)
@RequestMapping(WlyyRequestMapping.api_common)
public interface WlyyAgreementKpiFegin {

    @PostMapping(value = WlyyRequestMapping.AgreementKpi.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData);

    @PutMapping(value = WlyyRequestMapping.AgreementKpi.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData);

    @DeleteMapping(value = WlyyRequestMapping.AgreementKpi.api_delete)
    Envelop delete(@RequestParam(value = "code" ) String code);

    @GetMapping(value = WlyyRequestMapping.AgreementKpi.api_getByCode)
    Envelop findByCode(@RequestParam(value = "code" ) String code);

    @RequestMapping(value = WlyyRequestMapping.AgreementKpi.api_queryPage, method = RequestMethod.GET)
    Envelop queryPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value = WlyyRequestMapping.AgreementKpi.api_getList)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
