package com.yihu.jw.business.version.service;

import com.yihu.jw.business.version.dao.ServerVersionLogDao;
import com.yihu.jw.business.version.model.BaseServerVersionLog;
import com.yihu.jw.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ServerVersionLogService extends BaseJpaService<BaseServerVersionLog, ServerVersionLogDao> {
    @Autowired
    private ServerVersionLogDao baseServerVersionLogDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

}
