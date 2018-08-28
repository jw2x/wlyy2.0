package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.user.UserRoleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 用户角色
 * Created by progr1mmer on 2018/8/20.
 */
public interface UserRoleDao extends PagingAndSortingRepository<UserRoleDO, Integer>, JpaSpecificationExecutor<UserRoleDO> {
}
