package com.yihu.jw.base.dao.team;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.team.BaseTeamMemberDO;

import java.util.List;
import java.util.Map;

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

    @Query("select doc.name,doc.idcard,doc.id from BaseDoctorDO doc where doc.id in (select team.doctorCode from BaseTeamMemberDO team where team.orgCode = ?1 and team.teamCode = ?2)")
    List<Map<String,Object>> getTeamMemberList();

}