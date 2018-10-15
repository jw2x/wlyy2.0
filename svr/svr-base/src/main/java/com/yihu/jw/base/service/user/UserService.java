package com.yihu.jw.base.service.user;

import com.yihu.jw.base.dao.user.UserDao;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.utils.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service - 后台管理员
 * Created by progr1mmer on 2018/8/20.
 */
@Service
public class UserService extends BaseJpaService<UserDO, UserDao> {

    @Autowired
    private UserDao userDao;

    public UserDO register(UserDO userDO) {
        userDO.setSalt(randomString(5));
        userDO.setEnabled(true);
        userDO.setLocked(false);
        userDO.setLoginFailureCount(0);
        String password = userDO.getPassword();
        if (StringUtils.isEmpty(password)) {
            password = userDO.getIdcard().substring(0, 5);
        }
        userDO.setPassword(MD5.md5Hex(password + "{" + userDO.getSalt() + "}"));
        return userDao.save(userDO);
    }


    /**
     * 根据用户手机号查找（手机号为登录账号）
     * @param mobile
     * @return
     */
    public UserDO findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public UserDO findById(String id) {
        return userDao.findById(id);
    }

    /**
     * 判断手机号是否存在
     * @param mobile
     * @return
     */
    public Boolean existMobile(String mobile){
        if(StringUtils.isEmpty(mobile)) {
            return null;
        }
        return userDao.existsByMobile(mobile);
    }

    /**
     * 判断手机号是否存在
     * @param username
     * @return
     */
    public Boolean existUserName(String username){
        if(StringUtils.isEmpty(username)) {
            return null;
        }
        return userDao.existsByUsername(username);
    }

    /**
     * 用户管理，获取用户基本信息列表
     * @param name 模糊查询
     * @param saasId 精准匹配，为空查全部
     * @param roleId 精准匹配，为空查全部
     * @return
     */
    public List<Map<String,Object>> queryBaseUserInfoList(String name,String saasId,String roleId){
        List<Map<String,Object>> result = new ArrayList<>();
        return result;
    }


}
