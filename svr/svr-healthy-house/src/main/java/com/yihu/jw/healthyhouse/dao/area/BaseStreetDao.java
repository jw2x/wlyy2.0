package com.yihu.jw.healthyhouse.dao.area;

import com.yihu.jw.healthyhouse.model.area.BaseStreetDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * 街道字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseStreetDao extends PagingAndSortingRepository<BaseStreetDO, Integer>, JpaSpecificationExecutor<BaseStreetDO>  {

    BaseStreetDO findByName(String name);
}