package com.yihu.jw.manage.dao.system;

import com.yihu.jw.manage.model.system.ManageUserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenweida on 2017/6/9.
 */
public interface UserRoleDao extends PagingAndSortingRepository<ManageUserRole, Long>, JpaSpecificationExecutor<ManageUserRole> {

    @Query("from ManageUserRole m where m.roleCode = ?1")
    List<ManageUserRole> findByRoleCode(String roleCode);

    @Transactional
    @Modifying
    @Query("delete from ManageUserRole r where r.roleCode = ?1 and r.userCode=?2")
    void deleteUserRole(String roleCode, String userCode);

    @Query("from ManageUserRole r where r.roleCode = ?1 and r.userCode=?2")
    ManageUserRole findByRoleAndUserCode(String roleCode, String userCode);
}
