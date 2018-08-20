package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.UserRoleDao;
import com.yihu.jw.entity.base.user.UserRoleDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * Created by progr1mmer on 2018/8/20.
 */
@Service
public class UserRoleService extends BaseJpaService<UserRoleDO, UserRoleDao> {
}
