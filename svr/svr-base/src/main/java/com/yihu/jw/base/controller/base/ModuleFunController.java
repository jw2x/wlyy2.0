package com.yihu.jw.base.controller.base;

import com.yihu.jw.base.service.base.ModuleFunService;
import com.yihu.jw.restmodel.base.base.BaseContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
@RestController
@RequestMapping(BaseContants.api_common)
@Api(value = "模块功能模块", description = "模块功能模块")
public class ModuleFunController extends EnvelopRestController {
    @Autowired
    private ModuleFunService moduleFunService;

    @GetMapping(value = BaseContants.ModuleFun.api_getExistFun)
    @ApiOperation(value="查找已存在的功能")
    public Envelop getExistFunc(@PathVariable String code){
        List<String> funcs = moduleFunService.getExistFun(code);
        return Envelop.getSuccess("查询成功",funcs);
    }

    @PutMapping(value =  BaseContants.ModuleFun.api_changeFun,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "模块更新功能")
    public Envelop changeFun (@RequestBody String jsonData) {
        moduleFunService.changeFun(jsonData);
        return Envelop.getSuccess("更新成功",null);
    }
}
