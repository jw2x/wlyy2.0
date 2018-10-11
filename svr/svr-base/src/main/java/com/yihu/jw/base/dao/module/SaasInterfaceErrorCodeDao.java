package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.SaasInterfaceErrorCodeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 接口错误说明
 * @author yeshijie on 2018/10/11.
 */
public interface SaasInterfaceErrorCodeDao extends PagingAndSortingRepository<SaasInterfaceErrorCodeDO, String>,
        JpaSpecificationExecutor<SaasInterfaceErrorCodeDO> {


}
