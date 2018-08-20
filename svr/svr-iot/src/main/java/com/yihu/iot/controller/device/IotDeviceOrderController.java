package com.yihu.iot.controller.device;

import com.yihu.iot.service.company.IotCompanyService;
import com.yihu.iot.service.device.IotDeviceOrderService;
import com.yihu.jw.entity.iot.company.IotCompanyTypeDO;
import com.yihu.jw.entity.iot.device.IotDeviceOrderDO;
import com.yihu.jw.entity.iot.device.IotOrderPurchaseDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.restmodel.iot.company.IotCompanyTypeVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceOrderVO;
import com.yihu.jw.restmodel.iot.device.IotOrderPurchaseVO;
import com.yihu.jw.restmodel.iot.device.IotOrderVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import com.yihu.jw.util.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.order)
@Api(tags = "设备订单管理相关操作", description = "设备订单管理相关操作")
public class IotDeviceOrderController extends EnvelopRestEndpoint {
    @Autowired
    private IotDeviceOrderService iotDeviceOrderService;
    @Autowired
    private IotCompanyService iotCompanyService;

    @PostMapping(value = IotRequestMapping.DeviceOrder.createOrder)
    @ApiOperation(value = "创建设备订单", notes = "创建设备订单")
    public MixEnvelop<IotOrderVO, IotOrderVO> create(@ApiParam(name = "jsonData", value = "", defaultValue = "")
                          @RequestParam String jsonData) {
        try {
            IotOrderVO iotOrderVO = toEntity(jsonData, IotOrderVO.class);
            IotDeviceOrderDO deviceOrderDO = iotDeviceOrderService.create(iotOrderVO);
            return MixEnvelop.getSuccess(deviceOrderDO.getOrderNo());
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findById)
    @ApiOperation(value = "根据id查找设备订单", notes = "根据id查找设备订单")
    public MixEnvelop<IotDeviceOrderVO, IotDeviceOrderVO> findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        try {
            IotDeviceOrderDO iotDeviceOrderDO = iotDeviceOrderService.findById(id);
            IotDeviceOrderVO deviceOrderVO = convertToModel(iotDeviceOrderDO,IotDeviceOrderVO.class);
            //获取企业类型
            if(deviceOrderVO!=null){
                List<IotCompanyTypeDO> companyTypeDOList = iotCompanyService.findTypeByCompanyId(deviceOrderVO.getSupplierId());
                List<IotCompanyTypeVO> companyTypeVOList = convertToModels(companyTypeDOList,new ArrayList<>(companyTypeDOList.size()),IotCompanyTypeVO.class);
                deviceOrderVO.setTypeList(companyTypeVOList);
                if(iotDeviceOrderDO.getPurchaseTime()!=null){
                    deviceOrderVO.setPurchaseTime(DateUtil.dateToStrShort(iotDeviceOrderDO.getPurchaseTime()));
                }
            }
            return MixEnvelop.getSuccess(IotRequestMapping.DeviceOrder.message_success_find,deviceOrderVO);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPage)
    @ApiOperation(value = "分页查找设备订单", notes = "分页查找设备订单")
    public MixEnvelop<IotDeviceOrderVO, IotDeviceOrderVO> findPage(@ApiParam(name = "name", value = "供应商名称或负责人姓名", defaultValue = "")
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
            if(StringUtils.isBlank(type)){
                return iotDeviceOrderService.queryPage(page,size,name);
            }else {
                return iotDeviceOrderService.queryPage(page,size,name,type);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.DeviceOrder.delPurchase)
    @ApiOperation(value = "删除采购订单", notes = "删除采购订单")
    public MixEnvelop<IotOrderPurchaseVO, IotOrderPurchaseVO> delPurchase(@ApiParam(name = "id", value = "id")
                                              @RequestParam(value = "id", required = true) String id) {
        try {
            Integer re =  iotDeviceOrderService.delPurchase(id);
            if(re==1){
                return MixEnvelop.getSuccess(IotRequestMapping.DeviceOrder.message_success_find);
            }else {
                return MixEnvelop.getError(IotRequestMapping.DeviceOrder.delete_purchase_fail_message_device);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.DeviceOrder.delOrder)
    @ApiOperation(value = "删除订单", notes = "删除订单")
    public MixEnvelop<IotDeviceOrderVO, IotDeviceOrderVO> delOrder(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            Integer re =  iotDeviceOrderService.delOrder(id);
            if(re==1){
                return MixEnvelop.getSuccess(IotRequestMapping.DeviceOrder.message_success_find);
            }else {
                return MixEnvelop.getError(IotRequestMapping.DeviceOrder.delete_order_fail_message_device);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.DeviceOrder.updOrder)
    @ApiOperation(value = "修改订单", notes = "修改订单")
    public MixEnvelop<IotOrderVO, IotOrderVO> updOrder(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
        try {
            IotOrderVO iotOrderVO = toEntity(jsonData, IotOrderVO.class);
            iotDeviceOrderService.updOrder(iotOrderVO);
            return MixEnvelop.getSuccess(IotRequestMapping.DeviceOrder.message_success_find);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPurcharsePage)
    @ApiOperation(value = "分页查找采购清单", notes = "分页查找采购清单")
    public MixEnvelop<IotOrderPurchaseVO, IotOrderPurchaseVO> findPurcharsePage(
            @ApiParam(name = "orderId", value = "订单id", defaultValue = "")
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
            return iotDeviceOrderService.queryPurcharsePage(page,size,orderId,"1");
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findQualityPage)
    @ApiOperation(value = "质检管理", notes = "质检管理")
    public MixEnvelop<IotOrderPurchaseVO, IotOrderPurchaseVO> findQualityPage(
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
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return iotDeviceOrderService.queryPurcharsePage(page, size, qualityStatus, orderNo, startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.findPurcharseById)
    @ApiOperation(value = "根据id查找采购订单", notes = "根据id查找采购订单")
    public MixEnvelop<IotOrderPurchaseVO, IotOrderPurchaseVO> findPurcharseById(@ApiParam(name = "id", value = "id")
                                                 @RequestParam(value = "id", required = true) String id
    ) {
        try {
            IotOrderPurchaseDO iotOrderPurchaseDO = iotDeviceOrderService.findPurchaseById(id);
            IotOrderPurchaseVO orderPurchaseVO = convertToModel(iotOrderPurchaseDO,IotOrderPurchaseVO.class);
            if(iotOrderPurchaseDO.getNextQualityTime()!=null){
                orderPurchaseVO.setNextQualityTime(DateUtil.dateToStrShort(iotOrderPurchaseDO.getNextQualityTime()));
            }
            return MixEnvelop.getSuccess(IotRequestMapping.DeviceOrder.message_success_find,orderPurchaseVO);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

}
