package com.yihu.jw.controller.iot.supplier;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.iot.SupplierContants;
import com.yihu.jw.feign.iot.supplier.IotDeviceSupplierFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2017/12/5.
 */
@RestController
@RequestMapping(SupplierContants.DeviceSupplier.api_common)
@Api(value = "供应商相关操作", description = "供应商相关操作")
public class IotDeviceSupplierController extends EnvelopRestController{

    private Logger logger = LoggerFactory.getLogger(IotDeviceSupplierController.class);

    @Autowired
    private IotDeviceSupplierFeign iotDeviceSupplierFeign;

    @Autowired
    private Tracer tracer;

    @PostMapping(value = SupplierContants.DeviceSupplier.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建供应商", notes = "创建供应商")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return iotDeviceSupplierFeign.create(jsonData);
    }

    @PutMapping(value = SupplierContants.DeviceSupplier.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改供应商", notes = "修改供应商")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop update(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return iotDeviceSupplierFeign.update(jsonData);
    }

    @DeleteMapping(value =SupplierContants.DeviceSupplier.api_delete)
    @ApiOperation(value = "删除供应商", notes = "删除供应商")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop delete(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        return iotDeviceSupplierFeign.delete(code);
    }

    @GetMapping(value =SupplierContants.DeviceSupplier.api_getByCode)
    @ApiOperation(value = "根据code查找供应商", notes = "根据code查找供应商")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        return iotDeviceSupplierFeign.findByCode(code);
    }

    @RequestMapping(value = SupplierContants.DeviceSupplier.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取供应商")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page) throws Exception {
        return iotDeviceSupplierFeign.queryPage(fields,filters,sorts,size,page);
    }


    @GetMapping(value =SupplierContants.DeviceSupplier.api_getList)
    @ApiOperation(value = "获取供应商列表(不分页)")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,parentCode,saasId,name,price,posterPic,remark,type,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        return iotDeviceSupplierFeign.getList(fields,filters,sorts);
    }

}
