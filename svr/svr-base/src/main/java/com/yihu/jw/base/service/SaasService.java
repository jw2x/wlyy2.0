package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.UserDao;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.base.dao.SaasDao;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class SaasService extends BaseJpaService<SaasDO, SaasDao> {

    @Autowired
    private SaasDao saasDao;
    @Autowired
    private UserDao userDao;

    public SaasDO save(SaasDO saas, UserDO user) {
        user = userDao.save(user);
        saas.setManager(user.getId());
        return super.save(saas);
    }

    @Transactional
    public void delete(String ids) {
        for (String id : ids.split(",")) {
            SaasDO saas = saasDao.findById(id);
            saas.setStatus(SaasDO.Status.delete);
        }
    }

}
