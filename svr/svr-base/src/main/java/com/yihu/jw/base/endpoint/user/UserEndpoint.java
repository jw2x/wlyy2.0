package com.yihu.jw.base.endpoint.user;

import com.yihu.jw.base.service.UserService;
import com.yihu.jw.entity.base.user.UserDO;
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
@RequestMapping(value = BaseRequestMapping.User.PREFIX)
@Api(value = "用户管理", description = "用户管理服务接口", tags = {"wlyy基础服务 - 用户管理服务接口"})
public class UserEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private UserService userService;

    @PostMapping(value = BaseRequestMapping.User.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<UserDO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        UserDO userDO = toEntity(jsonData, UserDO.class);
        userDO = userService.save(userDO);
        return success(userDO);
    }

    @PostMapping(value = BaseRequestMapping.User.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        userService.delete(ids);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.User.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        UserDO userDO = toEntity(jsonData, UserDO.class);
        if (null == userDO.getId()) {
            return failed("ID不能为空");
        }
        userDO = userService.save(userDO);
        return success(userDO);
    }

    @GetMapping(value = BaseRequestMapping.User.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<UserDO> page (
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
        List<UserDO> userDOS = userService.search(fields, filters, sorts, page, size);
        int count = (int)userService.getCount(filters);
        return success(userDOS, count, page, size);
    }

    @GetMapping(value = BaseRequestMapping.User.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<UserDO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<UserDO> userDOS = userService.search(fields, filters, sorts);
        return success(userDOS);
    }

    @GetMapping(value = BaseRequestMapping.User.CHECK_USERNAME)
    @ApiOperation(value = "检查用户名是否可用(message=1代表可用，message=0代表不可用)")
    public Envelop checkName (
            @ApiParam(name = "username", value = "用户名", required = true)
            @RequestParam(value = "username", required = false) String username) throws Exception {
        if (userService.search("username=" + username).size() == 0) {
            return success("1");
        } else {
            return success("0");
        }
    }

}
