package com.yihu.jw.manage.controller.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.base.wx.WxGraphicMessageDO;
import com.yihu.jw.manage.service.wechat.GraphicMessageService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.rm.base.WechatRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@RestController
@RequestMapping(WechatRequestMapping.api_common)
@Api(description = "微信图文消息管理")
public class GraphicMessageController {

    @Autowired
    private GraphicMessageService graphicMessageService;

    @GetMapping(WechatRequestMapping.WxGraphicMessage.api_getWxGraphicMessages)
    @ApiOperation(value = "分页获取微信图文列表")
    public Envelop list(
            @ApiParam(name = "title", value = "标题", required = false) @RequestParam(required = false, name = "title") String title,
            @ApiParam(name = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        try {
            start=start/length+1;
            Map<String, String> map = new HashMap<>();
            map.put("title",title);
            map.put("saasId",saasId);
            map.put("sorts",sorts);
            Envelop envelop = graphicMessageService.list(length, start,map);
            return envelop;
        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }


    @DeleteMapping(value = WechatRequestMapping.WxGraphicMessage.api_delete)
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam String userCode
    ) {
        Envelop envelop = graphicMessageService.deleteByCode(codes,userCode);
        return envelop;
    }

    @GetMapping(value = WechatRequestMapping.WxGraphicMessage.api_getById)
    @ApiOperation(value = "根据code查找图文消息", notes = "根据code查找图文消息")
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @PathVariable String id
    ) {
        Envelop envelop = graphicMessageService.findByCode(id);
        return envelop;
    }

    @PostMapping(value = WechatRequestMapping.WxGraphicMessage.api_create)
    @ApiOperation(value = "保存或者修改微信图文消息", notes = "保存或者修改微信图文消息")
    public Envelop saveOrUpdate(@ModelAttribute @Valid WxGraphicMessageDO graphicMessage, @RequestParam String userCode) throws JsonProcessingException {
        return graphicMessageService.saveOrUpdate(graphicMessage,userCode);
    }
}
