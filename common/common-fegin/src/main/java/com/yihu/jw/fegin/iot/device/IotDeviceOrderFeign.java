package com.yihu.jw.fegin.iot.device;

import com.yihu.jw.fegin.fallbackfactory.iot.device.IotDeviceOrderFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceOrderVO;
import com.yihu.jw.restmodel.iot.device.IotOrderPurchaseVO;
import com.yihu.jw.restmodel.iot.device.IotOrderVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    MixEnvelop<IotOrderVO> create(@RequestParam(value = "jsonData", required = true) String jsonData);

    @GetMapping(value = IotRequestMapping.DeviceOrder.findById)
    MixEnvelop<IotDeviceOrderVO> findByCode(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPage)
    MixEnvelop<IotDeviceOrderVO> findPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @PostMapping(value = IotRequestMapping.DeviceOrder.delOrder)
    MixEnvelop<IotDeviceOrderVO> delOrder(@RequestParam(value = "id", required = true) String id);

    @PostMapping(value = IotRequestMapping.DeviceOrder.delPurchase)
    MixEnvelop<IotOrderPurchaseVO> delPurchase(@RequestParam(value = "id", required = true) String id);

    @PostMapping(value = IotRequestMapping.DeviceOrder.updOrder)
    MixEnvelop<IotOrderVO> updOrder(@RequestParam(value = "jsonData", required = false)String jsonData);

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPurcharsePage)
    MixEnvelop<IotOrderPurchaseVO> findPurcharsePage(
            @RequestParam(value = "orderId", required = true) String orderId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPurcharseById)
    MixEnvelop<IotOrderPurchaseVO> findPurcharseById(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.DeviceOrder.findQualityPage)
    MixEnvelop<IotOrderPurchaseVO> findQualityPage(
            @RequestParam(value = "qualityStatus", required = false) String qualityStatus,
            @RequestParam(value = "orderNo", required = false) String orderNo,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);
}
