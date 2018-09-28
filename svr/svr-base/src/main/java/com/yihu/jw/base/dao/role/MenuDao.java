package com.yihu.jw.base.dao.role;

import com.yihu.jw.entity.base.role.MenuDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/9/26.
 */
public interface MenuDao extends PagingAndSortingRepository<MenuDO, String>, JpaSpecificationExecutor<MenuDO> {

    @Modifying
    @Query("update MenuDO p set p.status=?2 where p.id=?1")
    void updateStatus(String id,Integer status);

    @Modifying
    @Query("update MenuDO p set p.sort=p.sort+1 where p.parentId=?1 and p.sort=?2")
    void addSort(String parentId,Integer sort);

    @Modifying
    @Query("update MenuDO p set p.sort=p.sort-1 where p.parentId=?1 and p.sort=?2")
    void subSort(String parentId,Integer sort);

    @Query("select count(*) from MenuDO a where a.name = ?1 ")
    int isExistName(String name);

    @Query("select count(*) from MenuDO a where a.parentId = ?1 ")
    int countMenuByParentId(String parentId);
}
