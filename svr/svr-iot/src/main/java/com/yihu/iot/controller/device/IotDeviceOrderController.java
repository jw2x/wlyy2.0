package com.yihu.iot.controller.device;

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
@Api(value = "设备订单管理相关操作", description = "设备订单管理相关操作")
public class IotDeviceOrderController extends EnvelopRestController{

}
