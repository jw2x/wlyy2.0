package com.yihu.rehabilitation.dao;

import com.yihu.jw.rehabilitation.RehabilitationPlanningDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RehabilitationPlanningDao extends PagingAndSortingRepository<RehabilitationPlanningDO, String>,
        JpaSpecificationExecutor<RehabilitationPlanningDO> {

    @Query("from RehabilitationPlanningDO w where w.id = ?1 and w.status = 0")
    RehabilitationPlanningDO findById(String id);
}
