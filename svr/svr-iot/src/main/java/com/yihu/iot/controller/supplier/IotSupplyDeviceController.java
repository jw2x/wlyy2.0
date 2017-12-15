package com.yihu.iot.controller.supplier;

import com.yihu.iot.service.supplier.IotSupplyDeviceService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.iot.supplier.IotSupplyDeviceDO;
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
@Api(value = "供应设备-供应设备管理相关操作", description = "供应设备-供应设备管理相关操作")
public class IotSupplyDeviceController extends EnvelopRestController{

    @Autowired
    private IotSupplyDeviceService iotSupplyDeviceService;

    @PostMapping(value = IotRequestMapping.SupplyDevice.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建供应设备", notes = "创建供应设备")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            IotSupplyDeviceDO iotSupplyDevice = toEntity(jsonData, IotSupplyDeviceDO.class);
            return Envelop.getSuccess(IotRequestMapping.SupplyDevice.message_success_create, iotSupplyDeviceService.create(iotSupplyDevice));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = IotRequestMapping.SupplyDevice.api_getById)
    @ApiOperation(value = "根据code查找供应设备", notes = "根据code查找供应设备")
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return Envelop.getSuccess(IotRequestMapping.SupplyDevice.message_success_find, iotSupplyDeviceService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = IotRequestMapping.SupplyDevice.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取供应设备")
    public Envelop queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,supplierName,type,contactsName,contactsMobile")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false,defaultValue = "name?中 g1;deviceModel?中 g1") String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //得到list数据
        List<IotSupplyDeviceDO> list = iotSupplyDeviceService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=iotSupplyDeviceService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<IotSupplyDeviceDO> iotSupplyDevices = convertToModels(list, new ArrayList<>(list.size()), IotSupplyDeviceDO.class, fields);

        return Envelop.getSuccessListWithPage(IotRequestMapping.SupplyDevice.message_success_find_functions,iotSupplyDevices, page, size,count);
    }


    @GetMapping(value = IotRequestMapping.SupplyDevice.api_getList)
    @ApiOperation(value = "获取供应设备列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,supplierName,type,contactsName,contactsMobile")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<IotSupplyDeviceDO> list = iotSupplyDeviceService.search(fields,filters,sorts);
        //封装返回格式
        List<IotSupplyDeviceDO> iotSupplyDevices = convertToModels(list, new ArrayList<>(list.size()), IotSupplyDeviceDO.class, fields);
        return Envelop.getSuccessList(IotRequestMapping.SupplyDevice.message_success_find_functions,iotSupplyDevices);
    }
}
