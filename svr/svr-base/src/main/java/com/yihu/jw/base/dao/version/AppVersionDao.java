package com.yihu.jw.base.dao.version;

import com.yihu.jw.entity.base.version.AppVersionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * app版本号表 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年09月07日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface AppVersionDao extends PagingAndSortingRepository<AppVersionDO, Integer>, JpaSpecificationExecutor<AppVersionDO>  {
}