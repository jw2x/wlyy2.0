package com.yihu.jw.business.user.dao;

import com.yihu.jw.base.user.BaseEmployDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/11/28.
 */
public interface EmployeeDao extends PagingAndSortingRepository<BaseEmployDO, String>, JpaSpecificationExecutor<BaseEmployDO> {
}
