package com.yihu.jw.business.base.controller;

import com.yihu.jw.base.base.ModuleDO;
import com.yihu.jw.business.base.service.ModuleService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.base.base.MModule;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
@RestController
@RequestMapping(BaseRequestMapping.api_base_common)
@Api(value = "模块模块", description = "模块接口管理")
public class ModuleController extends EnvelopRestController {
    @Autowired
    private ModuleService moduleService;

    @PostMapping(value = BaseRequestMapping.Module.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建模块", notes = "创建单个模块")
    public Envelop createModule(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            ModuleDO module = toEntity(jsonData, ModuleDO.class);
            return Envelop.getSuccess(BaseRequestMapping.Module.message_success_create, moduleService.createModule(module));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseRequestMapping.Module.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改模块", notes = "修改模块")
    public Envelop updateModule(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            ModuleDO module = toEntity(jsonData, ModuleDO.class);
            return Envelop.getSuccess(BaseRequestMapping.Module.message_success_update, moduleService.updateModule(module));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @DeleteMapping(value = BaseRequestMapping.Module.api_delete)
    @ApiOperation(value = "删除模块", notes = "删除模块")
    public Envelop deleteModule(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes) {
        try {
            moduleService.deleteModule(codes);
            return Envelop.getSuccess(BaseRequestMapping.Module.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseRequestMapping.Module.api_getById)
    @ApiOperation(value = "根据code查找模块", notes = "根据code查找模块")
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @PathVariable(value = "id", required = true) String id
    ) {
        try {
            return Envelop.getSuccess(BaseRequestMapping.Module.message_success_find, moduleService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseRequestMapping.Module.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取父模块列表(分页)")
    public Envelop getModules(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            //code like 1,name大于aa ,code 等于1 , defaultValue = "code?1;name>aa;code=1"
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(StringUtils.isBlank(sorts)){
            sorts = "-updateTime";
        }
        if(StringUtils.isBlank(filters)){
            filters = "parentCode=0;";
        }else{
            filters="parentCode=0;"+filters;
        }

        //得到list数据
        List<ModuleDO> list = moduleService.search(fields, filters, sorts, page, size);

        if(list!=null){
            for(ModuleDO module:list){//循环遍历,设置是否有子节点
                List<ModuleDO> children = moduleService.getChildren(module.getId());
                //children长度为0时    state  “open”表示是子节点，“closed”表示为父节点；
                // children长度>0时,  state   “open,closed”表示是节点的打开关闭
                if (children.size()>0){
                    module.setState("closed");
                }else{
                    module.setState("open");
                }
            }
        }

        //获取总数
        long count=moduleService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MModule> mModules = convertToModels(list, new ArrayList<>(list.size()), MModule.class, fields);

        return Envelop.getSuccessListWithPage(BaseRequestMapping.Module.message_success_find_Modules,mModules, page, size,count);
    }


    @GetMapping(value = BaseRequestMapping.Module.api_getListNoPage)
    @ApiOperation(value = "获取模块列表，不分页")
    public Envelop getListNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<ModuleDO> list = moduleService.search(fields,filters,sorts);
        //封装返回格式
        List<MModule> mModules = convertToModels(list, new ArrayList<>(list.size()), MModule.class, fields);
        return Envelop.getSuccessList(BaseRequestMapping.Module.message_success_find_Modules,mModules);
    }


    @GetMapping(value =BaseRequestMapping.Module.api_getChildren )
    @ApiOperation(value="查找子节点")
    public Envelop getChildren(@PathVariable String code){
        List<ModuleDO> children = moduleService.getChildren(code);
        return Envelop.getSuccess("查询成功",children);
    }


}
