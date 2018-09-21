package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.healthyhouse.dao.user.FeedBackDao;
import com.yihu.jw.healthyhouse.model.user.FeedBack;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 意见反馈.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
@Service
@Transactional
public class FeedBackService extends BaseJpaService<FeedBack, FeedBackDao> {

    @Autowired
    private FeedBackDao feedBackDao;

    public FeedBack findById(String id) {
        return  feedBackDao.findById(id);
    }

}
