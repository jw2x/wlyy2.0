package com.yihu.jw.base.dao.people_num;

import com.yihu.jw.entity.base.peopel_num.BasePeopleNumDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 基础人口基数信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  		2018年09月05日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BasePeopleNumDao extends PagingAndSortingRepository<BasePeopleNumDO, String>, JpaSpecificationExecutor<BasePeopleNumDO>  {
}