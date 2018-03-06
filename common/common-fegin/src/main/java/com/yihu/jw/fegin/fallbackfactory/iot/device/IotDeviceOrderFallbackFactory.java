package com.yihu.jw.fegin.fallbackfactory.iot.device;

import com.yihu.jw.fegin.iot.device.IotDeviceOrderFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceOrderVO;
import com.yihu.jw.restmodel.iot.device.IotOrderPurchaseVO;
import com.yihu.jw.restmodel.iot.device.IotOrderVO;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2017/12/8.
 */
@Component
public class IotDeviceOrderFallbackFactory implements FallbackFactory<IotDeviceOrderFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public IotDeviceOrderFeign create(Throwable e) {
        return new IotDeviceOrderFeign() {

            @Override
            public Envelop<IotOrderVO> create(@RequestParam(value = "jsonData", required = true) String jsonData) {
                tracer.getCurrentSpan().logEvent("创建设备订单失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop<IotDeviceOrderVO>  findByCode(@RequestParam(value = "id", required = true) String id
            ) {
                tracer.getCurrentSpan().logEvent("根据id查找设备订单失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public Envelop<IotDeviceOrderVO> findPage(@RequestParam(value = "name", required = false) String name,
                                                      @RequestParam(value = "type", required = false) String type,
                                                      @RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "size", required = false) Integer size){
                tracer.getCurrentSpan().logEvent("分页查找设备订单失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("name:" + name);
                tracer.getCurrentSpan().logEvent("type:" + type);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }

            @Override
            public Envelop<IotDeviceOrderVO> delOrder(@RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("删除订单失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public Envelop<IotOrderVO> updOrder(@RequestParam(value = "jsonData", required = false)String jsonData) {
                tracer.getCurrentSpan().logEvent("修改订单失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop<IotOrderPurchaseVO> findPurcharsePage(@RequestParam(value = "orderId", required = true) String orderId,
                                                                 @RequestParam(value = "page", required = false) Integer page,
                                                                 @RequestParam(value = "size", required = false) Integer size){
                tracer.getCurrentSpan().logEvent("分页查找采购清单失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("orderId:" + orderId);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }

            @Override
            public Envelop<IotOrderPurchaseVO>  findPurcharseById(@RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("根据id查找采购订单失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }
        };
    }

}
