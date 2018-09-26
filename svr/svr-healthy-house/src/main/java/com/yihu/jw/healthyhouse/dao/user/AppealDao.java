package com.yihu.jw.healthyhouse.dao.user;

import com.yihu.jw.healthyhouse.model.user.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 账号申诉
 * @author zdm
 * @version 1.0
 * @created 2018.09.26
 */
public interface AppealDao extends JpaRepository<Appeal, Long> {

    Appeal findById(String id);
}

