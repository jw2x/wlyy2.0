package com.yihu.jw.manage.service.login;

import com.yihu.jw.manage.model.system.ManageMenu;
import com.yihu.jw.manage.model.system.ManageRole;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.system.MenuService;
import com.yihu.jw.manage.service.system.RoleService;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.util.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/8.
 */
@Service
public class LoginService {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    public ManageUser login(String username, String password) throws Exception{
        Map<String, List> data = new HashMap<>();
        //判断是否登陆信息是否正确
        ManageUser user = userService.findByAccount(username);
        if(user==null){
            throw new Exception("账号不存在");
        }
        if (!user.getPassword().equals(MD5.GetMD5Code(password + user.getSalt()))) {
            throw new Exception("密码错误");
        }
        //保存登陆信息

        return user;
    }

    public Map<String,List> index(String usercode) {
        Map<String, List> data = new HashMap<>();
        //得到角色
        List<ManageRole> roles=roleService.findByUserCode(usercode);
        //得到用户所有菜单
        List<ManageMenu> menus=menuService.findByUserCode(usercode);
        data.put("role",roles);
        data.put("menus",menus);
        return data;
    }
}
