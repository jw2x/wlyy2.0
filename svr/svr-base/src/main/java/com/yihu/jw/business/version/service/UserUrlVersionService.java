package com.yihu.jw.business.version.service;

import com.yihu.jw.business.version.dao.ServerVersionLogDao;
import com.yihu.jw.business.version.dao.UserUrlVersionDao;
import com.yihu.jw.business.version.model.BaseServerVersion;
import com.yihu.jw.business.version.model.BaseServerVersionLog;
import com.yihu.jw.business.version.model.BaseUserUrlVersion;
import com.yihu.jw.mysql.query.BaseJpaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by chenweida on 2017/6/20.
 */
@Service
public class UserUrlVersionService extends BaseJpaService<BaseUserUrlVersion, UserUrlVersionDao> {
    @Autowired
    private UserUrlVersionDao userUrlVersionDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ServerVersionLogDao serverVersionLogDao;
    @Autowired
    private ServerVersionService serverVersionService;

    /**
     * 更改后台用户版本
     *
     * @param serverCode       (bsvCode)
     * @param userCodes        (通过,分隔的userCode)
     * @param userCode         //创建/修改人
     * @param userName//创建/修改人
     * @param saasId
     * @return
     */
    @Transactional
    public void changeUserVersion(String serverCode, String userCodes, String userCode, String userName, String saasId) {
        //通过serverCode,查找该版本信息
        BaseServerVersion baseServerVersionbyCode = serverVersionService.findByCode(serverCode);


        String[] user_codes = userCodes.split(",");
        List<String> userList = new ArrayList<String>();//现有版本的用户
        Collections.addAll(userList, user_codes);

        List<String> oldUserCodes = findByBsvCode(serverCode, saasId);//原有版本的用户

        List<String> _oldUserCodes = new ArrayList<String>(oldUserCodes);//构建该版本原有的用户  oldUserCodes
        _oldUserCodes.removeAll(userList);// 去除相同元素--->>需要删除的用户

        List<String> _newUserCodes = new ArrayList<String>(userList);//构建userList的副本
        _newUserCodes.removeAll(oldUserCodes);// 去除相同元素---->>需要添加 的权限

        //先删除 在添加...
        for (String delCode : _oldUserCodes) {
            BaseUserUrlVersion userUrlVersion = findByUserCodeAndSaasId(delCode, saasId);
            userUrlVersion.setUpdateUser(userCode);
            userUrlVersion.setUpdateUserName(userName);
            userUrlVersion.setStatus(-1);
            userUrlVersionDao.save(userUrlVersion);
        }


        String deleteUserCodes = StringUtils.join(_oldUserCodes.toArray(), ",");
        //不为空,记录日志
        if (StringUtils.isNotBlank(deleteUserCodes)) {
            BaseServerVersionLog serverVersionLog = new BaseServerVersionLog();
            serverVersionLog.setSaasId(saasId);
            serverVersionLog.setUserCodes(deleteUserCodes);
            serverVersionLog.setBsvCode(serverCode);
            serverVersionLog.setName(baseServerVersionbyCode.getName());
            serverVersionLog.setVersionInt(baseServerVersionbyCode.getVersionInt());
            serverVersionLog.setType(2);
            serverVersionLog.setStatus(1);
            serverVersionLog.setCreateUser(userCode);
            serverVersionLog.setCreateUserName(userName);
            serverVersionLog.setUpdateUser(userCode);
            serverVersionLog.setUpdateUserName(userName);
            serverVersionLog.setCode(UUID.randomUUID().toString().replaceAll("-", ""));
            serverVersionLogDao.save(serverVersionLog);
        }


        for (String usercode : _newUserCodes) {
            //同一个saasId下,usercode只能关联一个bsvCode(serverCode)
            BaseUserUrlVersion baseUserUrlVersion = findByUserCodeAndSaasId(usercode, saasId);
            if (baseUserUrlVersion != null) {
                //存在
                if (baseUserUrlVersion.getBsvCode().equals(serverCode)) {
                    continue;
                }
                baseUserUrlVersion.setStatus(-1);
                baseUserUrlVersion.setUpdateUser(userCode);
                baseUserUrlVersion.setUpdateUserName(userName);
                userUrlVersionDao.save(baseUserUrlVersion);
            }
            BaseUserUrlVersion userUrlVersion = new BaseUserUrlVersion();
            userUrlVersion.setCode(UUID.randomUUID().toString().replaceAll("-", ""));
            userUrlVersion.setSaasId(saasId);
            userUrlVersion.setBsvCode(serverCode);
            userUrlVersion.setUserCode(usercode);
            userUrlVersion.setStatus(1);
            userUrlVersion.setCreateUser(userCode);
            userUrlVersion.setCreateUserName(userName);
            userUrlVersion.setUpdateUser(userCode);
            userUrlVersion.setUpdateUserName(userName);
            userUrlVersionDao.save(userUrlVersion);
        }

        String updateUserCodes = StringUtils.join(_newUserCodes.toArray(), ",");
        //不为空,记录日志
        if (StringUtils.isNotBlank(updateUserCodes)) {
            BaseServerVersionLog serverVersionLog = new BaseServerVersionLog();
            serverVersionLog.setSaasId(saasId);
            serverVersionLog.setUserCodes(updateUserCodes);
            serverVersionLog.setBsvCode(serverCode);
            serverVersionLog.setName(baseServerVersionbyCode.getName());
            serverVersionLog.setVersionInt(baseServerVersionbyCode.getVersionInt());
            serverVersionLog.setType(1);
            serverVersionLog.setStatus(1);
            serverVersionLog.setCreateUser(userCode);
            serverVersionLog.setCreateUserName(userName);
            serverVersionLog.setUpdateUser(userCode);
            serverVersionLog.setUpdateUserName(userName);
            serverVersionLog.setCode(UUID.randomUUID().toString().replaceAll("-", ""));
            serverVersionLogDao.save(serverVersionLog);
        }
    }


    /**
     * 通过bsvCode,saasId查找已存在的userCode
     *
     * @param bsvCode
     * @param saasId
     * @return
     */
    public List<String> findByBsvCode(String bsvCode, String saasId) {
        String sql = "select v.user_code from base_user_url_version v where v.status=1 and v.bsv_code=? and v.saas_id = ?";
        Object[] objs = new Object[2];
        objs[0] = bsvCode;
        objs[1] = saasId;
        List<String> userCodes = jdbcTemplate.queryForList(sql, objs, String.class);
        return userCodes;
    }

    /**
     * 通过两个参数,判断是否存在BaseUserUrlVersion,存在返回 BaseUserUrlVersion对象
     * 不存在,返回null
     *
     * @param userCode
     * @param saasId
     * @return
     */
    public BaseUserUrlVersion findByUserCodeAndSaasId(String userCode, String saasId) {
        return userUrlVersionDao.findByBsvCodeAndSaasId(userCode, saasId);
    }

}
