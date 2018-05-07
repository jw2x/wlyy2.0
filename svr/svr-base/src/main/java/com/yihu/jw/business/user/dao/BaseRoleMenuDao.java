//package com.yihu.jw.business.user.dao;
//
//import com.yihu.jw.base.user.BaseEmployRoleDO;
//import com.yihu.jw.base.user.BaseRoleDO;
//import com.yihu.jw.base.user.BaseRoleMenuDO;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
//import java.util.List;
//
///**
// * Created by LiTaohong on 2017/11/28.
// */
//public interface BaseRoleMenuDao extends PagingAndSortingRepository<BaseRoleMenuDO, String>, JpaSpecificationExecutor<BaseRoleMenuDO> {
//
//
//    @Query("from BaseRoleMenuDO ba where ba.roleId = ?1")
//    List<BaseRoleMenuDO> findRoleMenuListByRoleId(String employId);
//
//    @Query("delete from BaseRoleMenuDO ba where ba.roleId = ?1 and ba.menuId = ?2")
//    int deleteOneByRoleIdAndMenuId(String roleId,String menuId);
//
//    @Query("delete from BaseRoleMenuDO ba where ba.roleId = ?1 and ba.menuId in (?2)")
//    int deleteManyByRoleIdAndMenuIds(String roleId,String menuIds);
//}
