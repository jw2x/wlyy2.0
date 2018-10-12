package com.yihu.jw.base.service.customize;

import com.yihu.jw.base.dao.customize.UserHideModuleInterfaceDao;
import com.yihu.jw.entity.base.customize.UserHideModuleInterfaceDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * Service - 用户取消订阅的模块或功能
 * @author progr1mmer.
 * @date Created on 2018/9/14.
 */
@Service
public class UserHideModuleInterfaceService extends BaseJpaService<UserHideModuleInterfaceDO, UserHideModuleInterfaceDao> {
}
