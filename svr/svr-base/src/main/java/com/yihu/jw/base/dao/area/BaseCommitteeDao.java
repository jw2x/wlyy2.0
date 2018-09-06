package com.yihu.jw.base.dao.area;

import com.yihu.jw.entity.base.area.BaseCommitteeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * 区县居委会 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  		2018年09月04日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseCommitteeDao extends PagingAndSortingRepository<BaseCommitteeDO, Integer>, JpaSpecificationExecutor<BaseCommitteeDO>  {
}