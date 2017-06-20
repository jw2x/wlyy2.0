package com.yihu.jw.manage.controller.system;

import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/12.
 */

@RestController
@RequestMapping("/user")
@Api(description = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation(value = "用户列表")
    public Envelop userList(
            @ApiParam(name = "name", value = "用户名称", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "start", value = "当前页(0开始)", required = false) @RequestParam(required = false, name = "start", defaultValue = "0") Integer page,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer pageSize
    ) {
        try {
            Page<ManageUser> users = userService.userList(name,page, pageSize);
            return Envelop.getSuccessListWithPage(
                    "获取信息成功",
                    users.getContent(),//数据内容
                    page, //当前页
                    pageSize,//每个显示条数
                    users.getTotalElements()//总数
            );

        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }
}
