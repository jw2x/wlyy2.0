package com.yihu.jw.quota.dao.jpa.dimension;

import com.yihu.jw.quota.model.jpa.compute.TjCompute;
import com.yihu.jw.quota.model.jpa.dimension.TjDimensionMain;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/1.
 */
public interface TjDimensionMainDao extends PagingAndSortingRepository<TjDimensionMain, Long>, JpaSpecificationExecutor<TjDimensionMain> {
}
