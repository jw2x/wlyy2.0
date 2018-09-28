package com.yihu.jw.base.dao.population;

import com.yihu.jw.entity.base.population.BasePopulationDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 基础人口基数信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年09月26日 	update
 *
 * </pre>
 * @since 1.
 */
public interface BasePopulationDao extends PagingAndSortingRepository<BasePopulationDO, String>, JpaSpecificationExecutor<BasePopulationDO>  {
}