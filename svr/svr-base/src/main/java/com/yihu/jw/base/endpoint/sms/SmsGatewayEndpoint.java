package com.yihu.jw.base.endpoint.sms;

import com.yihu.jw.base.service.SmsGatewayService;
import com.yihu.jw.entity.base.sms.SmsGatewayDO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Endpoint - 短信网关
 * Created by progr1mmer on 2018/8/23.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.SmsGateway.PREFIX)
@Api(value = "短信网关管理", description = "短信网关管理服务接口", tags = {"wlyy基础服务 - 短信网关管理服务接口"})
public class SmsGatewayEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SmsGatewayService smsGatewayService;

    @PostMapping(value = BaseRequestMapping.SmsGateway.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SmsGatewayDO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SmsGatewayDO smsGatewayDO = toEntity(jsonData, SmsGatewayDO.class);
        smsGatewayDO = smsGatewayService.save(smsGatewayDO);
        return success(convertToModel(smsGatewayDO, SmsGatewayDO.class));
    }

    @PostMapping(value = BaseRequestMapping.SmsGateway.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        smsGatewayService.delete(ids);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.SmsGateway.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<SmsGatewayDO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SmsGatewayDO smsGatewayDO = toEntity(jsonData, SmsGatewayDO.class);
        if (null == smsGatewayDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        smsGatewayDO = smsGatewayService.save(smsGatewayDO);
        return success(convertToModel(smsGatewayDO, SmsGatewayDO.class));
    }

    @GetMapping(value = BaseRequestMapping.SmsGateway.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SmsGatewayDO> page (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        List<SmsGatewayDO> smsGatewayDOS = smsGatewayService.search(fields, filters, sorts, page, size);
        int count = (int)smsGatewayService.getCount(filters);
        return success(convertToModels(smsGatewayDOS, new ArrayList<>(), SmsGatewayDO.class), count, page, size);
    }

    @GetMapping(value = BaseRequestMapping.SmsGateway.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SmsGatewayDO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SmsGatewayDO> smsGatewayDOS = smsGatewayService.search(fields, filters, sorts);
        return success(convertToModels(smsGatewayDOS, new ArrayList<>(), SmsGatewayDO.class));
    }

    @GetMapping(value = BaseRequestMapping.SmsGateway.SEND)
    @ApiOperation(value = "发送短信")
    public Envelop send (
            @ApiParam(name = "clientId", value = "应用ID", required = true)
            @RequestParam(value = "clientId") String clientId,
            @ApiParam(name = "type", value = "短信标签类型", required = true)
            @RequestParam(value = "type") Integer type,
            @ApiParam(name = "mobile", value = "手机号码", required = true)
            @RequestParam(value = "mobile") String mobile) {

        return success("");
    }

}
