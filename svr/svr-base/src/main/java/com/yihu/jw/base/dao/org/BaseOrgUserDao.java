package com.yihu.jw.base.dao.org;

import com.yihu.jw.entity.base.org.BaseOrgUserDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


/**
 * 
 * 机构与机构管理员关联信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年10月20日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseOrgUserDao extends PagingAndSortingRepository<BaseOrgUserDO, String>, JpaSpecificationExecutor<BaseOrgUserDO>  {

    List<BaseOrgUserDO> findAllByOrgCode(String orgCode);
}