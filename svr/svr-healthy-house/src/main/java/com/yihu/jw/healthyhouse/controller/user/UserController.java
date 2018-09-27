package com.yihu.jw.healthyhouse.controller.user;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.cache.WlyyRedisVerifyCodeService;
import com.yihu.jw.healthyhouse.model.facility.Facility;
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
import jxl.write.Colour;
import jxl.write.WritableCellFormat;
import org.apache.log4j.spi.ErrorCode;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *  用户相关 接口
 * @author HZY
 * @created 2018/9/19 17:29
 */
@Api(value = "UserController", description = "用户管理信息", tags = {"用户管理"})
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class UserController  extends EnvelopRestEndpoint {

    @Autowired
    private UserService userService;
    @Autowired
    private WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService;


    @GetMapping("/userList")
    @ApiOperation(value = "获取用户列表")
    public PageEnvelop userList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) Integer page ) throws ManageException, ParseException {

        List<User> userList = userService.search(fields, filters, sorts, page, size);
        return success(userList, userList == null ? 0 : userList.size(), page, size);
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
    public ObjEnvelop usedFacilityCount() {
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


    @PostMapping("/updatePwd")
    @ApiOperation(value = "更新密码")
    public Envelop updatePwd(
            @ApiParam(name = "userId", value = "用户Id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "oldPwd", value = "原密码", required = true)@RequestParam(required = true, name = "oldPwd") String oldPwd ,
            @ApiParam(name = "newPwd", value = "新密码", required = true)@RequestParam(required = true, name = "newPwd") String newPwd ) throws ManageException {

        userService.updatePwd(userId,oldPwd,newPwd);
        return ObjEnvelop.getSuccess("更新密码成功");
    }

    @PostMapping("/updatePhone")
    @ApiOperation(value = "更新安全手机号码")
    public Envelop updatePhone(
            @ApiParam(name = "clientId", value = "应用id", required = true)@RequestParam(required = true, name = "clientId") String clientId,
            @ApiParam(name = "userId", value = "用户Id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "newPhone", value = "新安全手机号", required = true)@RequestParam(required = true, name = "newPhone") String newPhone ,
            @ApiParam(name = "captcha", value = "短信验证码", required = true)@RequestParam(required = true, name = "captcha") String captcha ) throws ManageException {

        //验证码
        if (wlyyRedisVerifyCodeService.verification(clientId, newPhone, captcha)) {
            userService.updateSecurePhone(userId,newPhone);
            return ObjEnvelop.getSuccess("更新安全手机号码成功");
        } else {
            return ObjEnvelop.getError("验证码错误");
        }
    }

    @PostMapping("/checkIdCardNo")
    @ApiOperation(value = "用户身份证号码认证")
    public Envelop checkIdCardNo(
            @ApiParam(name = "userId", value = "用户Id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "idCardNo", value = "身份证号码", required = true)@RequestParam(required = true, name = "idCardNo") String idCardNo ) throws ManageException {

        userService.checkIdCardNo(userId, idCardNo);
        return ObjEnvelop.getSuccess("身份证认证完成！");
    }


    @GetMapping("/exportToExcel")
    @ApiOperation(value = "用户列表导出excel")
    public void exportToExcel(
            HttpServletResponse response,
            @ApiParam(name = "city", value = "所在市区", required = false)@RequestParam(required = false, name = "city") String city,
            @ApiParam(name = "activated", value = "用户状态", required = false)@RequestParam(required = false, name = "activated") String activated ,
            @ApiParam(name = "name", value = "姓名/手机号", required = false)@RequestParam(required = false, name = "name") String name ,
            @ApiParam(name = "sort", value = "使用次数排序", required = false)@RequestParam(required = false, name = "sort") String sort) throws ManageException {
        Envelop envelop = new Envelop();
        //获取用户数据
        Map<String, String> map = new HashMap<>();
        map.put("cityCode",city);
        map.put("activated",activated);
        map.put("name",name);
        map.put("telephone",name);
        List<User> userList = userService.userList( map, sort);
        userService.exportUsersExcel(response,userList);
    }



}
