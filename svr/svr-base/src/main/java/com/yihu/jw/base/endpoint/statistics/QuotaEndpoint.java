package com.yihu.jw.base.endpoint.statistics;

import com.yihu.jw.base.service.statistics.QuotaService;
import com.yihu.jw.entity.base.statistics.QuotaDO;
import com.yihu.jw.restmodel.base.statistics.QuotaVO;
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
@RequestMapping(value = BaseRequestMapping.Quota.PREFIX, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "统计指标管理", description = "统计指标管理服务接口", tags = {"wlyy基础服务 - 统计指标管理服务接口"})
public class QuotaEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private QuotaService quotaService;

    @PostMapping(value = BaseRequestMapping.Quota.CREATE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<QuotaVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        QuotaDO quotaDO = toEntity(jsonData, QuotaDO.class);
        quotaDO = quotaService.save(quotaDO);
        return success(quotaDO, QuotaVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Quota.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        quotaService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Quota.UPDATE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<QuotaVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        QuotaDO quotaDO = toEntity(jsonData, QuotaDO.class);
        if (null == quotaDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        quotaDO = quotaService.save(quotaDO);
        return success(quotaDO, QuotaVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Quota.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<QuotaVO> page (
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
        List<QuotaDO> quotaDOList = quotaService.search(fields, filters, sorts, page, size);
        int count = (int)quotaService.getCount(filters);
        return success(quotaDOList, count, page, size, QuotaVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Quota.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<QuotaVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<QuotaDO> quotaDOList = quotaService.search(fields, filters, sorts);
        return success(quotaDOList, QuotaVO.class);
    }
}
