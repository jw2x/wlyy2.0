package com.yihu.iot.controller.supplier;

import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotRequestMapping.api_iot_common)
@Api(value = "供应设备数据类型管理相关操作", description = "供应设备数据类型管理相关操作")
public class IotSupplyDeviceDataTypeController extends EnvelopRestController{

}
