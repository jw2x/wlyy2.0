package com.yihu.jw.business.user.dao;

import com.yihu.jw.base.user.BaseEmployRoleDO;
import com.yihu.jw.base.user.BaseRoleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by LiTaohong on 2017/11/28.
 */
public interface EmployRoleDao extends PagingAndSortingRepository<BaseEmployRoleDO, String>, JpaSpecificationExecutor<BaseEmployRoleDO> {

    @Query("from BaseEmployRoleDO ba where ba.employId = ?1")
    List<BaseEmployRoleDO> findRoleListByEmployId(String employId);

    @Query("delete from BaseEmployRoleDO ba where ba.employId = ?1 and ba.roleId = ?2")
    int deleteOneByEmployIdAndRoleId(String employId,String roleId);

    @Query("delete from BaseEmployRoleDO ba where ba.employId = ?1 and ba.roleId in (?2)")
    int deleteManyByEmployIdAndRoleIds(String employId,String roleIds);
}
