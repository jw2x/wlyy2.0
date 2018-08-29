package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/8/21.
 */

import com.yihu.jw.entity.specialist.SpecialistEvaluateDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-08-21 16:41
 * @desc 评论表
 **/
public interface SpecialistEvaluateDao extends PagingAndSortingRepository<SpecialistEvaluateDO, String>, JpaSpecificationExecutor<SpecialistEvaluateDO> {

}
