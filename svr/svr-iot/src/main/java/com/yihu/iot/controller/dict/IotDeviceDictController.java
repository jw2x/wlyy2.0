package com.yihu.iot.controller.dict;

import com.yihu.iot.service.dict.IotDeviceDictService;
import com.yihu.jw.entity.iot.dict.IotDeviceDictDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotRequestMapping.api_iot_common)
@Api(tags = "设备字典管理相关操作", description = "设备字典管理相关操作")
public class IotDeviceDictController extends EnvelopRestEndpoint {

    @Autowired
    private IotDeviceDictService iotDeviceDictService;

    @GetMapping(value = IotRequestMapping.DeviceDict.api_getList)
    @ApiOperation(value = "获取设备字典列表(不分页)")
    public MixEnvelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,deviceType,name,dataType,dataTypeName")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件",defaultValue = "del=1;deviceType=1")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<IotDeviceDictDO> list = iotDeviceDictService.search(fields,filters,sorts);
        //封装返回格式
        List<IotDeviceDictDO> iotDeviceDicts = convertToModels(list, new ArrayList<>(list.size()), IotDeviceDictDO.class, fields);
        return MixEnvelop.getSuccessList(IotRequestMapping.DeviceDict.message_success_find_functions,iotDeviceDicts);
    }
}
