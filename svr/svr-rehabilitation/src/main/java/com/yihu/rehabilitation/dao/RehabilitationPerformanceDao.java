package com.yihu.rehabilitation.dao;

import com.yihu.jw.rehabilitation.RehabilitationPerformanceDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RehabilitationPerformanceDao extends PagingAndSortingRepository<RehabilitationPerformanceDO, String>,
        JpaSpecificationExecutor<RehabilitationPerformanceDO> {

    @Query("from RehabilitationPerformanceDO w where w.id = ?1")
    RehabilitationPerformanceDO findById(String id);
}
