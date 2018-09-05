package com.yihu.jw.base.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.team.BaseTeamMemberDO;

/**
 * 
 * 团队成员 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseTeamMemberDao extends PagingAndSortingRepository<BaseTeamMemberDO, String>, JpaSpecificationExecutor<BaseTeamMemberDO>  {
}