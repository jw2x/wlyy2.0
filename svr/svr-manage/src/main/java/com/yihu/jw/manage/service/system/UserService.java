package com.yihu.jw.manage.service.system;

import com.yihu.jw.manage.dao.system.UserDao;
import com.yihu.jw.manage.dao.system.UserRoleDao;
import com.yihu.jw.manage.model.system.ManageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/6/9.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    public ManageUser findByAccount(String username) {
        return userDao.findByAccount(username);
    }

    public ManageUser findByCode(String usercode) {
        return userDao.findByCode(usercode);
    }
}
