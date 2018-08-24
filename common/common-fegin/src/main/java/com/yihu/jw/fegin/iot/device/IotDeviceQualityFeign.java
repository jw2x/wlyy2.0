package com.yihu.jw.fegin.iot.device;

import com.yihu.jw.fegin.fallbackfactory.iot.device.IotDeviceQualityFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceQualityInspectionPlanVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2018/01/24.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotDeviceQualityFallbackFactory.class
)
@RequestMapping(IotRequestMapping.Common.quality)
public interface IotDeviceQualityFeign{

    @PostMapping(value = IotRequestMapping.DeviceQuality.addQualityPlan)
    MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> create(@RequestParam(value = "jsonData", required = true) String jsonData);

    @GetMapping(value = IotRequestMapping.DeviceQuality.findById)
    MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> findByCode(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.DeviceQuality.queryQualityPlanPage)
    MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> queryQualityPlanPage(
           @RequestParam(value = "purcharseId", required = false) String purcharseId,
           @RequestParam(value = "orderNo", required = false) String orderNo,
           @RequestParam(value = "startTime", required = false) String startTime,
           @RequestParam(value = "endTime", required = false) String endTime,
           @RequestParam(value = "page", required = false) Integer page,
           @RequestParam(value = "size", required = false) Integer size);

    @PostMapping(value = IotRequestMapping.DeviceQuality.delQualityPlan)
    MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> delQualityPlan(@RequestParam(value = "id", required = true) String id);

    @PostMapping(value = IotRequestMapping.DeviceQuality.completeQualityPlan)
    MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> completeQualityPlan(
        @RequestParam(value = "actualTime", required = true) String actualTime,
        @RequestParam(value = "id", required = true) String id);


    @PostMapping(value = IotRequestMapping.DeviceQuality.completePlanByPurchaseId)
    MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> completePlanByPurchaseId(
            @RequestParam(value = "actualTime", required = true) String actualTime,
            @RequestParam(value = "purchaseId", required = true) String purchaseId);
}
