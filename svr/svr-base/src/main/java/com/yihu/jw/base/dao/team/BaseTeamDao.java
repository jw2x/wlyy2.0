package com.yihu.jw.base.dao.team;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.team.BaseTeamDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 团队信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseTeamDao extends PagingAndSortingRepository<BaseTeamDO, String>, JpaSpecificationExecutor<BaseTeamDO>  {
    /*@Query("select orgCode as code,OrgName as name from BaseTeamDO")
    List<Map<String,Object>> getTeamOrgList();*/
}