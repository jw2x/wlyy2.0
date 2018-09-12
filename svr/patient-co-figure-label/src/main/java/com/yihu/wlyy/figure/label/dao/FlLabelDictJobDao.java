package com.yihu.wlyy.figure.label.dao;

import com.yihu.figure_label.entity.FlLabelDictJob;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author lith on 2018.03.14
 * 定时任务具体字典维度
 */
public interface FlLabelDictJobDao extends PagingAndSortingRepository<FlLabelDictJob, String>, JpaSpecificationExecutor<FlLabelDictJob> {

    /**
     * 根据jobId查询
     * @param jobId
     * @return
     */
    @Query(" FROM FlLabelDictJob a WHERE a.jobId=?1")
    FlLabelDictJob findByJobId(Long jobId);

    /**
     * 根据标签类型查询
     * @param type
     * @return
     */
    @Query(" FROM FlLabelDictJob a WHERE a.labelType=?1")
    FlLabelDictJob findByLabelType(String type);

}
