package com.yihu.jw.base.dao.team;

import com.yihu.jw.entity.base.team.BaseTeamMemberDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/8/31.
 */
public interface BaseTeamMemberDao extends PagingAndSortingRepository<BaseTeamMemberDO, String>, JpaSpecificationExecutor<BaseTeamMemberDO> {
}
