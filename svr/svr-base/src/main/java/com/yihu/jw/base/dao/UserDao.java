package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.user.UserDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 用户
 * Created by progr1mmer on 2018/8/20.
 */
public interface UserDao extends PagingAndSortingRepository<UserDO, String>, JpaSpecificationExecutor<UserDO> {
}
