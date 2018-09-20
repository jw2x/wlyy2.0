package com.yihu.jw.healthyhouse.dao.dict;

import com.yihu.jw.healthyhouse.model.dict.SystemDict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author zdm
 * @version 1.0
 * @created 2018.09.18
 */
public interface SystemDictDao extends JpaRepository<SystemDict, Long> {

    SystemDict findByName(String name);

    SystemDict findByPhoneticCode(String phoneticCode);
    SystemDict findById(String id);
}

