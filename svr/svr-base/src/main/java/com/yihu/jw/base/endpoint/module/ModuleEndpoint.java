package com.yihu.jw.base.endpoint.module;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        module = moduleService.save(module);
        return success(module, ModuleVO.class);
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

}
