package com.yihu.jw.healthyhouse.dao.area;

import com.yihu.jw.healthyhouse.model.area.BaseCity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * 城市字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseCityDao extends PagingAndSortingRepository<BaseCity, Integer>, JpaSpecificationExecutor<BaseCity>  {
    BaseCity findByName(String name);

}