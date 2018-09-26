package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.healthyhouse.dao.user.NavigationServiceEvaluationDao;
import com.yihu.jw.healthyhouse.model.user.NavigationServiceEvaluation;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务评价.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
@Service
@Transactional
public class NavigationServiceEvaluationService extends BaseJpaService<NavigationServiceEvaluation, NavigationServiceEvaluationDao> {

    @Autowired
    private NavigationServiceEvaluationDao navigationServiceEvaluationDao;

    public NavigationServiceEvaluation findById(String id) {
        return  navigationServiceEvaluationDao.findById(id);
    }

}
