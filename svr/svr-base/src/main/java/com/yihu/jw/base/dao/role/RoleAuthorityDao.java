package com.yihu.jw.base.dao.role;

import com.yihu.jw.entity.base.role.RoleAuthorityDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 角色权限
 * @author progr1mmer.
 * @date Created on 2018/9/14.
 */
public interface RoleAuthorityDao extends PagingAndSortingRepository<RoleAuthorityDO, Integer>, JpaSpecificationExecutor<RoleAuthorityDO> {
}
