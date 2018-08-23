package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.RoleDao;
import com.yihu.jw.entity.base.role.RoleDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * Service - 角色
 * Created by progr1mmer on 2018/8/17.
 */
@Service
public class RoleService extends BaseJpaService<RoleDO, RoleDao> {
}
