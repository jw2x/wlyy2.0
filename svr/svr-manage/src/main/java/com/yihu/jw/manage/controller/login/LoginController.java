package com.yihu.jw.manage.controller.login;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.manage.adapter.CacheAdapter;
import com.yihu.jw.manage.adapter.cache.model.LoginCacheModel;
import com.yihu.jw.manage.aop.annotation.ManageLog;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.login.LoginService;
import com.yihu.jw.manage.service.system.MenuRoleService;
import com.yihu.jw.manage.service.system.UserRoleService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.EnvelopRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/6
 */
@RestController
@Api(description = "登陆模块")
public class LoginController extends EnvelopRestController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private MenuRoleService menuRoleService;
    @Autowired
    private CacheAdapter cacheAdapter;
    @Autowired
    private UserRoleService userRoleService;


    @GetMapping("/login")
    @ApiOperation(value = "登陆")
    @ManageLog
    public Envelop login(
            @ApiParam(name = "username", value = "账号", required = true)@RequestParam(required = true, name = "username") String username,
            @ApiParam(name = "password", value = "密码", required = true)@RequestParam(required = true, name = "password") String password) throws ManageException {
        ManageUser data = loginService.login(username, password);

        String userCode = data.getCode();

        //查找saas
        List<String> saases = userRoleService.getSaasIdByUserCode(userCode);


        //根据userCode查找用户拥有的权限,放入缓存中
        List<Map<String, Object>> maps = menuRoleService.findByUserCode(userCode);
        cacheAdapter.setData(CacheAdapter.ROLE,userCode,maps);

        //登陆用户放入缓存
        LoginCacheModel loginCacheModel = new LoginCacheModel();
        loginCacheModel.setCode(userCode);

        //saas有多个时,取出第一个放入缓存
        if(saases!=null&&saases.size()>0){
            loginCacheModel.setSaasId(saases.get(0));
        }
        cacheAdapter.setData(CacheAdapter.LOGIN,userCode,loginCacheModel);
        return Envelop.getSuccess("登陆成功", loginCacheModel);
    }

    @GetMapping("/loginout")
    @ApiOperation(value = "退出")
    public Envelop loginout(
            @ApiParam(name = "userCode", value = "用户code", required = true)@RequestParam(required = true, name = "userCode") String userCode) {
        try {
            //从缓存清空
            cacheAdapter.removeData(CacheAdapter.ROLE,userCode);//权限移除
            cacheAdapter.removeData(CacheAdapter.LOGIN,userCode);//登陆移除
            return Envelop.getSuccess("登出成功");
        } catch (Exception e) {
            error(e);
            return Envelop.getError("登出成功:" + e.getMessage(), -1);
        }
    }

    @GetMapping("/index")
    @ApiOperation(value = "index页面需要的参数,菜单 用户信息")
    public Envelop index(
            @ApiParam(name = "userCode", value = "用户code", required = true)  @RequestParam(required = true, name = "userCode") String userCode
    ) throws ManageException {
        Map<String, List> data = loginService.index(userCode);
        return Envelop.getSuccess("获取信息成功", data);
    }
}
