package com.yihu.jw.base.dao.team;

import com.yihu.jw.entity.base.team.BaseTeamDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/8/31.
 */
public interface BaseTeamDao extends PagingAndSortingRepository<BaseTeamDO, String>, JpaSpecificationExecutor<BaseTeamDO> {
}
