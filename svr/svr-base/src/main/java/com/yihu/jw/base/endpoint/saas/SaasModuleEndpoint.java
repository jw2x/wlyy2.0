package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.service.SaasModuleService;
import com.yihu.jw.entity.base.saas.SaasModuleDO;
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
 * Created by progr1mmer on 2018/8/16.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.SaasModule.PREFIX)
@Api(value = "Saas模块管理", description = "Saas模块管理服务接口", tags = {"wlyy基础服务 - Saas模块管理服务接口"})
public class SaasModuleEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SaasModuleService saasModuleService;

    @PostMapping(value = BaseRequestMapping.SaasModule.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SaasModuleDO> create (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasModuleDO saasModuleDO = toEntity(jsonData, SaasModuleDO.class);
        saasModuleDO = saasModuleService.save(saasModuleDO);
        return success(saasModuleDO);
    }

    @PostMapping(value = BaseRequestMapping.SaasModule.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        saasModuleService.delete(ids);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.SaasModule.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasModuleDO saasModuleDO = toEntity(jsonData, SaasModuleDO.class);
        if (null == saasModuleDO.getId()) {
            return failed("ID不能为空", Envelop.class);
        }
        saasModuleDO = saasModuleService.save(saasModuleDO);
        return success(saasModuleDO);
    }

    @GetMapping(value = BaseRequestMapping.SaasModule.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SaasModuleDO> page (
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
        List<SaasModuleDO> saasModuleDOS = saasModuleService.search(fields, filters, sorts, page, size);
        int count = (int)saasModuleService.getCount(filters);
        return success(saasModuleDOS, count, page, size);
    }

    @GetMapping(value = BaseRequestMapping.SaasModule.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasModuleDO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SaasModuleDO> saasModuleDOS = saasModuleService.search(fields, filters, sorts);
        return success(saasModuleDOS);
    }

}
