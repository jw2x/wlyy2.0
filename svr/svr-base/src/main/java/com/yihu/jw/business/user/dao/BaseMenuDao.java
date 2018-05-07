//package com.yihu.jw.business.user.dao;
//
//import com.yihu.jw.base.user.BaseMenuDO;
//import com.yihu.jw.base.user.BaseMenuDO;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
//import java.util.List;
//
///**
// * Created by LiTaohong on 2017/12/05.
// */
//public interface BaseMenuDao extends PagingAndSortingRepository<BaseMenuDO, String>, JpaSpecificationExecutor<BaseMenuDO> {
//
//    //角色与saasId为一对多关系
//    @Query("from BaseMenuDO b where b.saasId = ?1")
//    List<BaseMenuDO> findAllBySaasId(String saasId);
//
//    @Query("from BaseMenuDO b where b.saasId = ?1 and b.name = ?2")
//    BaseMenuDO findOneBySaasIdAndName(String saasId, String name);
//
//    @Query("from BaseMenuDO b where b.name like %?1%")
//    List<BaseMenuDO> findAllByName(String name);
//
//    @Query("from BaseMenuDO b where b.saasId = ?1 and b.parentId = ?2")
//    List<BaseMenuDO> getChildrenMenuList(String saasId,String parentId);
//
//
//
//
//}
