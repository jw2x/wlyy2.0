package com.yihu.iot.controller.device;

import com.yihu.iot.service.device.IotDeviceQualityInspectionPlanService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.iot.device.IotDeviceQualityInspectionPlanDO;
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
@Api(tags = "设备质检管理相关操作", description = "设备质检管理相关操作")
public class IotDeviceQualityInspectionPlanController extends EnvelopRestController{
    @Autowired
    private IotDeviceQualityInspectionPlanService iotDeviceQualityInspectionPlanService;

    @PostMapping(value = IotRequestMapping.DeviceQualityInspectionPlan.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建设备质检", notes = "创建设备质检")
    public Envelop create(@ApiParam(name = "json_data", value = "", defaultValue = "")
                          @RequestBody String jsonData) {
        try {
            IotDeviceQualityInspectionPlanDO iotDeviceQualityInspectionPlan = toEntity(jsonData, IotDeviceQualityInspectionPlanDO.class);
            return Envelop.getSuccess(IotRequestMapping.DeviceQualityInspectionPlan.message_success_create, iotDeviceQualityInspectionPlanService.create(iotDeviceQualityInspectionPlan));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = IotRequestMapping.DeviceQualityInspectionPlan.api_getById)
    @ApiOperation(value = "根据code查找设备质检", notes = "根据code查找设备质检")
    public Envelop findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return Envelop.getSuccess(IotRequestMapping.DeviceQualityInspectionPlan.message_success_find, iotDeviceQualityInspectionPlanService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = IotRequestMapping.DeviceQualityInspectionPlan.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取设备质检")
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
        List<IotDeviceQualityInspectionPlanDO> list = iotDeviceQualityInspectionPlanService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=iotDeviceQualityInspectionPlanService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<IotDeviceQualityInspectionPlanDO> iotDeviceQualityInspectionPlans = convertToModels(list, new ArrayList<>(list.size()), IotDeviceQualityInspectionPlanDO.class, fields);

        return Envelop.getSuccessListWithPage(IotRequestMapping.DeviceQualityInspectionPlan.message_success_find_functions,iotDeviceQualityInspectionPlans, page, size,count);
    }


    @GetMapping(value = IotRequestMapping.DeviceQualityInspectionPlan.api_getList)
    @ApiOperation(value = "获取设备质检列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段(id,supplierName,type,contactsName,contactsMobile)", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "-createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<IotDeviceQualityInspectionPlanDO> list = iotDeviceQualityInspectionPlanService.search(fields,filters,sorts);
        //封装返回格式
        List<IotDeviceQualityInspectionPlanDO> iotDeviceQualityInspectionPlans = convertToModels(list, new ArrayList<>(list.size()), IotDeviceQualityInspectionPlanDO.class, fields);
        return Envelop.getSuccessList(IotRequestMapping.DeviceQualityInspectionPlan.message_success_find_functions,iotDeviceQualityInspectionPlans);
    }
}
