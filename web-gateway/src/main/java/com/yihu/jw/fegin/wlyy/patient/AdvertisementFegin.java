package com.yihu.jw.fegin.wlyy.patient;

import com.yihu.jw.fegin.fallbackfactory.wlyy.patient.AdvertisementFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wlyy.patient.WlyyPatientContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@FeignClient(
        name = CommonContants.svr_wlyy // name值是eurika的实例名字
        ,fallbackFactory  = AdvertisementFeginFallbackFactory.class
)
@RequestMapping(WlyyPatientContants.Advertisement.api_common)
public interface AdvertisementFegin {

    @PostMapping(value = WlyyPatientContants.Advertisement.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData);

    @PutMapping(value = WlyyPatientContants.Advertisement.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData);

    @DeleteMapping(value = WlyyPatientContants.Advertisement.api_delete)
    Envelop delete( @RequestParam(value = "code") String code);

    @RequestMapping(value= WlyyPatientContants.Advertisement.api_getByCode,method = RequestMethod.GET)
    Envelop findByCode( @RequestParam(value = "code" ) String code);

    @RequestMapping(value = WlyyPatientContants.Advertisement.api_queryPage, method = RequestMethod.GET)
    Envelop queryPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value = WlyyPatientContants.Advertisement.api_getList)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);

    @GetMapping(value= WlyyPatientContants.Advertisement.api_getListByPatientCode)
    Envelop getListByPatientCode(String patientCode);
}
