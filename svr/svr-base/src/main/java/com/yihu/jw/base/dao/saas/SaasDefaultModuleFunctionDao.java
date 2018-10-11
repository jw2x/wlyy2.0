package com.yihu.jw.base.dao.saas;

import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.saas.SaasDefaultModuleFunctionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Dao - Saas默认模块功能
 * Created by progr1mmer on 2018/8/17.
 */
public interface SaasDefaultModuleFunctionDao extends PagingAndSortingRepository<SaasDefaultModuleFunctionDO, Integer>, JpaSpecificationExecutor<SaasDefaultModuleFunctionDO> {

    List<SaasDefaultModuleFunctionDO> findBySaasType(Integer saasType);

    @Modifying
    @Query("delete from SaasDefaultModuleFunctionDO sdf where sdf.saasType = ?1 ")
    void deleteBySaasType(Integer saasType);
}
