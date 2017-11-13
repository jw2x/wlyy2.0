package com.yihu.jw.business.version.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.base.sms.BaseSmsGateway;
import com.yihu.jw.base.version.BaseUserVersion;
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
public class UserVersionService extends BaseJpaService<BaseUserVersion, UserVersionDao> {
    @Autowired
    private UserVersionDao userVersionDao;

    @Transactional
    public BaseUserVersion createUserVersion(BaseUserVersion baseUserVersion) {
        return userVersionDao.save(baseUserVersion);
    }

    @Transactional
    public void deleteUserVersion(String ids) {
        String[] idStr = ids.split(",");
        for (String id : idStr) {
            userVersionDao.delete(id);
        }
    }

    public BaseUserVersion getUserVersionBySaasIdAndUserId(String saasId, String userId) {
      return  userVersionDao.findBySaasIdAndUserId(saasId,userId);
    }

    public BaseUserVersion getUserVersion(String id) {
        return  userVersionDao.findOne(id);
    }
}
