package com.yihu.jw.base.endpoint.sms;

import com.yihu.jw.base.service.SmsTemplateService;
import com.yihu.jw.entity.base.sms.SmsTemplateDO;
import com.yihu.jw.restmodel.base.sms.SmsTemplateVO;
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
 * Endpoint - 短信模板
 * Created by progr1mmer on 2018/8/23.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.SmsTemplate.PREFIX)
@Api(value = "短信模板管理", description = "短信模板管理服务接口", tags = {"wlyy基础服务 - 短信模板管理服务接口"})
public class SmsTemplateEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SmsTemplateService smsTemplateService;

    @PostMapping(value = BaseRequestMapping.SmsTemplate.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SmsTemplateVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SmsTemplateDO smsTemplateDO = toEntity(jsonData, SmsTemplateDO.class);
        smsTemplateDO = smsTemplateService.save(smsTemplateDO);
        return success(convertToModel(smsTemplateDO, SmsTemplateVO.class));
    }

    @PostMapping(value = BaseRequestMapping.SmsTemplate.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        smsTemplateService.delete(ids);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.SmsTemplate.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<SmsTemplateVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SmsTemplateDO smsTemplateDO = toEntity(jsonData, SmsTemplateDO.class);
        if (null == smsTemplateDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        smsTemplateDO = smsTemplateService.save(smsTemplateDO);
        return success(convertToModel(smsTemplateDO, SmsTemplateVO.class));
    }

    @GetMapping(value = BaseRequestMapping.SmsTemplate.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SmsTemplateVO> page (
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
        List<SmsTemplateDO> smsTemplateDOS = smsTemplateService.search(fields, filters, sorts, page, size);
        int count = (int)smsTemplateService.getCount(filters);
        return success(smsTemplateDOS, count, page, size, SmsTemplateVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SmsTemplate.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SmsTemplateVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SmsTemplateDO> smsTemplateDOS = smsTemplateService.search(fields, filters, sorts);
        return success(smsTemplateDOS, SmsTemplateVO.class);
    }

}
