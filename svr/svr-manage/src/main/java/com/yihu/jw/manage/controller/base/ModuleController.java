package com.yihu.jw.manage.controller.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.base.base.ModuleDO;
import com.yihu.jw.manage.service.base.ModuleService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/20.
 */
@RestController
@RequestMapping(BaseRequestMapping.api_base_common)
@Api(description = "模块管理")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @GetMapping(BaseRequestMapping.Module.api_getList)
    @ApiOperation(value = "分页获取模块列表")
    public Map list(
            @ApiParam(name = "name", value = "模块名称", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "saasId", value = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
            @ApiParam(name = "sorts", value = "排序", required = false) @RequestParam(required = false, name = "sorts") String sorts,
            @ApiParam(name = "start", value = "当前页", required = false) @RequestParam(required = false, name = "start", defaultValue = "1") Integer start,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer length
    ) {
        Map<String, Object> req = new HashMap<String, Object>();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("name",name);
            map.put("saasId",saasId);
            map.put("sorts",sorts);
            Envelop envelop = moduleService.list(length, start,map);

            List<Map<String,Object>> list = envelop.getDetailModelList();
            //数据返回
            req.put("rows", list);
            req.put("total",envelop.getTotalCount());
            return req;
        } catch (Exception e) {
            req.put("-1","获取信息失败"+e.getMessage());
            return req;
        }
    }


    @DeleteMapping(value = BaseRequestMapping.Module.api_delete)
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam String userCode
    ) {
        Envelop envelop = moduleService.deleteByCode(codes,userCode);
        return envelop;
    }

    @GetMapping(value = BaseRequestMapping.Module.api_getById)
    @ApiOperation(value = "根据code查找", notes = "根据code查找")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable String code
    ) {
        Envelop envelop = moduleService.findByCode(code);
        return envelop;
    }

    @GetMapping(value = BaseRequestMapping.Module.api_getChildren)
    @ApiOperation(value = "查找子节点", notes = "查找子节点")
    public List<Map<String,Object>> getChildren(@PathVariable String code){
        Envelop envelop = moduleService.getChildren(code);
        if(envelop.getObj()==null){
            return new ArrayList<>();
        }
        return (List)envelop.getObj();
    }

    @PostMapping(BaseRequestMapping.Module.api_create)
    @ApiOperation(value = "保存/更新", notes = "保存/更新")
    public Envelop saveOrUpdate(@ModelAttribute @Valid ModuleDO module, String userCode) throws JsonProcessingException {
        return moduleService.saveOrUpdate(module,userCode);
    }

    @GetMapping(BaseRequestMapping.Module.api_getListNoPage)
    @ApiOperation(value = "获取模块列表")
    public List getListNoPage(@ApiParam(name = "saasId", value = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId) {
        Map<String, Object> req = new HashMap<String, Object>();
        Map<String, String> map = new HashMap<>();
        map.put("saasId",saasId);
        Envelop envelop = moduleService.getListNoPage(map);

        List<Map<String,Object>> list = envelop.getDetailModelList();
        return list;
    }

    @GetMapping(BaseRequestMapping.ModuleFun.api_getExistFun)
    @ApiOperation(value = "获取已经存在功能")
    public List<String> getExistFun(@ApiParam(name = "code", value = "模块code", required = true) @PathVariable(required = true, name = "code") String code){
        List<String> funcs = moduleService.getExistFun(code);
        return funcs;
    }

    @PutMapping(value = BaseRequestMapping.ModuleFun.api_changeFun)
    @ApiOperation(value = "模块更新功能")
    public Envelop changeFun (@RequestParam String funCodes,
                              @RequestParam String moduleCode) {
        moduleService.changeFun(moduleCode, funCodes);
        return Envelop.getSuccess("更新成功", null);

    }

}
