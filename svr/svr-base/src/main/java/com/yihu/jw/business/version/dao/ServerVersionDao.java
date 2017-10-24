package com.yihu.jw.business.version.dao;// default package

import com.yihu.jw.business.version.model.BaseServerVersion;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface ServerVersionDao extends PagingAndSortingRepository<BaseServerVersion, Long>, JpaSpecificationExecutor<BaseServerVersion> {

    @Query("from BaseServerVersion f where f.name=?1 and f.status=1")
    BaseServerVersion findByName(String name);

    @Query("from BaseServerVersion f where f.name=?1 and f.status=1 and f.code != ?2")
    BaseServerVersion findByNameExcludeCode(String name, String code);

    @Query("from BaseServerVersion f where f.code=?1 and f.status=1")
    BaseServerVersion findByCode(String code);

    @Query("from BaseServerVersion v where v.status!=-1")
    List<BaseServerVersion> findAll();
}
