package com.yihu.jw.base.endpoint.statistics;

import com.yihu.jw.base.service.statistics.DimensionService;
import com.yihu.jw.entity.base.statistics.DimensionDO;
import com.yihu.jw.restmodel.base.statistics.DimensionVO;
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
 *
 * @author yeshijie on 2018/8/31.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Dimension.PREFIX, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "统计维度管理", description = "统计维度管理服务接口", tags = {"wlyy基础服务 - 统计维度管理服务接口"})
public class DimensionEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private DimensionService dimensionService;

    @PostMapping(value = BaseRequestMapping.Dimension.CREATE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<DimensionVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        DimensionDO dimensionDO = toEntity(jsonData, DimensionDO.class);
        dimensionDO = dimensionService.save(dimensionDO);
        return success(convertToModel(dimensionDO, DimensionVO.class));
    }

    @PostMapping(value = BaseRequestMapping.Dimension.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        dimensionService.delete(ids);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Dimension.UPDATE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<DimensionVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        DimensionDO dimensionDO = toEntity(jsonData, DimensionDO.class);
        if (null == dimensionDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        dimensionDO = dimensionService.save(dimensionDO);
        return success(convertToModel(dimensionDO, DimensionVO.class));
    }

    @GetMapping(value = BaseRequestMapping.Dimension.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<DimensionVO> page (
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
        List<DimensionDO> dimensionDOList = dimensionService.search(fields, filters, sorts, page, size);
        int count = (int)dimensionService.getCount(filters);
        return success(dimensionDOList, count, page, size, DimensionVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Dimension.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<DimensionVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<DimensionDO> dimensionDOList = dimensionService.search(fields, filters, sorts);
        return success(dimensionDOList, DimensionVO.class);
    }
}
