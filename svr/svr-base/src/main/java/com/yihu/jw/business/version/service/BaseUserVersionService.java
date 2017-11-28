package com.yihu.jw.business.version.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.base.version.BaseUserVersionDO;
import com.yihu.jw.business.sms.dao.SmsGatewayDao;
import com.yihu.jw.business.version.dao.BaseUserVersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by chenweida on 2017/11/10.
 */
@Service
public class BaseUserVersionService extends BaseJpaService<BaseUserVersionDO, BaseUserVersionDao> {
    @Autowired
    private BaseUserVersionDao baseUserVersionDao;

    @Transactional
    public BaseUserVersionDO createUserVersion(BaseUserVersionDO baseUserVersion) {
        return baseUserVersionDao.save(baseUserVersion);
    }

    @Transactional
    public void deleteUserVersion(String ids) {
        String[] idStr = ids.split(",");
        for (String id : idStr) {
            baseUserVersionDao.delete(id);
        }
    }

    public BaseUserVersionDO getUserVersionByUserId(String userId) {
        return baseUserVersionDao.getUserVersionByUserId(userId);
    }

    public BaseUserVersionDO getUserVersion(String id) {
        return baseUserVersionDao.findOne(id);
    }
}
