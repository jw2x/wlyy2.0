package com.yihu.jw.wx.dao;

import com.yihu.jw.wx.model.WxMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public interface WxMenuDao  extends PagingAndSortingRepository<WxMenu, Long>, JpaSpecificationExecutor<WxMenu> {

    @Query("from WxMenu m where m.code = ?1")
    WxMenu findByCode(String code);

    @Query("from WxMenu m where m.wechatCode =?1 and m.status = 1 order  by m.supMenucode ,m.sort")
    List<WxMenu> findByWechatCode(String wechatCode);
}
