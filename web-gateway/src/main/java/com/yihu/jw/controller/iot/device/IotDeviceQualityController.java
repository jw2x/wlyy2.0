package com.yihu.jw.controller.iot.device;

import com.yihu.jw.commnon.iot.IotCommonContants;
import com.yihu.jw.feign.iot.device.IotDeviceQualityFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.device.IotDeviceQualityInspectionPlanVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2018/01/24.
 */
@RestController
@RequestMapping(IotCommonContants.Common.quality)
@Api(tags = "设备质检管理相关操作", description = "设备质检管理相关操作")
public class IotDeviceQualityController extends EnvelopRestController{
    @Autowired
    private IotDeviceQualityFeign iotDeviceQualityFeign;

    @PostMapping(value = IotRequestMapping.DeviceQuality.addQualityPlan)
    @ApiOperation(value = "创建设备质检", notes = "创建设备质检")
    public Envelop<IotDeviceQualityInspectionPlanVO> create(@ApiParam(name = "jsonData", value = "", defaultValue = "")
                          @RequestParam(value = "jsonData", required = true) String jsonData) {
        return iotDeviceQualityFeign.create(jsonData);
    }

    @GetMapping(value = IotRequestMapping.DeviceQuality.findById)
    @ApiOperation(value = "根据code查找设备质检", notes = "根据code查找设备质检")
    public Envelop<IotDeviceQualityInspectionPlanVO> findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        return iotDeviceQualityFeign.findByCode(id);
    }


    @GetMapping(value = IotRequestMapping.DeviceQuality.queryQualityPlanPage)
    @ApiOperation(value = "分页获取设备质检计划", notes = "分页获取设备质检计划")
    public Envelop<IotDeviceQualityInspectionPlanVO> queryQualityPlanPage(@ApiParam(name = "purcharseId", value = "采购id", defaultValue = "")
                                                           @RequestParam(value = "purcharseId", required = true) String purcharseId,
                                                           @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                           @RequestParam(value = "page", required = false) Integer page,
                                                           @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                           @RequestParam(value = "size", required = false) Integer size){
        return iotDeviceQualityFeign.queryQualityPlanPage(purcharseId,page,size);
    }

    @PostMapping(value = IotRequestMapping.DeviceQuality.delQualityPlan)
    @ApiOperation(value = "删除质检计划", notes = "删除质检计划")
    public Envelop<IotDeviceQualityInspectionPlanVO> delQualityPlan(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        return iotDeviceQualityFeign.delQualityPlan(id);
    }

    @PostMapping(value = IotRequestMapping.DeviceQuality.completeQualityPlan)
    @ApiOperation(value = "完成质检计划", notes = "完成质检计划")
    public Envelop<IotDeviceQualityInspectionPlanVO> completeQualityPlan(@ApiParam(name = "actualTime", value = "完成时间", defaultValue = "")
                                            @RequestParam(value = "actualTime", required = true) String actualTime,
                                            @ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        return iotDeviceQualityFeign.completeQualityPlan(actualTime,id);
    }

}
