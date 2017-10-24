package com.yihu.jw.business.login.dao;

import com.yihu.jw.business.login.model.BaseLoginLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/20.
 */
public interface BaseLoginLogDao extends PagingAndSortingRepository<BaseLoginLog, Long>, JpaSpecificationExecutor<BaseLoginLog> {
}
