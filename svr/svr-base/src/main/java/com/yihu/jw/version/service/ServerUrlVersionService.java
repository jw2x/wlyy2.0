package com.yihu.jw.version.service;

import com.yihu.jw.base.model.base.Function;
import com.yihu.jw.base.service.base.FunctionService;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.version.dao.ServerUrlVersionDao;
import com.yihu.jw.version.model.BaseServerUrlVersion;
import com.yihu.jw.version.model.BaseServerVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by chenweida on 2017/6/20.
 */
@Service
public class ServerUrlVersionService extends BaseJpaService<BaseServerUrlVersion, ServerUrlVersionDao> {
     @Autowired
    private ServerUrlVersionDao serverUrlVersionDao;
    @Autowired
    private ServerVersionService serverVersionService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public BaseServerUrlVersion createBaseServerUrlVersion(BaseServerUrlVersion baseServerVersion) throws ApiException {
        if (StringUtils.isEmpty(baseServerVersion.getCode())) {
            throw new ApiException(BaseVersionContants.BaseServerUrlVersion.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(baseServerVersion.getName())) {
            throw new ApiException(BaseVersionContants.BaseServerUrlVersion.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        BaseServerUrlVersion baseServerVersionTmp = serverUrlVersionDao.findByName(baseServerVersion.getName());
        if (baseServerVersionTmp != null) {
            throw new ApiException(BaseVersionContants.BaseServerUrlVersion.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return serverUrlVersionDao.save(baseServerVersion);
    }

    @Transactional
    public BaseServerUrlVersion updateBaseServerUrlVersion(BaseServerUrlVersion baseServerVersion) {
        if (StringUtils.isEmpty(baseServerVersion.getCode())) {
            throw new ApiException(BaseVersionContants.BaseServerUrlVersion.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(baseServerVersion.getName())) {
            throw new ApiException(BaseVersionContants.BaseServerUrlVersion.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(baseServerVersion.getId())) {
            throw new ApiException(BaseVersionContants.BaseServerUrlVersion.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        BaseServerUrlVersion baseServerVersionTmp = serverUrlVersionDao.findByNameExcludeCode(baseServerVersion.getName(), baseServerVersion.getCode());
        if (baseServerVersionTmp != null) {
            throw new ApiException(BaseVersionContants.BaseServerUrlVersion.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return serverUrlVersionDao.save(baseServerVersion);
    }

    public BaseServerUrlVersion findByCode(String code) {
        BaseServerUrlVersion baseServerVersion = serverUrlVersionDao.findByCode(code);
        if (baseServerVersion == null) {
            throw new ApiException(BaseVersionContants.BaseServerUrlVersion.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        String serverCode = baseServerVersion.getServerCode();
        BaseServerVersion serverVersion = serverVersionService.findByCode(serverCode);
        String functionCode = baseServerVersion.getFunctionCode();
        Function function = functionService.findByCode(functionCode);
        baseServerVersion.setFunctionName(function.getName());
        baseServerVersion.setServerName(serverVersion.getName());
        return baseServerVersion;
    }

    @Transactional
    public void deleteBaseServerUrlVersion(String codes) {
        if(!StringUtils.isEmpty(codes)){
            for(String code:codes.split(",")){
                BaseServerUrlVersion baseServerVersion = serverUrlVersionDao.findByCode(code);
                if (baseServerVersion == null) {
                    continue;
                }
                baseServerVersion.setStatus(-1);
                serverUrlVersionDao.save(baseServerVersion);
            }
        }
    }

    /**
     * 通过serverCode查找
     * @param serverCode
     * @return
     */
    public List<BaseServerUrlVersion> findByServer(String serverCode){
        return serverUrlVersionDao.findByServer(serverCode);
    }

    @Transactional
    public void deleteByServer(String serverCode, String userName, String userCode) {
        serverUrlVersionDao.deleteByServer(serverCode,userName,userCode);
    }
}
