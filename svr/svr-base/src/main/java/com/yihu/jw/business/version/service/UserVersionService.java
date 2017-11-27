package com.yihu.jw.business.version.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.base.version.BaseUserVersionDO;
import com.yihu.jw.business.sms.dao.SmsGatewayDao;
import com.yihu.jw.business.version.dao.UserVersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by chenweida on 2017/11/10.
 */
@Service
public class UserVersionService extends BaseJpaService<BaseUserVersionDO, UserVersionDao> {
    @Autowired
    private UserVersionDao userVersionDao;

    @Transactional
    public BaseUserVersionDO createUserVersion(BaseUserVersionDO baseUserVersion) {
        return userVersionDao.save(baseUserVersion);
    }

    @Transactional
    public void deleteUserVersion(String ids) {
        String[] idStr = ids.split(",");
        for (String id : idStr) {
            userVersionDao.delete(id);
        }
    }

    public BaseUserVersionDO getUserVersionByUserId(String userId) {
      return  userVersionDao.getUserVersionByUserId(userId);
    }

    public BaseUserVersionDO getUserVersion(String id) {
        return  userVersionDao.findOne(id);
    }
}
