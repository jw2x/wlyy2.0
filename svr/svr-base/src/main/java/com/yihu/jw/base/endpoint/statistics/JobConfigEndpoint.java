package com.yihu.jw.base.endpoint.statistics;

import com.yihu.jw.base.service.statistics.JobConfigService;
import com.yihu.jw.entity.base.statistics.JobConfigDO;
import com.yihu.jw.restmodel.base.statistics.JobConfigVO;
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
@RequestMapping(value = BaseRequestMapping.JobConfig.PREFIX, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "统计job配置管理", description = "统计job配置管理服务接口", tags = {"wlyy基础服务 - 统计job配置管理服务接口"})
public class JobConfigEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private JobConfigService jobConfigService;

    @PostMapping(value = BaseRequestMapping.ServicePackage.CREATE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<JobConfigVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        JobConfigDO jobConfigDO = toEntity(jsonData, JobConfigDO.class);
        jobConfigDO = jobConfigService.save(jobConfigDO);
        return success(convertToModel(jobConfigDO, JobConfigVO.class));
    }

    @PostMapping(value = BaseRequestMapping.ServicePackage.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        jobConfigService.delete(ids);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.ServicePackage.UPDATE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<JobConfigVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        JobConfigDO jobConfigDO = toEntity(jsonData, JobConfigDO.class);
        if (null == jobConfigDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        jobConfigDO = jobConfigService.save(jobConfigDO);
        return success(convertToModel(jobConfigDO, JobConfigVO.class));
    }

    @GetMapping(value = BaseRequestMapping.ServicePackage.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<JobConfigVO> page (
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
        List<JobConfigDO> jobConfigDOList = jobConfigService.search(fields, filters, sorts, page, size);
        int count = (int)jobConfigService.getCount(filters);
        return success(jobConfigDOList, count, page, size, JobConfigVO.class);
    }

    @GetMapping(value = BaseRequestMapping.ServicePackage.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<JobConfigVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<JobConfigDO> jobConfigDOList = jobConfigService.search(fields, filters, sorts);
        return success(jobConfigDOList, JobConfigVO.class);
    }
}
