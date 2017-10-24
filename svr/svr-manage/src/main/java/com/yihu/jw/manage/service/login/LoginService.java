package com.yihu.jw.manage.service.login;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.manage.cache.login.LoginCache;
import com.yihu.jw.manage.dao.login.ManageLoginLogDao;
import com.yihu.jw.manage.model.login.ManageLoginLog;
import com.yihu.jw.manage.model.system.*;
import com.yihu.jw.manage.service.system.ManageMenuUrlService;
import com.yihu.jw.manage.service.system.MenuService;
import com.yihu.jw.manage.service.system.RoleService;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.restmodel.manage.system.ManageUserVO;
import com.yihu.jw.util.security.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private ManageLoginLogDao manageLoginLogDao;
    @Autowired
    private ManageMenuUrlService menuUrlService;

    /**
     * ManageException异常不回滚
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Transactional(noRollbackForClassName = "ManageException")
    public ManageUser login(String username, String password) throws ManageException {
        ManageLoginLog manageLoginLog = new ManageLoginLog();
        manageLoginLog.setLoginAccount(username);
        manageLoginLog.setLoginTime(new Date());
        Map<String, List> data = new HashMap<>();
        //判断是否登陆信息是否正确
        ManageUser user = userService.findByAccount(username);
        if (user == null) {
            //保存登陆信息
            String message = "账号不存在";
            manageLoginLog.setType(ManageLoginLog.type_error);
            manageLoginLog.setErrorMessage(message);
            manageLoginLogDao.save(manageLoginLog);
            throw new ManageException(message);
        }
        if (!user.getPassword().equals(MD5.GetMD5Code(password + user.getSalt()))) {
            //保存登陆信息
            String message = "密码错误";
            manageLoginLog.setLoginUser(user.getCode());
            manageLoginLog.setLoginUserName(user.getName());
            manageLoginLog.setType(ManageLoginLog.type_error);
            manageLoginLog.setErrorMessage(message);
            manageLoginLogDao.save(manageLoginLog);
            throw new ManageException(message);
        }
        //保存登陆信息
        manageLoginLog.setType(ManageLoginLog.type_success);
        manageLoginLog.setLoginUser(user.getCode());
        manageLoginLog.setLoginUserName(user.getName());
        manageLoginLogDao.save(manageLoginLog);
        //添加登陆缓存中
        ManageUserVO manageUserVO=new ManageUserVO();
        BeanUtils.copyProperties(user,manageLoginLog);
        LoginCache.addCache(user.getCode(),manageUserVO);
        return user;
    }

    public Map<String, List> index(String usercode) throws ManageException {
        Map<String, List> data = new HashMap<>();
        //得到角色
        List<ManageRole> roles = roleService.findByUserCode(usercode);

        List<MenuItems> menuItemses = new ArrayList<>();

        //查询所有父菜单
        List<ManageMenu> parentMenus = menuService.findParentMenus(usercode);
        //查询所有子菜单
        if(parentMenus!=null){
            for(ManageMenu parentMenu:parentMenus){
                //通过父菜单查找对应的子菜单
                List<ManageMenu> childMenus = menuService.findChildMenus(usercode,parentMenu.getCode());
                for(ManageMenu childMenu:childMenus){
                    List<ManageMenuUrl> menuUrls = menuUrlService.getListByMenuCode(childMenu.getCode());
                    ArrayList<String> urls = new ArrayList<String>();
                    if(menuUrls.size()>0){
                        urls.add(menuUrls.get(0).getUrl());
                    }
                    childMenu.setUrl(urls);
                }
                MenuItems menuItem = new MenuItems();
                menuItem.setParentMenu(parentMenu);
                menuItem.setChildMenus(childMenus);
                menuItemses.add(menuItem);
            }
        }
        data.put("role", roles);
        data.put("menus", menuItemses);
        return data;
    }
}
