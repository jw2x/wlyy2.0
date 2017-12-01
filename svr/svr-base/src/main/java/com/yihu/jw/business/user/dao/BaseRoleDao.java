package com.yihu.jw.business.user.dao;

import com.yihu.jw.base.user.BaseRoleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by LiTaohong on 2017/11/28.
 */
public interface BaseRoleDao extends PagingAndSortingRepository<BaseRoleDO, String>, JpaSpecificationExecutor<BaseRoleDO> {


    @Query("from BaseRoleDO b where b.id = ?1")
    BaseRoleDO findOneById(String id);

    //角色与saasId为一对多关系
    @Query("from BaseRoleDO b where b.saasId = ?1")
    List<BaseRoleDO> findAllBySaasId(String saasId);

    @Query("from BaseRoleDO b where b.saasId = ?1 and b.name = ?2")
    BaseRoleDO findOneBySaasIdAndName(String saasId,String name);

    @Query("from BaseRoleDO b where b.name like %?1%")
    List<BaseRoleDO> findAllByName(String name);
}
