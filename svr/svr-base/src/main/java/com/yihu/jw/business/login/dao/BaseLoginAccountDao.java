package com.yihu.jw.business.login.dao;

import com.yihu.jw.base.login.BaseLoginAccount;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/20.
 */
public interface BaseLoginAccountDao extends PagingAndSortingRepository<BaseLoginAccount, Long>, JpaSpecificationExecutor<BaseLoginAccount> {
}
