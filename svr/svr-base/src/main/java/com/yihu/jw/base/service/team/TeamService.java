package com.yihu.jw.base.service.team;


import com.yihu.jw.base.dao.team.BaseTeamDao;
import com.yihu.jw.base.dao.team.BaseTeamMemberDao;
import com.yihu.jw.entity.base.team.BaseTeamDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Trick on 2018/8/31.
 */
@Service
@Transactional
public class TeamService extends BaseJpaService<BaseTeamDO, BaseTeamDao> {

    @Autowired
    private BaseTeamDao teamDao;

    @Autowired
    private BaseTeamMemberDao baseTeamMemberDao;

}
