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

    @Query("from WxMenu m where m.code = ?1 and m.status = 1")
    WxMenu findByCode(String code);

    @Query("from WxMenu m where m.wechatCode =?1 and m.status = 1 order  by m.supMenucode ,m.sort")
    List<WxMenu> findByWechatCode(String wechatCode);

    @Query("from WxMenu m where m.wechatCode = ?1 and  m.supMenucode= ?2 and m.status =1 order by m.sort")
    List<WxMenu> findChildMenus(String wechatCode, String sup_menucode);

    /**
     * 根据wechatCode判断父菜单的sort是否重复
     * @param wechatCode
     * @param sort
     */
    @Query("from WxMenu m where m.wechatCode = ?1 and m.status =1 and m.sort =?2 and m.supMenucode is null and m.code != ?3")
    WxMenu findByWechatCodeExcludeSortFromParent(String wechatCode, Integer sort,String code);

    /**
     * 根据wechatCode,supMenucode判断子菜单的sort是否重复
     * @param wechatCode
     * @param sort
     */
    @Query("from WxMenu m where m.wechatCode = ?1 and m.status =1 and m.sort =?2 and m.supMenucode =?3 and m.code != ?4")
    WxMenu findByWechatCodeExcludeSortFromChild(String wechatCode, Integer sort,String supMenucode,String code);

    @Query("from WxMenu m where m.wechatCode =?1 and m.status = 1 and m.supMenucode is null order by m.supMenucode ,m.sort")
    List<WxMenu> findParentMenuByWechatCode(String wechatCode);
}
