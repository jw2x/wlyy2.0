package com.yihu.jw.feign.iot.device;

import com.yihu.jw.feign.fallbackfactory.iot.device.IotDeviceOrderFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceOrderVO;
import com.yihu.jw.restmodel.iot.device.IotOrderPurchaseVO;
import com.yihu.jw.restmodel.iot.device.IotOrderVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2018/01/20.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotDeviceOrderFallbackFactory.class
)
@RequestMapping(IotRequestMapping.Common.order)
public interface IotDeviceOrderFeign {

    @PostMapping(value = IotRequestMapping.DeviceOrder.createOrder)
    public Envelop<IotOrderVO> create(@RequestParam(value = "jsonData", required = true) String jsonData);

    @GetMapping(value = IotRequestMapping.DeviceOrder.findById)
    public Envelop<IotDeviceOrderVO> findByCode(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPage)
    public Envelop<IotDeviceOrderVO> findPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @PostMapping(value = IotRequestMapping.DeviceOrder.delOrder)
    public Envelop<IotDeviceOrderVO> delOrder(@RequestParam(value = "id", required = true) String id);

    @PostMapping(value = IotRequestMapping.DeviceOrder.updOrder)
    public Envelop<IotOrderVO> updOrder(@RequestParam(value = "jsonData", required = false)String jsonData);

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPurcharsePage)
    public Envelop<IotOrderPurchaseVO> findPurcharsePage(
            @RequestParam(value = "orderId", required = true) String orderId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

}
