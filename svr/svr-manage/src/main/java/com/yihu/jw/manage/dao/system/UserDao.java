package com.yihu.jw.manage.dao.system;

import com.yihu.jw.manage.model.system.ManageRoleMenu;
import com.yihu.jw.manage.model.system.ManageUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenweida on 2017/6/9.
 */
public interface UserDao extends PagingAndSortingRepository<ManageUser, Long>, JpaSpecificationExecutor<ManageUser> {
   @Query("from ManageUser u where u.loginAccount=?1 and u.status=1 ")
    ManageUser findByAccount(String username);

    @Query("from ManageUser u where u.code=?1 and u.status=1 ")
    ManageUser findByCode(String usercode);

    @Transactional
    @Modifying
    @Query("update ManageUser u set u.status = -1 where u.code = ?1 ")
    void delete(String code);
}
