package com.yihu.jw.base.endpoint.sms;

import com.yihu.jw.base.service.sms.SmsService;
import com.yihu.jw.entity.base.sms.SmsDO;
import com.yihu.jw.restmodel.base.sms.SmsVO;
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

import java.util.List;

/**
 * Endpoint - 短信记录
 * Created by progr1mmer on 2018/9/6.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Sms.PREFIX)
@Api(value = "短信记录管理", description = "短信记录管理服务接口", tags = {"wlyy基础服务 - 短信记录管理服务接口"})
public class SmsEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SmsService smsService;

    @PostMapping(value = BaseRequestMapping.Sms.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SmsVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SmsDO smsDO = toEntity(jsonData, SmsDO.class);
        smsDO = smsService.save(smsDO);
        return success(smsDO, SmsVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Sms.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        smsService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Sms.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<SmsVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SmsDO smsDO = toEntity(jsonData, SmsDO.class);
        if (null == smsDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        smsDO = smsService.save(smsDO);
        return success(smsDO, SmsVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Sms.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SmsVO> page (
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
        List<SmsDO> smsDOS = smsService.search(fields, filters, sorts, page, size);
        int count = (int)smsService.getCount(filters);
        return success(smsDOS, count, page, size, SmsVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Sms.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SmsVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SmsDO> smsDOS = smsService.search(fields, filters, sorts);
        return success(smsDOS, SmsVO.class);
    }

}
