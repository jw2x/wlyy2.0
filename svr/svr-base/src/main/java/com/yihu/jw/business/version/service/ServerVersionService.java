package com.yihu.jw.business.version.service;

import com.yihu.jw.business.version.dao.ServerVersionDao;
import com.yihu.jw.business.version.model.BaseServerVersion;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.version.MBaseServerVersion;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ServerVersionService extends BaseJpaService<BaseServerVersion, ServerVersionDao> {
    @Autowired
    private ServerVersionDao baseServerVersionDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ServerUrlVersionService serverUrlVersionService;

    @Transactional
    public BaseServerVersion createBaseServerVersion(BaseServerVersion baseServerVersion) throws ApiException {
        if (StringUtils.isEmpty(baseServerVersion.getCode())) {
            throw new ApiException(BaseVersionRequestMapping.BaseServerVersion.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(baseServerVersion.getName())) {
            throw new ApiException(BaseVersionRequestMapping.BaseServerVersion.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        BaseServerVersion BaseServerVersionTmp = baseServerVersionDao.findByName(baseServerVersion.getName());
        if (BaseServerVersionTmp != null) {
            throw new ApiException(BaseVersionRequestMapping.BaseServerVersion.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return baseServerVersionDao.save(baseServerVersion);
    }

    @Transactional
    public BaseServerVersion updateBaseServerVersion(BaseServerVersion BaseServerVersion) {
        if (StringUtils.isEmpty(BaseServerVersion.getCode())) {
            throw new ApiException(BaseVersionRequestMapping.BaseServerVersion.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(BaseServerVersion.getName())) {
            throw new ApiException(BaseVersionRequestMapping.BaseServerVersion.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(BaseServerVersion.getId())) {
            throw new ApiException(BaseVersionRequestMapping.BaseServerVersion.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        BaseServerVersion BaseServerVersionTmp = baseServerVersionDao.findByNameExcludeCode(BaseServerVersion.getName(), BaseServerVersion.getCode());
        if (BaseServerVersionTmp != null) {
            throw new ApiException(BaseVersionRequestMapping.BaseServerVersion.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return baseServerVersionDao.save(BaseServerVersion);
    }

    public BaseServerVersion findByCode(String code) {
        BaseServerVersion baseServerVersion = baseServerVersionDao.findByCode(code);
        if (baseServerVersion == null) {
            throw new ApiException(BaseVersionRequestMapping.BaseServerVersion.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        }
        return baseServerVersion;
    }

    @Transactional
    public void deleteBaseServerVersion(String codes,String userCode,String userName) {
        if(StringUtils.isEmpty(codes)){
            throw new ApiException(BaseVersionRequestMapping.BaseServerVersion.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        for(String code:codes.split(",")){
            BaseServerVersion baseServerVersion = baseServerVersionDao.findByCode(code);
            if (baseServerVersion == null) {
                continue;
            }
            baseServerVersion.setStatus(-1);
            baseServerVersion.setUpdateUserName(userName);
            baseServerVersion.setUpdateUser(userCode);
            baseServerVersionDao.save(baseServerVersion);

            //同时删除后台url版本
            serverUrlVersionService.deleteByServer(code, userName,userCode);

        }
    }

    public List<MBaseServerVersion> getModuleBaseServerVersions(String saasCode) {
        String sql=" select m.code,m.parent_code,m.name from base_BaseServerVersion f,base_module_BaseServerVersion mf where f.code=mf.BaseServerVersion_id and f.status=1 and mf.module_id=?";
        return jdbcTemplate.queryForList(sql,MBaseServerVersion.class,saasCode);
    }

    public List<BaseServerVersion> findAll(){
        return baseServerVersionDao.findAll();
    }

    /**
     * key为code ,value为版本名字
     * @return
     */
    public Map<String,String> getName(){
        List<BaseServerVersion> baseServerVersion = findAll();
        Map<String, String> map = new HashMap<>();
        if(null!=baseServerVersion){
            for(BaseServerVersion version: baseServerVersion){
                map.put(version.getCode(),version.getName());
            }
        }
        return map;
    }
}
