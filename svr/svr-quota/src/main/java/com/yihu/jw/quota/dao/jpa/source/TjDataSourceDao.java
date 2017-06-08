package com.yihu.jw.quota.dao.jpa.source;

import com.yihu.jw.quota.model.jpa.source.TjDataSource;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/1.
 */
public interface TjDataSourceDao extends PagingAndSortingRepository<TjDataSource, Long>, JpaSpecificationExecutor<TjDataSource> {
}
