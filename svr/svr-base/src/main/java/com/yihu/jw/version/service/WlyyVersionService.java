package com.yihu.jw.version.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.base.version.MWlyyVersion;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.version.dao.ServerVersionDao;
import com.yihu.jw.version.dao.WlyyVersionDao;
import com.yihu.jw.version.model.WlyyVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class WlyyVersionService extends BaseJpaService<WlyyVersion, WlyyVersionDao> {
    @Autowired
    private WlyyVersionDao wlyyVersionDao;

    @Transactional
    public WlyyVersion createWlyyVersion(WlyyVersion wlyyVersion) throws ApiException {
        if (StringUtils.isEmpty(wlyyVersion.getName())) {
            throw new ApiException(BaseVersionContants.WlyyVersion.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        WlyyVersion wlyyVersionTmp = wlyyVersionDao.findByName(wlyyVersion.getName());
        if (wlyyVersionTmp != null) {
            throw new ApiException(BaseVersionContants.WlyyVersion.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return wlyyVersionDao.save(wlyyVersion);
    }

    @Transactional
    public WlyyVersion updateWlyyVersion(WlyyVersion WlyyVersion) {
        if (StringUtils.isEmpty(WlyyVersion.getCode())) {
            throw new ApiException(BaseVersionContants.WlyyVersion.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(WlyyVersion.getName())) {
            throw new ApiException(BaseVersionContants.WlyyVersion.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(WlyyVersion.getId())) {
            throw new ApiException(BaseVersionContants.WlyyVersion.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        WlyyVersion wlyyVersionTmp = wlyyVersionDao.findByNameExcludeCode(WlyyVersion.getName(), WlyyVersion.getCode());
        if (wlyyVersionTmp != null) {
            throw new ApiException(BaseVersionContants.WlyyVersion.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return wlyyVersionDao.save(WlyyVersion);
    }

    public WlyyVersion findByCode(String code) {
        WlyyVersion WlyyVersion = wlyyVersionDao.findByCode(code);
        if (WlyyVersion == null) {
            throw new ApiException(BaseVersionContants.WlyyVersion.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return WlyyVersion;
    }

    @Transactional
    public void deleteWlyyVersion(String code) {
        WlyyVersion WlyyVersion = wlyyVersionDao.findByCode(code);
        if (WlyyVersion == null) {
            throw new ApiException(BaseVersionContants.WlyyVersion.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        WlyyVersion.setStatus(-1);
    }
}
