package com.yihu.jw.manage.controller.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.service.base.FunctionService;
import com.yihu.jw.restmodel.base.base.FunctionVO;
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
@Api(description = "功能管理")
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    @GetMapping(BaseRequestMapping.Function.api_getList)
    @ApiOperation(value = "分页获取功能列表")
    public Map list(
            @ApiParam(name = "name", value = "功能名称", required = false) @RequestParam(required = false, name = "name") String name,
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
            Envelop envelop = functionService.list(length, start,map);

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


    @DeleteMapping(value = BaseRequestMapping.Function.api_delete)
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam String userCode
    ) {
        Envelop envelop = functionService.deleteByCode(codes,userCode);
        return envelop;
    }

    @GetMapping(value = BaseRequestMapping.Function.api_getById)
    @ApiOperation(value = "根据code查找", notes = "根据code查找")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable String code
    ) {
        Envelop envelop = functionService.findByCode(code);
        return envelop;
    }

    @GetMapping(value = BaseRequestMapping.Function.api_getChildren)
    @ApiOperation(value = "查找子节点", notes = "查找子节点")
    public List<Map<String,Object>> getChildren(@PathVariable String code){
        Envelop envelop = functionService.getChildren(code);
        if(envelop.getObj()==null){
            return new ArrayList<>();
        }
        return (List)envelop.getObj();
    }

    @PostMapping(BaseRequestMapping.Function.api_create)
    @ApiOperation(value = "保存/更新", notes = "保存/更新")
    public Envelop saveOrUpdate(@ModelAttribute @Valid FunctionVO func, String userCode) throws JsonProcessingException {
        return functionService.saveOrUpdate(func,userCode);
    }

    @GetMapping(BaseRequestMapping.Function.api_getListNoPage)
    @ApiOperation(value = "获取功能列表")
    public List getListNoPage(@ApiParam(name = "saasId", value = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
                              @ApiParam(name = "parentCode", value = "parentCode", required = false) @RequestParam(required = false, name = "parentCode") String parentCode
    ) {
        Map<String, Object> req = new HashMap<String, Object>();
        Map<String, String> map = new HashMap<>();
        map.put("saasId",saasId);
        map.put("parentCode",parentCode);
        Envelop envelop = functionService.getListNoPage(map);

        List<Map<String,Object>> list = envelop.getDetailModelList();
        return list;
    }


}
