package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/8/22.
 */

import com.yihu.jw.entity.specialist.SpecialistEvaluateLabelDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-08-22 9:38
 * @desc 评论标签
 **/
public interface SpecialistEvaluateLabelDao extends PagingAndSortingRepository<SpecialistEvaluateLabelDO, String>, JpaSpecificationExecutor<SpecialistEvaluateLabelDO> {
}
