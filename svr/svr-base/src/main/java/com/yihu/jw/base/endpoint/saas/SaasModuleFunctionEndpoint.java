package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.service.SaasModuleFunctionService;
import com.yihu.jw.entity.base.saas.SaasModuleFunctionDO;
import com.yihu.jw.restmodel.base.saas.SaasModuleFunctionVO;
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
 * Endpoint - Saas所分配的模块功能
 * Created by progr1mmer on 2018/8/16.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.RoleModuleFunction.PREFIX)
@Api(value = "Saas模块功能管理", description = "Saas模块功能管理服务接口", tags = {"SAAS - Saas模块功能管理服务接口"})
public class SaasModuleFunctionEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SaasModuleFunctionService saasModuleFunctionService;

    @PostMapping(value = BaseRequestMapping.RoleModuleFunction.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SaasModuleFunctionVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasModuleFunctionDO saasModuleFunctionDO = toEntity(jsonData, SaasModuleFunctionDO.class);
        saasModuleFunctionDO = saasModuleFunctionService.save(saasModuleFunctionDO);
        return success(saasModuleFunctionDO, SaasModuleFunctionVO.class);
    }

    @PostMapping(value = BaseRequestMapping.RoleModuleFunction.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        saasModuleFunctionService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.RoleModuleFunction.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasModuleFunctionDO saasModuleFunctionDO = toEntity(jsonData, SaasModuleFunctionDO.class);
        if (null == saasModuleFunctionDO.getId()) {
            return failed("ID不能为空", Envelop.class);
        }
        saasModuleFunctionDO = saasModuleFunctionService.save(saasModuleFunctionDO);
        return success(saasModuleFunctionDO);
    }

    @GetMapping(value = BaseRequestMapping.RoleModuleFunction.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SaasModuleFunctionVO> page (
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
        List<SaasModuleFunctionDO> saasModuleFunctionDOS = saasModuleFunctionService.search(fields, filters, sorts, page, size);
        int count = (int)saasModuleFunctionService.getCount(filters);
        return success(saasModuleFunctionDOS, count, page, size, SaasModuleFunctionVO.class);
    }

    @GetMapping(value = BaseRequestMapping.RoleModuleFunction.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasModuleFunctionVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SaasModuleFunctionDO> saasModuleFunctionDOS = saasModuleFunctionService.search(fields, filters, sorts);
        return success(saasModuleFunctionDOS, SaasModuleFunctionVO.class);
    }

}
