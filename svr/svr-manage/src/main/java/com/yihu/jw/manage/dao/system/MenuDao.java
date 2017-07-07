package com.yihu.jw.manage.dao.system;

import com.yihu.jw.manage.model.system.ManageMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/6/9.
 */
public interface MenuDao extends PagingAndSortingRepository<ManageMenu, Long>, JpaSpecificationExecutor<ManageMenu> {

    @Query("from ManageMenu m where m.status!=-1 and m.code=?1")
    ManageMenu findByCode(String code);

    @Query("from ManageMenu m where m.status!=-1 and m.parentCode = ?1 ")
    List<ManageMenu> getChildMenus(String parentCode);

    @Query("from ManageMenu m where m.status!=-1 and m.parentCode = '0' ")
    List<ManageMenu> getParentMenus();

}
