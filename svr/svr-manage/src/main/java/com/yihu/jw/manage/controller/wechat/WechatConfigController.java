package com.yihu.jw.manage.controller.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.model.wechat.WechatConfig;
import com.yihu.jw.manage.service.wechat.WechatConfigService;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@RestController
@RequestMapping("/wechatConfig")
@Api(description = "微信配置管理")
public class WechatConfigController {

    @Autowired
    private WechatConfigService wechatConfigService;

    @GetMapping("/list")
    @ApiOperation(value = "分页获取微信配置列表")
    public Envelop list(
            @ApiParam(name = "name", value = "微信名", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        try {
            Envelop envelop = wechatConfigService.list(name, sorts,length, start);
            return envelop;
        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }


    @DeleteMapping(value = "/wechat/{codes}")
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes
    ) {
        Envelop envelop = wechatConfigService.deleteByCode(codes);
        return envelop;
    }

    @GetMapping(value = "/wechat/{code}")
    @ApiOperation(value = "根据code查找微信配置", notes = "根据code查找微信配置")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
           @PathVariable String code
    ) {
        Envelop envelop = wechatConfigService.findByCode(code);
        return envelop;
    }

    @PostMapping(value = "/wechat")
    @ApiOperation(value = "保存微信配置", notes = "保存微信配置")
    public Envelop save(@ModelAttribute @Valid WechatConfig wechatConfig) throws JsonProcessingException {
        return wechatConfigService.save(wechatConfig);
    }

    @PutMapping(value = "/wechat")
    @ApiOperation(value = "更新微信配置", notes = "更新微信配置")
    public Envelop Update(@ModelAttribute @Valid WechatConfig wechatConfig){
        return wechatConfigService.update(wechatConfig);
    }



    /*@GetMapping("/getByCode")
    @ApiOperation(value = "根据code查找微信图文消息", notes = "根据code查找微信图文消息")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        return wechatConfigService.findByCode(code);
    }
    @GetMapping("/getByCodea")
    @ApiOperation(value = "根据code查找微信图文消息", notes = "根据code查找微信图文消息")
    public Envelop findByCodea(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        return wechatConfigService.findByCodea(code);
    }*/

}
