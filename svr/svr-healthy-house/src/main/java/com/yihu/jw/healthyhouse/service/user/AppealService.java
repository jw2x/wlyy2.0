package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.healthyhouse.dao.user.AppealDao;
import com.yihu.jw.healthyhouse.model.user.Appeal;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账号申诉.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.26
 */
@Service
@Transactional
public class AppealService extends BaseJpaService<Appeal, AppealDao> {

    @Autowired
    private AppealDao AppealDao;

    public Appeal findById(String id) {
        return  AppealDao.findById(id);
    }

}
