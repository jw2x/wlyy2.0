package com.yihu.ehr.iot.controller.device;

import com.yihu.ehr.iot.controller.common.BaseController;
import com.yihu.ehr.iot.service.device.DeviceOrderService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceOrderVO;
import com.yihu.jw.restmodel.iot.device.IotOrderPurchaseVO;
import com.yihu.jw.restmodel.iot.device.IotOrderVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.order)
@Api(tags = "设备订单管理相关操作", description = "设备订单管理相关操作")
public class IotDeviceOrderController extends BaseController {
    @Autowired
    private DeviceOrderService deviceOrderService;

    @PostMapping(value = IotRequestMapping.DeviceOrder.createOrder, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建设备订单", notes = "创建设备订单")
    public Envelop<IotOrderVO> create(@ApiParam(name = "json_data", value = "", defaultValue = "")
                          @RequestParam String jsonData) {
        try {
            return deviceOrderService.create(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findById)
    @ApiOperation(value = "根据id查找设备订单", notes = "根据id查找设备订单")
    public Envelop<IotDeviceOrderVO>  findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id) {
        try {
            return deviceOrderService.findByCode(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
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
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return deviceOrderService.findPage(name,type,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.DeviceOrder.delOrder)
    @ApiOperation(value = "删除订单", notes = "删除订单")
    public Envelop<IotDeviceOrderVO> delOrder(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            return deviceOrderService.delOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.DeviceOrder.updOrder)
    @ApiOperation(value = "修改订单", notes = "修改订单")
    public Envelop<IotOrderVO> updOrder(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
        try {
            return deviceOrderService.updOrder(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPurcharsePage)
    @ApiOperation(value = "分页查找采购清单", notes = "分页查找采购清单")
    public Envelop<IotOrderPurchaseVO> findPurcharsePage(@ApiParam(name = "orderId", value = "订单id", defaultValue = "")
                                                     @RequestParam(value = "orderId", required = true) String orderId,
                                                         @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                     @RequestParam(value = "page", required = false) Integer page,
                                                         @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                     @RequestParam(value = "size", required = false) Integer size){
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return deviceOrderService.findPurcharsePage(orderId,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

}
