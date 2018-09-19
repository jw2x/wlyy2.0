package com.yihu.jw.healthyhouse.dao;

import com.yihu.jw.healthyhouse.model.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HZY
 * @created 2018/9/18 19:21
 */
public interface UserDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query("from User u where u.loginCode=?1 and u.activated<>0 ")
    User findByLoginCode(String loginCode);

    @Query("from User u where u.name=?1 and u.activated<>0 ")
    User findByName(String name);

    @Transactional
    @Modifying
    @Query("update User u set u.activated = 0 where u.loginCode = ?1 ")
    void delete(String loginCode);
}
