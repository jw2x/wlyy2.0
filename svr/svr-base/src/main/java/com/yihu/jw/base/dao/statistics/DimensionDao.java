package com.yihu.jw.base.dao.statistics;

import com.yihu.jw.entity.base.statistics.DimensionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author yeshijie on 2018/8/31.
 */
public interface DimensionDao extends PagingAndSortingRepository<DimensionDO, String>, JpaSpecificationExecutor<DimensionDO> {


}
