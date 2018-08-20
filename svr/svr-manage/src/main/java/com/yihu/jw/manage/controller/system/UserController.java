package com.yihu.jw.manage.controller.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.system.UserRoleService;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.restmodel.web.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/12.
 */

@RestController
@RequestMapping("/manage")
@Api(description = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/user/list")
    @ApiOperation(value = "用户列表")
    public Envelop userList(
            @ApiParam(name = "name", value = "用户名称", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "mobile", value = "用户名称", required = false) @RequestParam(required = false, name = "mobile") String mobile,
            @ApiParam(name = "start", value = "当前页(0开始)", required = false) @RequestParam(required = false, name = "start", defaultValue = "0") Integer page,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer pageSize
    ) {
        try {
            page=page/pageSize;
            Map<String, String> map = new HashMap<>();
            map.put("name",name);
            map.put("mobile",mobile);
            Page<ManageUser> users = userService.userList(page, pageSize,map);

            List<ManageUser> content = users.getContent();
            if(content!=null){//查找
                content = userRoleService.findByManageUser(content);
            }

            return Envelop.getSuccessListWithPage("获取信息成功", users.getContent(), page, pageSize, users.getTotalElements());

        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }

    @DeleteMapping(value = "/user/{codes}")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public Envelop delete(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode) {
        try {
            userService.delete(codes,userCode);
            return Envelop.getSuccess("删除成功");
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = "/user/{code}")
    @ApiOperation(value = "根据code查找用户", notes = "根据code查找用户")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess("查询成功", userService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = "/user")
    @ApiOperation(value = "保存或者修改用户", notes = "保存或者修改用户")
    public Envelop saveOrUpdate(@ModelAttribute @Valid ManageUser user,@RequestParam(value="oldPsd" ,required = false)String oldPsd ,@RequestParam(value = "userCode" ,required = true)String userCode) throws JsonProcessingException, ManageException {
        return userService.saveOrUpdate(user,oldPsd,userCode);
    }
}
