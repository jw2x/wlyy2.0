package com.yihu.jw.base.dao.user;

import com.yihu.jw.entity.base.user.UserDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Dao - 后台管理员
 * Created by progr1mmer on 2018/8/20.
 */
public interface UserDao extends PagingAndSortingRepository<UserDO, String>, JpaSpecificationExecutor<UserDO> {

    List<UserDO> findByEnabled(boolean enabled);

    UserDO findByMobile(String mobile);

    UserDO findById(String id);

    boolean existsByMobile(String mobile);

    boolean existsByUsername(String username);
}
