package com.yihu.jw.base.dao.score;

import com.yihu.jw.entity.base.score.BaseEvaluateDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/8/31.
 */
public interface BaseEvaluateDao extends PagingAndSortingRepository<BaseEvaluateDO, String>, JpaSpecificationExecutor<BaseEvaluateDO> {

}
