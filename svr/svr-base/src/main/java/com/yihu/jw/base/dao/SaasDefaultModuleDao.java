package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.saas.SaasDefaultModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by progr1mmer on 2018/8/17.
 */
public interface SaasDefaultModuleDao extends PagingAndSortingRepository<SaasDefaultModuleDO, Integer>, JpaSpecificationExecutor<SaasDefaultModuleDO> {

    List<SaasDefaultModuleDO> findByType(SaasDO.Type type);
}
