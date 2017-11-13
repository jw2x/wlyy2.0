package com.yihu.jw.business.version.service;

import com.yihu.jw.base.version.BaseUserVersion;
import com.yihu.jw.business.version.dao.UserVersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenweida on 2017/11/10.
 */
@Service
public class UserVersionService {
    @Autowired
    private UserVersionDao userVersionDao;

    @Transactional
    public BaseUserVersion createUserVersion(BaseUserVersion baseUserVersion) {
        return userVersionDao.save(baseUserVersion);
    }

    @Transactional
    public void deleteUserVersion(String id) {
        userVersionDao.delete(id);
    }
}
