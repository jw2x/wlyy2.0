package com.yihu.jw.base.dao.org;

import com.yihu.jw.entity.base.org.BaseOrgSaasDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


/**
 * 
 * 机构与Saas关联信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年10月20日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseOrgSaasDao extends PagingAndSortingRepository<BaseOrgSaasDO, String>, JpaSpecificationExecutor<BaseOrgSaasDO>  {

    List<BaseOrgSaasDO> findBySaasid(String saasId);
}