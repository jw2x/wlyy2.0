package com.yihu.jw.business.version.service;

import com.yihu.jw.business.version.dao.WlyyVersionDao;
import com.yihu.jw.business.version.model.WlyyVersion;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class WlyyVersionService extends BaseJpaService<WlyyVersion, WlyyVersionDao> {
    @Autowired
    private WlyyVersionDao wlyyVersionDao;

    @Transactional
    public WlyyVersion createWlyyVersion(WlyyVersion wlyyVersion) throws ApiException {
        if (StringUtils.isEmpty(wlyyVersion.getCode())) {
            wlyyVersion.setCode(UUID.randomUUID().toString().replaceAll("-",""));
        }
        if (StringUtils.isEmpty(wlyyVersion.getName())) {
            throw new ApiException(BaseVersionRequestMapping.WlyyVersion.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        WlyyVersion wlyyVersionTmp = wlyyVersionDao.findByName(wlyyVersion.getName());
        if (wlyyVersionTmp != null) {
            throw new ApiException(BaseVersionRequestMapping.WlyyVersion.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return wlyyVersionDao.save(wlyyVersion);
    }

    @Transactional
    public WlyyVersion updateWlyyVersion(WlyyVersion wlyyVersion) {
        if (StringUtils.isEmpty(wlyyVersion.getCode())) {
            throw new ApiException(BaseVersionRequestMapping.WlyyVersion.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyVersion.getName())) {
            throw new ApiException(BaseVersionRequestMapping.WlyyVersion.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyVersion.getId())) {
            throw new ApiException(BaseVersionRequestMapping.WlyyVersion.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        WlyyVersion wlyyVersionTmp = wlyyVersionDao.findByNameExcludeCode(wlyyVersion.getName(), wlyyVersion.getCode());
        if (wlyyVersionTmp != null) {
            throw new ApiException(BaseVersionRequestMapping.WlyyVersion.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return wlyyVersionDao.save(wlyyVersion);
    }

    public WlyyVersion findByCode(String code) {
        WlyyVersion WlyyVersion = wlyyVersionDao.findByCode(code);
        if (WlyyVersion == null) {
            throw new ApiException(BaseVersionRequestMapping.WlyyVersion.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        }
        return WlyyVersion;
    }

    @Transactional
    public void deleteWlyyVersion(String codes, String userCode, String userName) {
        if(!StringUtils.isEmpty(codes)){
            for (String code: codes.split(",")){
                WlyyVersion wlyyVersion = wlyyVersionDao.findByCode(code);
                if (wlyyVersion == null) {
                    continue;
                }
                wlyyVersion.setStatus(-1);
                wlyyVersion.setUpdateUserName(userName);
                wlyyVersion.setUpdateUser(userCode);
                wlyyVersionDao.save(wlyyVersion);
            }
        }
    }
}
