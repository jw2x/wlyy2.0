package com.yihu.jw.base.service.system;

import com.yihu.jw.base.dao.system.SystemDictEntryDao;
import com.yihu.jw.entity.base.system.SystemDictEntryDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 系统字典项
 * Created by LiTaohong on 2017/12/01.
 */
@Service
public class SystemDictEntryService extends BaseJpaService<SystemDictEntryDO, SystemDictEntryDao> {

    @Autowired
    private SystemDictEntryDao systemDictEntryDao;

    public SystemDictEntryDO findById(String id) {
        return systemDictEntryDao.findOne(id);
    }

    public void deleteByDictCode(String dictCode) {
        systemDictEntryDao.deleteByDictCode(dictCode);
    }
}
