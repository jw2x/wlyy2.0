package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationDetailDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

/**
 * Created by 刘文彬 on 2018/8/20.
 */
public interface RehabilitationDetailDao extends PagingAndSortingRepository<RehabilitationDetailDO, Long>,JpaSpecificationExecutor<RehabilitationDetailDO> {

    @Query(" select count(1) from RehabilitationDetailDO where status =?1 and programId=?2 and executeTime>=?3 and executeTime<=?4 ")
    Integer todayBacklogCount(Integer status,String programId,Date executeStartTime,Date executeEndTime);
}
