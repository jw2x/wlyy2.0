package com.yihu.jw.version.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.version.dao.ServerVersionDao;
import com.yihu.jw.version.dao.ServerVersionLogDao;
import com.yihu.jw.version.model.BaseServerVersion;
import com.yihu.jw.version.model.BaseServerVersionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ServerVersionLogService extends BaseJpaService<BaseServerVersion, ServerVersionDao> {
    @Autowired
    private ServerVersionLogDao baseServerVersionLogDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public BaseServerVersionLog createBaseServerVersionLog(BaseServerVersionLog baseServerVersionLog) throws ApiException {
        if (StringUtils.isEmpty(baseServerVersionLog.getName())) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        BaseServerVersionLog BaseServerVersionTmp = baseServerVersionLogDao.findByName(baseServerVersionLog.getName());
        if (BaseServerVersionTmp != null) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return baseServerVersionLogDao.save(baseServerVersionLog);
    }


    public BaseServerVersionLog findByCode(String code) {
        BaseServerVersionLog serverVersion = baseServerVersionLogDao.findByCode(code);
        if (serverVersion == null) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return serverVersion;
    }

    @Transactional
    public void deleteBaseServerVersionLog(String code) {
        BaseServerVersionLog baseServerVersion = baseServerVersionLogDao.findByCode(code);
        if (baseServerVersion == null) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        baseServerVersion.setStatus(-1);
    }


}
