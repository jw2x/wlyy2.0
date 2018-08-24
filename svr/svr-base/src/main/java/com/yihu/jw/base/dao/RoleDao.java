package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.role.RoleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 角色
 * Created by progr1mmer on 2018/8/17.
 */
public interface RoleDao extends PagingAndSortingRepository<RoleDO, String>, JpaSpecificationExecutor<RoleDO> {

}
