package com.yihu.jw.healthyhouse.dao.user;

import com.yihu.jw.healthyhouse.model.user.NavigationServiceEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 服务评价dao
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
public interface NavigationServiceEvaluationDao extends JpaRepository<NavigationServiceEvaluation, Long> {

    NavigationServiceEvaluation findById(String id);
}

