package com.yihu.jw.manage.dao.system;

import com.yihu.jw.manage.model.system.ManageRoleMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenweida on 2017/6/9.
 */
public interface RoleMenuDao extends PagingAndSortingRepository<ManageRoleMenu, Long>, JpaSpecificationExecutor<ManageRoleMenu> {

    @Query("from ManageRoleMenu m where m.roleCode=?1")
    List<ManageRoleMenu> findByRoleCode(String roleCode);

    @Transactional
    @Modifying
    @Query("delete from ManageRoleMenu r where r.roleCode = ?2 and r.menuCode=?1")
    void delete(String menuCode, String roleCode);
}
