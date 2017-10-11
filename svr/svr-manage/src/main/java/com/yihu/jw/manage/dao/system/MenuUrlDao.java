package com.yihu.jw.manage.dao.system;

import com.yihu.jw.manage.model.system.ManageMenu;
import com.yihu.jw.manage.model.system.ManageMenuUrl;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3 0003.
 */
public interface MenuUrlDao extends PagingAndSortingRepository<ManageMenuUrl, Long>, JpaSpecificationExecutor<ManageMenu> {

    @Query("from  ManageMenuUrl m where m.menuCode = ?1")
    List<ManageMenuUrl> getListByMenuCode(String menuCode);


    @Transactional
    @Modifying
    @Query("delete ManageMenuUrl m where m.menuCode = ?1")
    void delete(String menuCode);
}
