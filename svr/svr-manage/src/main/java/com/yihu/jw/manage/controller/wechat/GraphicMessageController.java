package com.yihu.jw.manage.controller.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.model.wechat.GraphicMessage;
import com.yihu.jw.manage.service.wechat.GraphicMessageService;
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
@RequestMapping("/wechat")
@Api(description = "微信图文消息管理")
public class GraphicMessageController {

    @Autowired
    private GraphicMessageService graphicMessageService;

    @GetMapping("graphicMessage/list")
    @ApiOperation(value = "分页获取微信图文列表")
    public Envelop list(
            @ApiParam(name = "title", value = "标题", required = false) @RequestParam(required = false, name = "title") String title,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        try {
            Envelop envelop = graphicMessageService.list(title, sorts,length, start);
            return envelop;
        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }


    @DeleteMapping(value = "/graphicMessage/{codes}")
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes
    ) {
        Envelop envelop = graphicMessageService.deleteByCode(codes);
        return envelop;
    }

    @GetMapping(value = "/graphicMessage/{code}")
    @ApiOperation(value = "根据code查找图文消息", notes = "根据code查找图文消息")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable String code
    ) {
        Envelop envelop = graphicMessageService.findByCode(code);
        return envelop;
    }

    @PostMapping(value = "/graphicMessage")
    @ApiOperation(value = "保存或者修改微信图文消息", notes = "保存或者修改微信图文消息")
    public Envelop saveOrUpdate(@ModelAttribute @Valid GraphicMessage graphicMessage) throws JsonProcessingException {
        return graphicMessageService.saveOrUpdate(graphicMessage);
    }
}
