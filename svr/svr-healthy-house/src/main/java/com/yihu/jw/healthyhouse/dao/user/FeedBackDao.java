package com.yihu.jw.healthyhouse.dao.user;

import com.yihu.jw.healthyhouse.model.user.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 意见反馈dao
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
public interface FeedBackDao extends JpaRepository<FeedBack, Long> {

    FeedBack findById(String id);
}

