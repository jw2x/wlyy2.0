package com.yihu.jw.base.dao.saas;

import com.yihu.jw.entity.base.saas.SaasDO;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - SAAS
 * Created by progr1mmer on 2018/8/14.
 */
public interface SaasDao extends PagingAndSortingRepository<SaasDO, String> {

    SaasDO findByName(String name);

    SaasDO findById(String id);

    SaasDO findByCreateUser(String createUser);
}
