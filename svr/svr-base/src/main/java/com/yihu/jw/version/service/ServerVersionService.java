package com.yihu.jw.version.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.base.version.MBaseServerVersion;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.version.dao.ServerVersionDao;
import com.yihu.jw.version.model.BaseServerVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ServerVersionService extends BaseJpaService<BaseServerVersion, ServerVersionDao> {
    @Autowired
    private ServerVersionDao BaseServerVersionDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public BaseServerVersion createBaseServerVersion(BaseServerVersion BaseServerVersion) throws ApiException {
        if (StringUtils.isEmpty(BaseServerVersion.getCode())) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(BaseServerVersion.getName())) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        BaseServerVersion BaseServerVersionTmp = BaseServerVersionDao.findByName(BaseServerVersion.getName());
        if (BaseServerVersionTmp != null) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return BaseServerVersionDao.save(BaseServerVersion);
    }

    @Transactional
    public BaseServerVersion updateBaseServerVersion(BaseServerVersion BaseServerVersion) {
        if (StringUtils.isEmpty(BaseServerVersion.getCode())) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(BaseServerVersion.getName())) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(BaseServerVersion.getId())) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        BaseServerVersion BaseServerVersionTmp = BaseServerVersionDao.findByNameExcludeCode(BaseServerVersion.getName(), BaseServerVersion.getCode());
        if (BaseServerVersionTmp != null) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return BaseServerVersionDao.save(BaseServerVersion);
    }

    public BaseServerVersion findByCode(String code) {
        BaseServerVersion BaseServerVersion = BaseServerVersionDao.findByCode(code);
        if (BaseServerVersion == null) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return BaseServerVersion;
    }

    @Transactional
    public void deleteBaseServerVersion(String code) {
        BaseServerVersion BaseServerVersion = BaseServerVersionDao.findByCode(code);
        if (BaseServerVersion == null) {
            throw new ApiException(BaseVersionContants.BaseServerVersion.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        BaseServerVersion.setStatus(-1);
    }

    public List<MBaseServerVersion> getModuleBaseServerVersions(String saasCode) {
        String sql=" select m.code,m.parent_code,m.name from base_BaseServerVersion f,base_module_BaseServerVersion mf where f.code=mf.BaseServerVersion_id and f.status=1 and mf.module_id=?";
        return jdbcTemplate.queryForList(sql,MBaseServerVersion.class,saasCode);
    }

}
