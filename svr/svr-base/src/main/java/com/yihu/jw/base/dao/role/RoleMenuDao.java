package com.yihu.jw.base.dao.role;

import com.yihu.jw.entity.base.role.RoleMenuDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/9/26.
 */
public interface RoleMenuDao extends PagingAndSortingRepository<RoleMenuDO, String>, JpaSpecificationExecutor<RoleMenuDO> {
}
