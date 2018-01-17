package com.yihu.iot.controller.device;

import com.yihu.iot.service.device.IotDeviceService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.iot.device.IotDeviceDO;
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
@Api(tags = "设备管理相关操作", description = "设备管理相关操作")
public class IotDeviceController extends EnvelopRestController{
    @Autowired
    private IotDeviceService iotDeviceService;

    @PostMapping(value = IotRequestMapping.Device.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建设备", notes = "创建设备")
    public Envelop create(@ApiParam(name = "json_data", value = "", defaultValue = "")
                          @RequestBody String jsonData) {
        try {
            IotDeviceDO iotDevice = toEntity(jsonData, IotDeviceDO.class);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create, iotDeviceService.create(iotDevice));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @GetMapping(value = IotRequestMapping.Device.api_getById)
    @ApiOperation(value = "根据code查找设备", notes = "根据code查找设备")
    public Envelop findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_find, iotDeviceService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = IotRequestMapping.Device.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取设备")
    public Envelop queryPage(
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
        List<IotDeviceDO> list = iotDeviceService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=iotDeviceService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<IotDeviceDO> iotDevices = convertToModels(list, new ArrayList<>(list.size()), IotDeviceDO.class, fields);

        return Envelop.getSuccessListWithPage(IotRequestMapping.Device.message_success_find_functions,iotDevices, page, size,count);
    }


    @GetMapping(value = IotRequestMapping.Device.api_getList)
    @ApiOperation(value = "获取设备列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段(id,supplierName,type,contactsName,contactsMobile)", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "-createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<IotDeviceDO> list = iotDeviceService.search(fields,filters,sorts);
        //封装返回格式
        List<IotDeviceDO> iotDevices = convertToModels(list, new ArrayList<>(list.size()), IotDeviceDO.class, fields);
        return Envelop.getSuccessList(IotRequestMapping.Device.message_success_find_functions,iotDevices);
    }
}
