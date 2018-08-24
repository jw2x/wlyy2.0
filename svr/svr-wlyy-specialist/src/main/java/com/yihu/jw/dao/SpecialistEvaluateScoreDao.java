package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/8/22.
 */

import com.yihu.jw.entity.specialist.SpecialistEvaluateScoreDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-08-22 9:39
 * @desc 评论得分
 **/
public interface SpecialistEvaluateScoreDao extends PagingAndSortingRepository<SpecialistEvaluateScoreDO, String>, JpaSpecificationExecutor<SpecialistEvaluateScoreDO> {
}
