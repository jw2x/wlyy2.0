package com.yihu.jw.business.version.dao;// default package

import com.yihu.jw.business.version.model.BaseServerVersionLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface ServerVersionLogDao extends PagingAndSortingRepository<BaseServerVersionLog, Long>, JpaSpecificationExecutor<BaseServerVersionLog> {

    @Query("from BaseServerVersionLog f where f.name=?1 and f.status=1")
    BaseServerVersionLog findByName(String name);

    @Query("from BaseServerVersionLog f where f.id=?1 and f.status=1")
    BaseServerVersionLog findByCode(String code);
}
