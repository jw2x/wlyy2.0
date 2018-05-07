//package com.yihu.jw.business.wx.dao;
//
//import com.yihu.jw.base.wx.WxMenuDO;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2017/5/19 0019.
// */
//public interface WxMenuDao  extends PagingAndSortingRepository<WxMenuDO, String>, JpaSpecificationExecutor<WxMenuDO> {
//
//
//    @Query("from WxMenuDO m where m.id = ?1 and m.status = 1")
//    WxMenuDO findById(String id);
//
//    @Query("from WxMenuDO m where m.wechatId =?1 and m.status = 1 order  by m.supMenuid ,m.sort")
//    List<WxMenuDO> findByWechatId(String wechatId);
//
//    @Query("from WxMenuDO m where m.wechatId = ?1 and  m.supMenuid= ?2 and m.status =1 order by m.sort")
//    List<WxMenuDO> findChildMenus(String wechatId, String supMenuid);
//
//    @Query("from WxMenuDO m where  m.supMenuid= ?1 and m.status =1 order by m.sort")
//    List<WxMenuDO> findChildMenus( String supMenuid);
//
//    /**
//     * 根据wechatId判断父菜单的sort是否重复
//     * @param wechatId
//     * @param sort
//     */
//    @Query("from WxMenuDO m where m.wechatId = ?1 and m.status =1 and m.sort =?2 and m.supMenuid ='0' and m.id != ?3")
//    WxMenuDO findByWechatIdExcludeSortFromParent(String wechatId, Integer sort,String id);
//
//    /**
//     * 根据wechatId,supMenucode判断子菜单的sort是否重复
//     * @param wechatId
//     * @param sort
//     */
//    @Query("from WxMenuDO m where m.wechatId = ?1 and m.status =1 and m.sort =?2 and m.supMenuid =?3 and m.id != ?4")
//    WxMenuDO findByWechatIdExcludeSortFromChild(String wechatId, Integer sort,String supMenuid,String id);
//
//    @Query("from WxMenuDO m where m.wechatId =?1 and m.status = 1 and  m.supMenuid = '0' order by m.supMenuid ,m.sort")
//    List<WxMenuDO> findParentMenuByWechatId(String wechatId);
//
//}
