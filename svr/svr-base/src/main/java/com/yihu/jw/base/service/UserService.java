package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.UserDao;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.utils.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
}
