package com.yihu.jw.business.user.dao;

import com.yihu.jw.base.user.BaseRoleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by LiTaohong on 2017/11/28.
 */
public interface RoleDao extends PagingAndSortingRepository<BaseRoleDO, String>, JpaSpecificationExecutor<BaseRoleDO> {
}
