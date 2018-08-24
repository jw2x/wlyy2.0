//package com.yihu.jw.base.endpoint.module;
//
//import com.yihu.jw.base.service.ModuleFunctionService;
//import com.yihu.jw.entity.base.module.ModuleFunctionDO;
//import com.yihu.jw.restmodel.web.Envelop;
//import com.yihu.jw.restmodel.web.ListEnvelop;
//import com.yihu.jw.restmodel.web.ObjEnvelop;
//import com.yihu.jw.restmodel.web.PageEnvelop;
//import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
//import com.yihu.jw.rm.base.BaseRequestMapping;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Created by progr1mmer on 2018/8/16.
// */
//@RestController
//@RequestMapping(value = BaseRequestMapping.ModuleFunction.PREFIX)
//@Api(value = "模块功能管理", description = "模块功能管理服务接口", tags = {"wlyy基础服务 - 模块功能管理服务接口"})
//public class ModuleFunctionEndpoint extends EnvelopRestEndpoint {
//
//    @Autowired
//    private ModuleFunctionService moduleFunctionService;
//
//    @PostMapping(value = BaseRequestMapping.ModuleFunction.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "创建")
//    public ObjEnvelop<ModuleFunctionDO> create (
//            @ApiParam(name = "json_data", value = "Json数据", required = true)
//            @RequestBody String jsonData) throws Exception {
//        ModuleFunctionDO moduleFunctionDO = toEntity(jsonData, ModuleFunctionDO.class);
//        moduleFunctionDO = moduleFunctionService.save(moduleFunctionDO);
//        return success(moduleFunctionDO);
//    }
//
//    @PostMapping(value = BaseRequestMapping.ModuleFunction.DELETE)
//    @ApiOperation(value = "删除")
//    public Envelop delete(
//            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
//            @RequestParam(value = "ids") String ids) {
//        moduleFunctionService.delete(ids);
//        return success("删除成功");
//    }
//
//    @PostMapping(value = BaseRequestMapping.ModuleFunction.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "更新")
//    public Envelop update (
//            @ApiParam(name = "json_data", value = "Json数据", required = true)
//            @RequestBody String jsonData) throws Exception {
//        ModuleFunctionDO moduleFunctionDO = toEntity(jsonData, ModuleFunctionDO.class);
//        if (null == moduleFunctionDO.getId()) {
//            return failed("ID不能为空", Envelop.class);
//        }
//        moduleFunctionDO = moduleFunctionService.save(moduleFunctionDO);
//        return success(moduleFunctionDO);
//    }
//
//    @GetMapping(value = BaseRequestMapping.ModuleFunction.PAGE)
//    @ApiOperation(value = "获取分页")
//    public PageEnvelop<ModuleFunctionDO> page (
//            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
//            @RequestParam(value = "fields", required = false) String fields,
//            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
//            @RequestParam(value = "filters", required = false) String filters,
//            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
//            @RequestParam(value = "sorts", required = false) String sorts,
//            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
//            @RequestParam(value = "page") int page,
//            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
//            @RequestParam(value = "size") int size) throws Exception {
//        List<ModuleFunctionDO> moduleFunctionDOS = moduleFunctionService.search(fields, filters, sorts, page, size);
//        int count = (int)moduleFunctionService.getCount(filters);
//        return success(moduleFunctionDOS, count, page, size);
//    }
//
//    @GetMapping(value = BaseRequestMapping.ModuleFunction.LIST)
//    @ApiOperation(value = "获取列表")
//    public ListEnvelop<ModuleFunctionDO> list (
//            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
//            @RequestParam(value = "fields", required = false) String fields,
//            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
//            @RequestParam(value = "filters", required = false) String filters,
//            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
//            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
//        List<ModuleFunctionDO> moduleFunctionDOS = moduleFunctionService.search(fields, filters, sorts);
//        return success(moduleFunctionDOS);
//    }
//
//}
