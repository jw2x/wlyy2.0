package com.yihu.jw.base.dao.saas;

import com.yihu.jw.entity.base.saas.SaasBusinessCardDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - SAAS名片
 * Created by progr1mmer on 2018/9/7.
 */
public interface SaasBusinessCardDao extends PagingAndSortingRepository<SaasBusinessCardDO, Integer>, JpaSpecificationExecutor<SaasBusinessCardDO> {

}
