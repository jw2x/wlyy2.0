package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.saas.SaasDO;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface SaasDao extends PagingAndSortingRepository<SaasDO, String> {

    SaasDO findByName(String name);

    SaasDO findById(String id);
}
