package com.yihu.jw.controller.iot.device;

import com.yihu.jw.common.iot.IotCommonContants;
import com.yihu.jw.fegin.iot.device.IotDeviceOrderFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.device.IotDeviceOrderVO;
import com.yihu.jw.restmodel.iot.device.IotOrderPurchaseVO;
import com.yihu.jw.restmodel.iot.device.IotOrderVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotCommonContants.Common.order)
@Api(tags = "设备订单管理相关操作", description = "设备订单管理相关操作")
public class IotDeviceOrderController extends EnvelopRestController{
    @Autowired
    private IotDeviceOrderFeign iotDeviceOrderFeign;

    @PostMapping(value = IotRequestMapping.DeviceOrder.createOrder)
    @ApiOperation(value = "创建设备订单", notes = "创建设备订单")
    public Envelop<IotOrderVO> create(@ApiParam(name = "jsonData", value = "", defaultValue = "")
                          @RequestParam(value = "jsonData", required = true) String jsonData) {
        return iotDeviceOrderFeign.create(jsonData);
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findById)
    @ApiOperation(value = "根据id查找设备订单", notes = "根据id查找设备订单")
    public Envelop<IotDeviceOrderVO>  findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        return iotDeviceOrderFeign.findByCode(id);
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPage)
    @ApiOperation(value = "分页查找设备订单", notes = "分页查找设备订单")
    public Envelop<IotDeviceOrderVO> findPage(@ApiParam(name = "name", value = "供应商名称或负责人姓名", defaultValue = "")
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @ApiParam(name = "type", value = "企业类型", defaultValue = "")
                                                 @RequestParam(value = "type", required = false) String type,
                                                 @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                 @RequestParam(value = "page", required = false) Integer page,
                                                 @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                 @RequestParam(value = "size", required = false) Integer size){
        return iotDeviceOrderFeign.findPage(name,type,page,size);
    }

    @PostMapping(value = IotRequestMapping.DeviceOrder.delOrder)
    @ApiOperation(value = "删除订单", notes = "删除订单")
    public Envelop<IotDeviceOrderVO> delOrder(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        return iotDeviceOrderFeign.delOrder(id);
    }

    @PostMapping(value = IotRequestMapping.DeviceOrder.updOrder)
    @ApiOperation(value = "修改订单", notes = "修改订单")
    public Envelop<IotOrderVO> updOrder(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
        return iotDeviceOrderFeign.updOrder(jsonData);
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPurcharsePage)
    @ApiOperation(value = "分页查找采购清单", notes = "分页查找采购清单")
    public Envelop<IotOrderPurchaseVO> findPurcharsePage(@ApiParam(name = "orderId", value = "订单id", defaultValue = "")
                                                     @RequestParam(value = "orderId", required = true) String orderId,
                                                         @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                     @RequestParam(value = "page", required = false) Integer page,
                                                         @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                     @RequestParam(value = "size", required = false) Integer size){
        return iotDeviceOrderFeign.findPurcharsePage(orderId,page,size);
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPurcharseById)
    @ApiOperation(value = "根据id查找采购订单", notes = "根据id查找采购订单")
    public Envelop<IotOrderPurchaseVO>  findPurcharseById(@ApiParam(name = "id", value = "id")
                                                          @RequestParam(value = "id", required = true) String id
    ) {
        return iotDeviceOrderFeign.findPurcharseById(id);
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findQualityPage)
    @ApiOperation(value = "质检管理", notes = "质检管理")
    public Envelop<IotOrderPurchaseVO> findQualityPage(
            @ApiParam(name = "qualityStatus", value = "质检状态", defaultValue = "")
            @RequestParam(value = "qualityStatus", required = false) String qualityStatus,
            @ApiParam(name = "orderNo", value = "订单编号", defaultValue = "")
            @RequestParam(value = "orderNo", required = false) String orderNo,
            @ApiParam(name = "startTime", value = "开始时间", defaultValue = "")
            @RequestParam(value = "startTime", required = false) String startTime,
            @ApiParam(name = "endTime", value = "结束时间", defaultValue = "")
            @RequestParam(value = "endTime", required = false) String endTime,
            @ApiParam(name = "page", value = "第几页", defaultValue = "")
            @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
            @RequestParam(value = "size", required = false) Integer size){
        return iotDeviceOrderFeign.findQualityPage(qualityStatus, orderNo, startTime, endTime, page, size);
    }
}
