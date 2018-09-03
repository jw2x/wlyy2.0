package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.service.SaasDefaultModuleFunctionService;
import com.yihu.jw.entity.base.saas.SaasDefaultModuleFunctionDO;
import com.yihu.jw.restmodel.base.saas.SaasDefaultModuleFunctionVO;
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
 * Endpoint - Saas默认模块功能
 * Created by progr1mmer on 2018/8/16.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.SaasDefaultModuleFunction.PREFIX)
@Api(value = "Saas默认模块功能管理", description = "Saas默认模块功能管理服务接口", tags = {"SAAS - Saas默认模块功能管理服务接口"})
public class SaasDefaultModuleFunctionEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SaasDefaultModuleFunctionService saasDefaultModuleService;

    @PostMapping(value = BaseRequestMapping.SaasDefaultModuleFunction.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SaasDefaultModuleFunctionVO> create (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasDefaultModuleFunctionDO saasDefaultModuleDO = toEntity(jsonData, SaasDefaultModuleFunctionDO.class);
        saasDefaultModuleDO = saasDefaultModuleService.save(saasDefaultModuleDO);
        return success(saasDefaultModuleDO, SaasDefaultModuleFunctionVO.class);
    }

    @PostMapping(value = BaseRequestMapping.SaasDefaultModuleFunction.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        saasDefaultModuleService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.SaasDefaultModuleFunction.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasDefaultModuleFunctionDO saasDefaultModuleDO = toEntity(jsonData, SaasDefaultModuleFunctionDO.class);
        if (null == saasDefaultModuleDO.getId()) {
            return failed("ID不能为空", Envelop.class);
        }
        saasDefaultModuleDO = saasDefaultModuleService.save(saasDefaultModuleDO);
        return success(saasDefaultModuleDO);
    }

    @GetMapping(value = BaseRequestMapping.SaasDefaultModuleFunction.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SaasDefaultModuleFunctionVO> page (
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
        List<SaasDefaultModuleFunctionDO> saasDefaultModuleDOS = saasDefaultModuleService.search(fields, filters, sorts, page, size);
        int count = (int)saasDefaultModuleService.getCount(filters);
        return success(saasDefaultModuleDOS, count, page, size, SaasDefaultModuleFunctionVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SaasDefaultModuleFunction.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasDefaultModuleFunctionVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SaasDefaultModuleFunctionDO> saasDefaultModuleDOS = saasDefaultModuleService.search(fields, filters, sorts);
        return success(saasDefaultModuleDOS, SaasDefaultModuleFunctionVO.class);
    }

}
