package com.yihu.jw.base.endpoint.module;

import com.yihu.jw.base.contant.CommonContant;
import com.yihu.jw.base.service.module.ModuleService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.jw.restmodel.base.module.ModuleVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Endpoint - 模块
 * Created by progr1mmer on 2018/8/16.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Module.PREFIX)
@Api(value = "模块管理", description = "模块管理服务接口", tags = {"wlyy基础服务 - 模块管理服务接口"})
public class ModuleEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @PostMapping(value = BaseRequestMapping.Module.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<ModuleVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        ModuleDO module = toEntity(jsonData, ModuleDO.class);
        int count = moduleService.isExistName(module.getName());
        if(count>0){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Module.NAME_IS_EXIST), ObjEnvelop.class);
        }
        module = moduleService.addModule(module);
        return success(module, ModuleVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Module.STATUS)
    @ApiOperation(value = "生效/失效")
    public Envelop status(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id,
            @ApiParam(name = "status", value = "1生效，0失效", required = true)
            @RequestParam(value = "status") Integer status) {
        moduleService.updateStatus(id, status);
        return success("修改成功");
    }

    @GetMapping(value = BaseRequestMapping.Module.IS_NAME_EXIST)
    @ApiOperation(value = "名称是否存在",notes = "返回值中的obj=1表示名称已经存在，0表示名称不存在")
    public ObjEnvelop isNameExist(
            @ApiParam(name = "name", value = "名称", required = true)
            @RequestParam(value = "name") String name) {
        return success(moduleService.isExistName(name));
    }

    @PostMapping(value = BaseRequestMapping.Module.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        moduleService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Module.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<ModuleVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        ModuleDO module = toEntity(jsonData, ModuleDO.class);
        if (null == module.getId()) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Common.ID_IS_NULL), ObjEnvelop.class);
        }
        int count = moduleService.isExistName(module.getName());
        if(count > 1){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Module.NAME_IS_EXIST), ObjEnvelop.class);
        }
        module = moduleService.save(module);
        return success(module, ModuleVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Module.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<ModuleVO> page (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        List<ModuleDO> modules = moduleService.search(fields, filters, sorts, page, size);
        int count = (int)moduleService.getCount(filters);
        return success(modules, count, page, size, ModuleVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Module.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<ModuleVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<ModuleDO> modules = moduleService.search(fields, filters, sorts);
        return success(modules, ModuleVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Module.FIND_ALL)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<ModuleVO> findAll (
            @ApiParam(name = "status", value = "状态")
            @RequestParam(value = "status", required = false) String status) throws Exception {
        String filters = null;
        if(StringUtils.isNotBlank(status)){
            filters = "status="+status;
        }
        List<ModuleDO> modules = moduleService.search(null, filters, null);
        List<ModuleVO> moduleVOs = convertToModels(modules,new ArrayList<>(modules.size()),ModuleVO.class);
        Map<String,List<ModuleVO>> map = moduleVOs.stream().collect(Collectors.groupingBy(ModuleVO::getParentId));
        moduleVOs.forEach(module->{
            List<ModuleVO> tmp = map.get(module.getId());
            module.setChildren(tmp);
        });
        moduleVOs = moduleVOs.stream()
                .filter(module -> CommonContant.DEFAULT_PARENTID.equals(module.getParentId()))
                .collect(Collectors.toList());

        return success(moduleVOs);
    }



}
