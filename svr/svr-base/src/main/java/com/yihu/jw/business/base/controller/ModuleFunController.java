package com.yihu.jw.business.base.controller;

import com.yihu.jw.business.base.service.ModuleFunService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
@RestController
@RequestMapping(BaseRequestMapping.api_base_common)
@Api(value = "模块功能模块", description = "模块功能模块")
public class ModuleFunController extends EnvelopRestController {
    @Autowired
    private ModuleFunService moduleFunService;

    @GetMapping(value = BaseRequestMapping.ModuleFun.api_getExistFun)
    @ApiOperation(value="查找已存在的功能")
    public Envelop getExistFunc(@PathVariable String id){
        List<String> funcs = moduleFunService.getExistFun(id);
        return Envelop.getSuccess("查询成功",funcs);
    }

    @PutMapping(value =  BaseRequestMapping.ModuleFun.api_changeFun,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "模块更新功能")
    public Envelop changeFun (@RequestBody String jsonData) throws JSONException {
        moduleFunService.changeFun(jsonData);
        return Envelop.getSuccess("更新成功",null);
    }
}
