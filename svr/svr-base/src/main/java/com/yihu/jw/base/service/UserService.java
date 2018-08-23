package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.UserDao;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * Service - 用户
 * Created by progr1mmer on 2018/8/20.
 */
@Service
public class UserService extends BaseJpaService<UserDO, UserDao> {
}
