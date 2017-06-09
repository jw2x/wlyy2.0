package com.yihu.jw.manage.dao.system;

import com.yihu.jw.manage.model.system.ManageRoleMenu;
import com.yihu.jw.manage.model.system.ManageUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/9.
 */
public interface UserDao extends PagingAndSortingRepository<ManageUser, Integer>, JpaSpecificationExecutor<ManageUser> {
   @Query("from ManageUser u where u.loginAccount=?1 and u.status=1 ")
    ManageUser findByAccount(String username);
}
