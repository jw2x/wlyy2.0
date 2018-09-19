package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.constant.LoginInfo;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.restmodel.wlyy.HouseUserContant;
import com.yihu.jw.util.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HZY
 * @created 2018/9/18 20:02
 */
@Service
public class LoginService {

    @Autowired
    private UserService userService;
    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Transactional(noRollbackForClassName = "ManageException")
    public User login(HttpServletRequest request, String username, String password) throws ManageException {
        Map<String, List> data = new HashMap<>();
        //判断登陆信息是否正确
        User user = userService.findByCode(username);
        if (user == null) {
            //保存登陆信息
            String message = "账号不存在";
            throw new ManageException(message);
        }
        if (!user.getPassword().equals(MD5.GetMD5Code(password + user.getSalt()))) {
            //保存登陆信息
            String message = "密码错误";
            throw new ManageException(message);
        }
        request.getSession().setAttribute(LoginInfo.IS_LOGIN, true);
        request.getSession().setAttribute(LoginInfo.TOKEN, ""); //TODO token是否添加
        request.getSession().setAttribute(LoginInfo.USER_NAME, user.getName());
        request.getSession().setAttribute(LoginInfo.USER_ID, user.getId());
        user.setActivated(HouseUserContant.activated_active);
        userService.saveOrUpdate(user,password,"systemLogin");
        return user;
    }


    /**
     *  登出
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Transactional(noRollbackForClassName = "ManageException")
    public User logout(HttpServletRequest request, String username, String password) throws ManageException {
        Map<String, List> data = new HashMap<>();
        //判断登陆信息是否正确
        User user = userService.findByCode(username);
        if (user == null) {
            //保存登陆信息
            String message = "账号不存在";
            throw new ManageException(message);
        }
        if (!user.getPassword().equals(MD5.GetMD5Code(password + user.getSalt()))) {
            //保存登陆信息
            String message = "密码错误";
            throw new ManageException(message);
        }
        request.getSession().removeAttribute(LoginInfo.IS_LOGIN);
        request.getSession().removeAttribute(LoginInfo.TOKEN); //TODO token是否添加
        request.getSession().removeAttribute(LoginInfo.USER_NAME);
        request.getSession().removeAttribute(LoginInfo.USER_ID);
        user.setActivated(HouseUserContant.activated_offline);
        userService.saveOrUpdate(user,password,"systemLogin");
        return user;
    }



}
