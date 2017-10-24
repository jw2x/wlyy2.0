package com.yihu.jw.manage.controller.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.service.wechat.WechatConfigService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.base.wx.MWxWechat;
import com.yihu.jw.rm.wx.WechatRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@RestController
@RequestMapping(WechatRequestMapping.api_common)
@Api(description = "微信配置管理")
public class WechatConfigController {

    @Autowired
    private WechatConfigService wechatConfigService;

    @GetMapping(WechatRequestMapping.WxConfig.api_getWechats)
    @ApiOperation(value = "分页获取微信配置列表")
    public Envelop list(
            @ApiParam(name = "name", value = "微信名", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        try {
            start=start/length+1;
            Map<String,String> map = new HashMap<String,String>();
            map.put("name",name);
            map.put("saasId",saasId);
            map.put("sorts",sorts);
            Envelop envelop = wechatConfigService.list(length, start,map);
            return envelop;
        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }


    @DeleteMapping(value = WechatRequestMapping.WxConfig.api_delete)
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam String userCode

    ) {
        Envelop envelop = wechatConfigService.deleteByCode(codes,userCode);
        return envelop;
    }

    @GetMapping(value = WechatRequestMapping.WxConfig.api_getByCode)
    @ApiOperation(value = "根据code查找微信配置", notes = "根据code查找微信配置")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
           @PathVariable String code
    ) {
        Envelop envelop = wechatConfigService.findByCode(code);
        return envelop;
    }

    @PostMapping(value = WechatRequestMapping.WxConfig.api_create)
    @ApiOperation(value = "保存微信配置", notes = "保存微信配置")
    public Envelop save(@ModelAttribute @Valid MWxWechat wechatConfig,@RequestParam String userCode) throws JsonProcessingException {
        return wechatConfigService.saveOrUpdate(wechatConfig, userCode);
    }

    @GetMapping(WechatRequestMapping.WxConfig.api_getWechatNoPage)
    @ApiOperation(value = "获取微信配置列表")
    public Envelop listNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts){
        return wechatConfigService.getListNoPage(fields,null,sorts);
    }
}
