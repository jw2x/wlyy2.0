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

    @Query("from WxMenu m where m.wechatCode =?1 and m.status = 1 order  by m.supMenucode ,m.sort")
    List<WxMenu> findByWechatCode(String wechatCode);

    @Query("from WxMenu m where m.wechatCode = ?1 and  m.supMenucode= ?2 and m.status =1 order by m.sort")
    List<WxMenu> findChildMenus(String wechatCode, String sup_menucode);

    @Query("from WxMenu m where  m.supMenucode= ?1 and m.status =1 order by m.sort")
    List<WxMenu> findChildMenus( String parentCode);

    /**
     * 根据wechatCode判断父菜单的sort是否重复
     * @param wechatCode
     * @param sort
     */
    @Query("from WxMenu m where m.wechatCode = ?1 and m.status =1 and m.sort =?2 and m.supMenucode ='0' and m.code != ?3")
    WxMenu findByWechatCodeExcludeSortFromParent(String wechatCode, Integer sort,String code);

    /**
     * 根据wechatCode,supMenucode判断子菜单的sort是否重复
     * @param wechatCode
     * @param sort
     */
    @Query("from WxMenu m where m.wechatCode = ?1 and m.status =1 and m.sort =?2 and m.supMenucode =?3 and m.code != ?4")
    WxMenu findByWechatCodeExcludeSortFromChild(String wechatCode, Integer sort,String supMenucode,String code);

    @Query("from WxMenu m where m.wechatCode =?1 and m.status = 1 and  m.supMenucode = '0' order by m.supMenucode ,m.sort")
    List<WxMenu> findParentMenuByWechatCode(String wechatCode);

}
