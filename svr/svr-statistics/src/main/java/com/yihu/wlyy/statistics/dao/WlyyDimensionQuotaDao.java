package com.yihu.wlyy.statistics.dao;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/7/10.
 */
public interface WlyyDimensionQuotaDao extends PagingAndSortingRepository<DimensionQuotaDO, Long>, JpaSpecificationExecutor< DimensionQuotaDO> {
    @Query("from DimensionQuotaDO w where w.quotaCode=?1 order by sort asc")
    List<DimensionQuotaDO> findDimensionQuotasByQuotaCode(String id);
}
