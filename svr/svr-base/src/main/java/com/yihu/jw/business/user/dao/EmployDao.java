package com.yihu.jw.business.user.dao;

import com.yihu.jw.entity.base.user.EmployDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/11/28.
 */

/**
 * 基础用户类接口
 */
public interface EmployDao extends PagingAndSortingRepository<EmployDO, String>, JpaSpecificationExecutor<EmployDO> {

    @Query("from BaseEmployDO ba where ba.saasId = ?1")
    List<EmployDO> findAllBySaasId(String saasId);

    @Query("from BaseEmployDO ba where ba.name like ?1 and ba.saasId = ?2")
    List<EmployDO> findAllByNameAndSaasId(String name, String saasId);

    @Query("from BaseEmployDO ba where ba.phone = ?1 and ba.saasId = ?2")
    EmployDO findByPhoneAndSaasId(String phone, String saasId);
}
