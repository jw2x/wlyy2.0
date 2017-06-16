package com.yihu.jw.manage.controller.wechat;

import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.wechat.GraphicMessageService;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@RestController
@RequestMapping("/graphicMessage")
@Api(description = "微信图文消息管理")
public class GraphicMessageController {

    @Autowired
    private GraphicMessageService graphicMessageService;

    @GetMapping("/list")
    @ApiOperation(value = "图文消息列表")
    public Envelop list(
            @ApiParam(name = "title", value = "标题", required = false) @RequestParam(required = false, name = "title") String title,
            @ApiParam(name = "description", value = "描述", required = false) @RequestParam(required = false, name = "description") String description,
            @ApiParam(name = "page", value = "当前页", required = false) @RequestParam(required = false, name = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "pageSize", value = "每页显示条数", required = false) @RequestParam(required = false, name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            Page<ManageUser> users = graphicMessageService.list(title, description , pageSize, page);
            return Envelop.getSuccessListWithPage(
                    "获取信息成功",
                    users.getContent(),//数据内容
                    page, //当前页
                    pageSize,//每个显示条数
                    users.getTotalElements()//总数
            );

        } catch (Exception e) {
            return Envelop.getError("获取信息成功:" + e.getMessage(), -1);
        }
    }

    @GetMapping("/getByCode")
    @ApiOperation(value = "根据code查找微信图文消息", notes = "根据code查找微信图文消息")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        return graphicMessageService.findByCode(code);
    }
    @GetMapping("/getByCodea")
    @ApiOperation(value = "根据code查找微信图文消息", notes = "根据code查找微信图文消息")
    public Envelop findByCodea(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        return graphicMessageService.findByCodea(code);
    }

}
