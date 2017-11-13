package com.yihu.jw.business.wx.dao;

import com.yihu.jw.base.wx.WxMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public interface WxMenuDao  extends PagingAndSortingRepository<WxMenu, String>, JpaSpecificationExecutor<WxMenu> {


    @Query("from WxMenu m where m.id = ?1 and m.status = 1")
    WxMenu findById(String id);

    @Query("from WxMenu m where m.wechatId =?1 and m.status = 1 order  by m.supMenuid ,m.sort")
    List<WxMenu> findByWechatId(String wechatId);

    @Query("from WxMenu m where m.wechatId = ?1 and  m.supMenuid= ?2 and m.status =1 order by m.sort")
    List<WxMenu> findChildMenus(String wechatId, String supMenuid);

    @Query("from WxMenu m where  m.supMenuid= ?1 and m.status =1 order by m.sort")
    List<WxMenu> findChildMenus( String supMenuid);

    /**
     * 根据wechatId判断父菜单的sort是否重复
     * @param wechatId
     * @param sort
     */
    @Query("from WxMenu m where m.wechatId = ?1 and m.status =1 and m.sort =?2 and m.supMenuid ='0' and m.id != ?3")
    WxMenu findByWechatIdExcludeSortFromParent(String wechatId, Integer sort,String id);

    /**
     * 根据wechatId,supMenucode判断子菜单的sort是否重复
     * @param wechatId
     * @param sort
     */
    @Query("from WxMenu m where m.wechatId = ?1 and m.status =1 and m.sort =?2 and m.supMenuid =?3 and m.id != ?4")
    WxMenu findByWechatIdExcludeSortFromChild(String wechatId, Integer sort,String supMenuid,String id);

    @Query("from WxMenu m where m.wechatId =?1 and m.status = 1 and  m.supMenuid = '0' order by m.supMenuid ,m.sort")
    List<WxMenu> findParentMenuByWechatId(String wechatId);

}
