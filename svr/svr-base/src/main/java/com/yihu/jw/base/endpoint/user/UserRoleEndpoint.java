package com.yihu.jw.base.endpoint.user;

import com.yihu.jw.base.service.UserRoleService;
import com.yihu.jw.entity.base.user.UserRoleDO;
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
@RequestMapping(value = BaseRequestMapping.UserRole.PREFIX)
@Api(value = "用户角色管理", description = "用户角色管理服务接口", tags = {"wlyy基础服务 - 用户角色管理服务接口"})
public class UserRoleEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping(value = BaseRequestMapping.UserRole.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<UserRoleDO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        UserRoleDO userRoleDO = toEntity(jsonData, UserRoleDO.class);
        userRoleDO = userRoleService.save(userRoleDO);
        return success(userRoleDO);
    }

    @PostMapping(value = BaseRequestMapping.UserRole.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        userRoleService.delete(ids);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.UserRole.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        UserRoleDO userRoleDO = toEntity(jsonData, UserRoleDO.class);
        if (null == userRoleDO.getId()) {
            return failed("ID不能为空", Envelop.class);
        }
        userRoleDO = userRoleService.save(userRoleDO);
        return success(userRoleDO);
    }

    @GetMapping(value = BaseRequestMapping.UserRole.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<UserRoleDO> page (
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
        List<UserRoleDO> userRoleDOS = userRoleService.search(fields, filters, sorts, page, size);
        int count = (int)userRoleService.getCount(filters);
        return success(userRoleDOS, count, page, size);
    }

    @GetMapping(value = BaseRequestMapping.UserRole.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<UserRoleDO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<UserRoleDO> userRoleDOS = userRoleService.search(fields, filters, sorts);
        return success(userRoleDOS);
    }

}
