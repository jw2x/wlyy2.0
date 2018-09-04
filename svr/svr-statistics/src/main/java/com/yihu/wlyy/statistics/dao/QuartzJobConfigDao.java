package com.yihu.wlyy.statistics.dao;

import com.yihu.jw.entity.base.statistics.JobConfigDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface QuartzJobConfigDao extends PagingAndSortingRepository<JobConfigDO, String>, JpaSpecificationExecutor<JobConfigDO> {

    @Query(" FROM JobConfigDO a WHERE a.id=?1 and a.status=?2 and a.del='1'")
    JobConfigDO findById(String code, String status);

    @Query(" FROM JobConfigDO a WHERE a.status=?1 and a.del='1'")
    List<JobConfigDO> findByAll(String s);

    @Query(" FROM JobConfigDO a WHERE  a.del='1' and a.id !=11 order by a.id asc")
    List<JobConfigDO> findByIds();

    @Query(" FROM JobConfigDO a WHERE a.id=?1 and a.del='1'")
    JobConfigDO findById(String id);

    @Modifying
    @Query(" update JobConfigDO a set a.status=?2 where a.id=?1 ")
    int updateStatus(String id, String status);

    @Query(" from JobConfigDO p where p.status=?1 and p.del='1'")
    List<JobConfigDO> findByStatus(String s);

}
