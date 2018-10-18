package com.yihu.jw.base.dao.saas;

import com.yihu.jw.entity.base.saas.SaasUserDO;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 租户账号
 * @author yeshijie on 2018/10/16.
 */
public interface SaasUserDao extends PagingAndSortingRepository<SaasUserDO, String> {


}
