package com.yihu.jw.base.dao.customize;

import com.yihu.jw.entity.base.customize.UserHideModuleInterfaceDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 用户取消订阅的模块或功能
 * @author progr1mmer.
 * @date Created on 2018/8/20.
 */
public interface UserHideModuleInterfaceDao extends PagingAndSortingRepository<UserHideModuleInterfaceDO, Integer>, JpaSpecificationExecutor<UserHideModuleInterfaceDO> {
}
