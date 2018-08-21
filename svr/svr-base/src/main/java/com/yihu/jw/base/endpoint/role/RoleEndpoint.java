package com.yihu.jw.base.endpoint.role;

import com.yihu.jw.base.service.RoleService;
import com.yihu.jw.entity.base.role.RoleDO;
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
@RequestMapping(value = BaseRequestMapping.Role.PREFIX)
@Api(value = "角色管理", description = "角色管理服务接口", tags = {"wlyy基础服务 - 角色管理服务接口"})
public class RoleEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = BaseRequestMapping.Module.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<RoleDO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        RoleDO roleDO = toEntity(jsonData, RoleDO.class);
        roleDO = roleService.save(roleDO);
        return success(roleDO);
    }

    @PostMapping(value = BaseRequestMapping.Module.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        roleService.delete(ids);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Module.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        RoleDO roleDO = toEntity(jsonData, RoleDO.class);
        if (null == roleDO.getId()) {
            return failed("ID不能为空", Envelop.class);
        }
        roleDO = roleService.save(roleDO);
        return success(roleDO);
    }

    @GetMapping(value = BaseRequestMapping.Module.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<RoleDO> page (
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
        List<RoleDO> roleDOS = roleService.search(fields, filters, sorts, page, size);
        int count = (int)roleService.getCount(filters);
        return success(roleDOS, count, page, size);
    }

    @GetMapping(value = BaseRequestMapping.Module.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<RoleDO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<RoleDO> roleDOS = roleService.search(fields, filters, sorts);
        return success(roleDOS);
    }

}
