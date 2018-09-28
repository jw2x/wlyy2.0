package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.InterfaceErrorCodeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 接口错误说明
 * @author yeshijie on 2018/9/28.
 */
public interface InterfaceErrorCodeDao extends PagingAndSortingRepository<InterfaceErrorCodeDO, String>, JpaSpecificationExecutor<InterfaceErrorCodeDO> {


}
