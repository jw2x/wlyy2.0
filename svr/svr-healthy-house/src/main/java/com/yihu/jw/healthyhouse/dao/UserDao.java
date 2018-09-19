package com.yihu.jw.healthyhouse.dao;

import com.yihu.jw.healthyhouse.model.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author HZY
 * @created 2018/9/18 19:21
 */
public interface UserDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {
}
