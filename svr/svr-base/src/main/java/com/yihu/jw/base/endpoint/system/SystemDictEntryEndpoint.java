package com.yihu.jw.base.endpoint.system;

import com.yihu.jw.base.service.system.SystemDictEntryService;
import com.yihu.jw.entity.base.system.SystemDictEntryDO;
import com.yihu.jw.restmodel.base.system.SystemDictEntryVO;
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
 * Endpoint - 系统字典项
 * Created by progr1mmer on 2018/8/16.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.SystemDictEntry.PREFIX)
@Api(value = "系统字典项", description = "系统字典项服务接口", tags = {"wlyy基础服务 - 系统字典项服务接口"})
public class SystemDictEntryEndpoint extends EnvelopRestEndpoint {
    
    @Autowired
    private SystemDictEntryService systemDictEntryService;

    @PostMapping(value = BaseRequestMapping.SystemDict.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SystemDictEntryVO> create (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SystemDictEntryDO systemDictEntryDO = toEntity(jsonData, SystemDictEntryDO.class);
        systemDictEntryDO = systemDictEntryService.save(systemDictEntryDO);
        return success(systemDictEntryDO, SystemDictEntryVO.class);
    }

    @PostMapping(value = BaseRequestMapping.SystemDict.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        systemDictEntryService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.SystemDict.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SystemDictEntryDO systemDictEntryDO = toEntity(jsonData, SystemDictEntryDO.class);
        if (null == systemDictEntryDO.getId()) {
            return failed("ID不能为空", Envelop.class);
        }
        systemDictEntryDO = systemDictEntryService.save(systemDictEntryDO);
        return success(systemDictEntryDO, SystemDictEntryVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SystemDict.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SystemDictEntryVO> page (
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
        List<SystemDictEntryDO> systemDictEntryDOS = systemDictEntryService.search(fields, filters, sorts, page, size);
        int count = (int)systemDictEntryService.getCount(filters);
        return success(systemDictEntryDOS, count, page, size, SystemDictEntryVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SystemDict.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SystemDictEntryVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SystemDictEntryDO> systemDictEntryDOS = systemDictEntryService.search(fields, filters, sorts);
        return success(systemDictEntryDOS, SystemDictEntryVO.class);
    }
}
