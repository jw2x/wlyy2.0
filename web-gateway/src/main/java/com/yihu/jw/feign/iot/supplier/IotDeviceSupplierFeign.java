package com.yihu.jw.feign.iot.supplier;

import com.yihu.jw.feign.fallbackfactory.iot.supplier.IotDeviceSupplierFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2017/12/5.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotDeviceSupplierFeignFallbackFactory.class
)
@RequestMapping(IotRequestMapping.api_iot_common)
public interface IotDeviceSupplierFeign {

    @PostMapping(value = IotRequestMapping.DeviceSupplier.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop create(@RequestBody String jsonData);

    @PutMapping(value = IotRequestMapping.DeviceSupplier.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop update(@RequestBody String jsonData);

    @DeleteMapping(value = IotRequestMapping.DeviceSupplier.api_delete)
    Envelop delete( @RequestParam(value = "id") String id);

    @RequestMapping(value= IotRequestMapping.DeviceSupplier.api_getById,method = RequestMethod.GET)
    Envelop findByCode( @RequestParam(value = "id" ) String id);

    @RequestMapping(value = IotRequestMapping.DeviceSupplier.api_queryPage, method = RequestMethod.GET)
    Envelop queryPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @GetMapping(value = IotRequestMapping.DeviceSupplier.api_getList)
    Envelop getList(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);
}
