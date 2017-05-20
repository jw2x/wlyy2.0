package com.yihu.jw.wx.dao;

import com.yihu.jw.wx.model.WxMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public interface WxMenuDao  extends PagingAndSortingRepository<WxMenu, Long>, JpaSpecificationExecutor<WxMenu> {

    @Query("from WxMenu m where m.code = ?1")
    WxMenu findByCode(String code);
}
