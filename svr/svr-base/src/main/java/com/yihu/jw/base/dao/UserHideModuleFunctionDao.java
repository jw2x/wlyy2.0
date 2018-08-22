package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.user.UserHideModuleFunctionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by progr1mmer on 2018/8/20.
 */
public interface UserHideModuleFunctionDao extends PagingAndSortingRepository<UserHideModuleFunctionDO, Integer>, JpaSpecificationExecutor<UserHideModuleFunctionDO> {
}
