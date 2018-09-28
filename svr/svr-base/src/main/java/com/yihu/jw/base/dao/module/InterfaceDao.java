package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.InterfaceDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 接口
 * @author yeshijie on 2018/9/28.
 */

public interface InterfaceDao extends PagingAndSortingRepository<InterfaceDO, String>, JpaSpecificationExecutor<InterfaceDO> {

}
