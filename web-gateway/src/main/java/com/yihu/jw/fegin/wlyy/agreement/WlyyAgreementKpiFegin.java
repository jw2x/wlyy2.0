package com.yihu.jw.fegin.wlyy.agreement;

import com.yihu.jw.fegin.fallbackfactory.wlyy.agreement.WlyyAgreementKpiFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wlyy.agreement.WlyyAgreementContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name = CommonContants.svr_wlyy // name值是eurika的实例名字
        ,fallbackFactory  = WlyyAgreementKpiFeginFallbackFactory.class
)
@RequestMapping(WlyyAgreementContants.AgreementKpi.api_common)
public interface WlyyAgreementKpiFegin {

    @PostMapping(value = WlyyAgreementContants.AgreementKpi.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData);

    @PutMapping(value = WlyyAgreementContants.AgreementKpi.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData);

    @DeleteMapping(value = WlyyAgreementContants.AgreementKpi.api_delete)
    Envelop delete(@RequestParam(value = "code" ) String code);

    @GetMapping(value = WlyyAgreementContants.AgreementKpi.api_getByCode)
    Envelop findByCode(@RequestParam(value = "code" ) String code);

    @RequestMapping(value = WlyyAgreementContants.AgreementKpi.api_queryPage, method = RequestMethod.GET)
    Envelop queryPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value = WlyyAgreementContants.AgreementKpi.api_getList)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
