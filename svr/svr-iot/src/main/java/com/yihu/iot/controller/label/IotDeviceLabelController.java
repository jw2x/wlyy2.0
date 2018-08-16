package com.yihu.iot.controller.label;

import com.yihu.iot.service.label.IotDeviceLabelService;
import com.yihu.jw.entity.iot.label.IotDeviceLabelDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
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
 * @author yeshijie on 2018/1/4.
 */
@RestController
@RequestMapping(IotRequestMapping.api_iot_common)
@Api(tags = "设备标签相关操作", description = "设备标签相关操作")
public class IotDeviceLabelController extends EnvelopRestEndpoint {

    @Autowired
    private IotDeviceLabelService iotDeviceLabelService;

    @PostMapping(value = IotRequestMapping.DeviceLabel.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建设备标签", notes = "创建设备标签")
    public MixEnvelop create(@ApiParam(name = "json_data", value = "", defaultValue = "")
                          @RequestBody String jsonData) {
        try {
            IotDeviceLabelDO iotDevice = toEntity(jsonData, IotDeviceLabelDO.class);
            return MixEnvelop.getSuccess(IotRequestMapping.DeviceLabel.message_success_create, iotDeviceLabelService.create(iotDevice));
        } catch (Exception e) {
            return MixEnvelop.getError(e.getMessage());
        }
    }


    @GetMapping(value = IotRequestMapping.DeviceLabel.api_getById)
    @ApiOperation(value = "根据code查找设备标签", notes = "根据code查找设备标签")
    public MixEnvelop findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return MixEnvelop.getSuccess(IotRequestMapping.DeviceLabel.message_success_find, iotDeviceLabelService.findById(id));
        } catch (Exception e) {
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @RequestMapping(value = IotRequestMapping.DeviceLabel.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取设备标签")
    public MixEnvelop queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段(id)", defaultValue = "")
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
        List<IotDeviceLabelDO> list = iotDeviceLabelService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=iotDeviceLabelService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<IotDeviceLabelDO> iotDevices = convertToModels(list, new ArrayList<>(list.size()), IotDeviceLabelDO.class, fields);

        return MixEnvelop.getSuccessListWithPage(IotRequestMapping.DeviceLabel.message_success_find_functions,iotDevices, page, size,count);
    }


    @GetMapping(value = IotRequestMapping.DeviceLabel.api_getList)
    @ApiOperation(value = "获取设备标签列表(不分页)")
    public MixEnvelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段(id)", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "-createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<IotDeviceLabelDO> list = iotDeviceLabelService.search(fields,filters,sorts);
        //封装返回格式
        List<IotDeviceLabelDO> iotDevices = convertToModels(list, new ArrayList<>(list.size()), IotDeviceLabelDO.class, fields);
        return MixEnvelop.getSuccessList(IotRequestMapping.DeviceLabel.message_success_find_functions,iotDevices);
    }
}
