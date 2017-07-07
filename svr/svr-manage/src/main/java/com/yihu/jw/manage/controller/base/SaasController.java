package com.yihu.jw.manage.controller.base;

import com.yihu.jw.manage.service.base.SaasService;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@RestController
@RequestMapping("/base")
@Api(description = "saas相关")
public class SaasController {

    @Autowired
    private SaasService saasService;

    @GetMapping("/saases")
    @ApiOperation(value = "分页获取微信配置列表")
    public Envelop list(
            @ApiParam(name = "name", value = "name", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        try {
            Envelop envelop = saasService.list(name, sorts,length, start);
            return envelop;
        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }

    @GetMapping("/saases/list")
    @ApiOperation(value = "获取微信配置列表(不分页)")
    public List getListNoPage(){
        return saasService.getListNoPage();
    }
}
