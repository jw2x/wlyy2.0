package com.yihu.jw.fegin.fallbackfactory.iot.device;

import com.yihu.jw.fegin.iot.device.IotDeviceQualityFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceQualityInspectionPlanVO;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2018/01/24.
 */
@Component
public class IotDeviceQualityFallbackFactory implements FallbackFactory<IotDeviceQualityFeign> {
    @Autowired
    private Tracer tracer;

    @Override
    public IotDeviceQualityFeign create(Throwable e) {
        return new IotDeviceQualityFeign() {

            @Override
            public Envelop<IotDeviceQualityInspectionPlanVO> create(@RequestParam(value = "jsonData", required = true) String jsonData) {
                tracer.getCurrentSpan().logEvent("创建质检计划失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop<IotDeviceQualityInspectionPlanVO> findByCode(@RequestParam(value = "id", required = true) String id
            ) {
                tracer.getCurrentSpan().logEvent("查找质检计划失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public Envelop<IotDeviceQualityInspectionPlanVO> queryQualityPlanPage(
                    @RequestParam(value = "purcharseId", required = false) String purcharseId,
                    @RequestParam(value = "page", required = false) Integer page,
                    @RequestParam(value = "size", required = false) Integer size){
                tracer.getCurrentSpan().logEvent("分页获取设备质检计划失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("purcharseId:" + purcharseId);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }

            @Override
            public Envelop<IotDeviceQualityInspectionPlanVO> delQualityPlan(
                    @RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("删除质检计划失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public Envelop<IotDeviceQualityInspectionPlanVO> completeQualityPlan(
                        @RequestParam(value = "actualTime", required = true) String actualTime,
                        @RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("完成质检计划失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("actualTime:" + actualTime);
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }
        };
    }



}
