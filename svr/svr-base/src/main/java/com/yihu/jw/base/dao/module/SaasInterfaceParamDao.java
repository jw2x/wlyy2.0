package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.SaasInterfaceParamDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 接口入参出参
 * @author yeshijie on 2018/10/11.
 */
public interface SaasInterfaceParamDao extends PagingAndSortingRepository<SaasInterfaceParamDO, String>, JpaSpecificationExecutor<SaasInterfaceParamDO> {

}
