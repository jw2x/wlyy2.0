package com.yihu.jw.healthyhouse.controller.user;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.constant.LoginInfo;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.healthyhouse.service.user.UserService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.restmodel.wlyy.HouseUserContant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 *  用户相关 接口
 * @author HZY
 * @created 2018/9/19 17:29
 */
@Api(value = "UserController", description = "用户信息", tags = {"用户"})
@RequestMapping("/user")
@RestController
public class UserController  extends EnvelopRestEndpoint {

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    @ApiOperation(value = "获取用户列表")
    public PageEnvelop userList(
            @ApiParam(name = "page", value = "页数", required = true)@RequestParam(required = true, name = "page") Integer page,
            @ApiParam(name = "pageSize", value = "每页数量", required = true)@RequestParam(required = true, name = "pageSize") Integer pageSize,
            @ApiParam(name = "city", value = "所在市区", required = false)@RequestParam(required = false, name = "city") String city,
            @ApiParam(name = "activated", value = "用户状态", required = false)@RequestParam(required = false, name = "activated") String activated ,
            @ApiParam(name = "name", value = "姓名/手机号", required = false)@RequestParam(required = false, name = "name") String name ,
            @ApiParam(name = "order", value = "使用次数排序", required = false)@RequestParam(required = false, name = "order") String order  ) throws ManageException {
        Map<String, String> map = new HashMap<>();
        map.put("cityCode",city);
        map.put("activated",activated);
        map.put("name",name);
        map.put("telephone",name);
        Page<User> users = userService.userList(page, pageSize, map, order);
        return PageEnvelop.getSuccessListWithPage("列表获取成功",users.getContent(),page,pageSize,users.getTotalElements());
    }


    @GetMapping("/userDetail")
    @ApiOperation(value = "获取用户详情")
    public ObjEnvelop userDetail(
            @ApiParam(name = "userId", value = "用户id", required = true)@RequestParam(required = true, name = "userId") String userId ) {
        User user = userService.findById(userId);
        return ObjEnvelop.getSuccess("获取成功",user);
    }


    @GetMapping("/usedFacilityCount")
    @ApiOperation(value = "获取用户统计信息")
    public ObjEnvelop usedFacilityCount(
            @ApiParam(name = "userId", value = "用户id", required = true)@RequestParam(required = true, name = "userId") String userId ) {
        Map<String, Long> userStatistics = userService.findUserStatistics();
        return ObjEnvelop.getSuccess("获取成功",userStatistics);
    }

    @PostMapping("/activateUser")
    @ApiOperation(value = "用户激活")
    public Envelop activeUser(
            @ApiParam(name = "userId", value = "用户id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "operator", value = "操作者", required = true)@RequestParam(required = true, name = "operator") String operator ) {
         userService.updateStatus(userId,operator, HouseUserContant.activated_active,null);
        return ObjEnvelop.getSuccess("激活成功");
    }


    @PostMapping("/freezeUser")
    @ApiOperation(value = "用户冻结")
    public Envelop freezeUser(
            @ApiParam(name = "userId", value = "用户id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "reason", value = "冻结原因", required = true)@RequestParam(required = true, name = "reason") String reason ,
            @ApiParam(name = "operator", value = "操作者", required = true)@RequestParam(required = true, name = "operator") String operator ) {

        userService.updateStatus(userId,operator, HouseUserContant.activated_lock,reason);
        return ObjEnvelop.getSuccess("冻结成功");
    }


    @PostMapping("/facilityUseUpdate")
    @ApiOperation(value = "用户使用设备次数总和")
    public Envelop facilityUseUpdate(
            @ApiParam(name = "userId", value = "用户Id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "facilityId", value = "设施Id", required = true)@RequestParam(required = true, name = "facilityId") String facilityId ) throws ManageException {

        userService.updateFacilityUse(userId,facilityId);
        return ObjEnvelop.getSuccess("更新用户使用设施次数成功");
    }



}
