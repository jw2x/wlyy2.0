package com.yihu.iot.controller.device;

import com.yihu.iot.service.device.IotDeviceOrderService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.iot.device.IotDeviceOrderDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotRequestMapping.api_iot_common)
@Api(tags = "设备订单管理相关操作", description = "设备订单管理相关操作")
public class IotDeviceOrderController extends EnvelopRestController{
    @Autowired
    private IotDeviceOrderService iotDeviceOrderService;

    @PostMapping(value = IotRequestMapping.DeviceOrder.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建设备订单", notes = "创建设备订单")
    public Envelop<IotDeviceOrderDO> create(@ApiParam(name = "json_data", value = "", defaultValue = "")
                          @RequestBody String jsonData) {
        try {
            IotDeviceOrderDO iotDeviceOrder = toEntity(jsonData, IotDeviceOrderDO.class);
            return Envelop.getSuccess(IotRequestMapping.DeviceOrder.message_success_create, iotDeviceOrderService.create(iotDeviceOrder));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceOrder.api_getById)
    @ApiOperation(value = "根据code查找设备订单", notes = "根据code查找设备订单")
    public Envelop<IotDeviceOrderDO>  findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return Envelop.getSuccess(IotRequestMapping.DeviceOrder.message_success_find, iotDeviceOrderService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = IotRequestMapping.DeviceOrder.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取设备订单")
    public Envelop<IotDeviceOrderDO>  queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段(id,supplierName,type,contactsName,contactsMobile)", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件(supplierName?中 g1;contactsName?中 g1)")
            @RequestParam(value = "filters", required = false,defaultValue = "") String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "-createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) Integer page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //得到list数据
        List<IotDeviceOrderDO> list = iotDeviceOrderService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=iotDeviceOrderService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<IotDeviceOrderDO> iotDeviceOrders = convertToModels(list, new ArrayList<>(list.size()), IotDeviceOrderDO.class, fields);

        return Envelop.getSuccessListWithPage(IotRequestMapping.DeviceOrder.message_success_find_functions,iotDeviceOrders, page, size,count);
    }


    @GetMapping(value = IotRequestMapping.DeviceOrder.api_getList)
    @ApiOperation(value = "获取设备订单列表(不分页)")
    public Envelop<IotDeviceOrderDO>  getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段(id,supplierName,type,contactsName,contactsMobile)", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "-createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<IotDeviceOrderDO> list = iotDeviceOrderService.search(fields,filters,sorts);
        //封装返回格式
        List<IotDeviceOrderDO> iotDeviceOrders = convertToModels(list, new ArrayList<>(list.size()), IotDeviceOrderDO.class, fields);
        return Envelop.getSuccessList(IotRequestMapping.DeviceOrder.message_success_find_functions,iotDeviceOrders);
    }
}
