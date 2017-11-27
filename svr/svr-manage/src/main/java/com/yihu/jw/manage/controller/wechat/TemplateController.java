package com.yihu.jw.manage.controller.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.service.wechat.TemplateService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.base.wx.MWxTemplate;
import com.yihu.jw.rm.base.WechatRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping(WechatRequestMapping.api_common)
@Api(description = "微信模板消息管理")
public class TemplateController {

    @Autowired
    private TemplateService templateService;


    @GetMapping(WechatRequestMapping.WxTemplate.api_getWxTemplates)
    @ApiOperation(value = "分页获取微信模板消息列表")
    public Map<String,Object> list(
            @ApiParam(name = "name", value = "微信名", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "saasId",  required = false) @RequestParam(required = false, name = "saasId") String saasId,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        start=start/length+1;
        Map<String, String> map = new HashMap<>();
        map.put("sorts",sorts);
        map.put("name",name);
        map.put("saasId",saasId);
        Envelop envelop = templateService.list(length, start,map);
        List list = envelop.getDetailModelList();

        //数据返回
        Map<String, Object> req = new HashMap<String, Object>();
        req.put("rows",list);
        req.put("total",envelop.getTotalCount());
        return req;
    }


    @DeleteMapping(value = WechatRequestMapping.WxTemplate.api_delete)
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam String userCode
    ) {
        Envelop envelop = templateService.deleteByCode(codes,userCode);
        return envelop;
    }

    @GetMapping(value = WechatRequestMapping.WxTemplate.api_getById)
    @ApiOperation(value = "根据code查找模板消息", notes = "根据code查找模板消息")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable String code
    ) {
        Envelop envelop = templateService.findByCode(code);
        return envelop;
    }

    @PostMapping(value = WechatRequestMapping.WxTemplate.api_create)
    @ApiOperation(value = "保存或者修改微信模板消息", notes = "保存或者修改微信模板消息")
    public Envelop saveOrUpdate(@ModelAttribute @Valid MWxTemplate template,@RequestParam String userCode) throws JsonProcessingException {
        Envelop envelop = templateService.saveOrUpdate(template,userCode);
        return envelop;
    }

    @GetMapping(value = WechatRequestMapping.WxTemplate.api_getWxTemplatesNoPage)
    @ApiOperation(value = "获取列表(不分页)", notes = "获取列表(不分页)")
    public List getListNoPage(
            @ApiParam(name = "wechatCode", value = "wechatCode")
            @RequestParam String wechatCode
    ){
        Map<String, String> filter = new HashMap<>();
        filter.put("wechatCode",wechatCode);
        Envelop envelop = templateService.getListNoPage(filter);
        List list = envelop.getDetailModelList();
        return list;
    }
}
