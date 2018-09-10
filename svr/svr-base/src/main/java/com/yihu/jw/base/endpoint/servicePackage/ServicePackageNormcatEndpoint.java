package com.yihu.jw.base.endpoint.servicePackage;

import com.yihu.jw.base.service.servicePackage.ServicePackageNormcatService;
import com.yihu.jw.entity.base.servicePackage.ServicePackageNormcatDO;
import com.yihu.jw.restmodel.base.servicePackage.ServicePackageNormcatVO;
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
 * @author yeshijie on 2018/9/7.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.ServicePackageNormcat.PREFIX, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "服务包类目管理", description = "服务包类目管理服务接口", tags = {"wlyy基础服务 - 服务包类目管理服务接口"})
public class ServicePackageNormcatEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private ServicePackageNormcatService servicePackageNormcatService;

    @PostMapping(value = BaseRequestMapping.ServicePackageNormcat.CREATE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<ServicePackageNormcatVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        ServicePackageNormcatDO normcatDO = toEntity(jsonData, ServicePackageNormcatDO.class);
        normcatDO = servicePackageNormcatService.save(normcatDO);
        return success(convertToModel(normcatDO, ServicePackageNormcatVO.class));
    }

    @PostMapping(value = BaseRequestMapping.ServicePackageNormcat.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) throws Exception{
        servicePackageNormcatService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.ServicePackageNormcat.UPDATE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<ServicePackageNormcatVO> update (
            @ApiParam(name = "jsonData", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        ServicePackageNormcatDO normcatDO = toEntity(jsonData, ServicePackageNormcatDO.class);
        if (null == normcatDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        normcatDO = servicePackageNormcatService.save(normcatDO);
        return success(normcatDO, ServicePackageNormcatVO.class);
    }

    @GetMapping(value = BaseRequestMapping.ServicePackageNormcat.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<ServicePackageNormcatVO> page (
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
        List<ServicePackageNormcatDO> normcatDOS = servicePackageNormcatService.search(fields, filters, sorts, page, size);
        int count = (int)servicePackageNormcatService.getCount(filters);
        return success(normcatDOS, count, page, size, ServicePackageNormcatVO.class);
    }

    @GetMapping(value = BaseRequestMapping.ServicePackageNormcat.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<ServicePackageNormcatVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<ServicePackageNormcatDO> normcatDOS = servicePackageNormcatService.search(fields, filters, sorts);
        return success(normcatDOS, ServicePackageNormcatVO.class);
    }
}
