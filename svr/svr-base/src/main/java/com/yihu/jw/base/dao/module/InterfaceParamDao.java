package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.InterfaceParamDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 接口入参出参
 * @author yeshijie on 2018/9/28.
 */
public interface InterfaceParamDao extends PagingAndSortingRepository<InterfaceParamDO, String>, JpaSpecificationExecutor<InterfaceParamDO> {
    

}
