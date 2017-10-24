package com.yihu.jw.business.version.dao;// default package

import com.yihu.jw.business.version.model.WlyyVersion;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface WlyyVersionDao extends PagingAndSortingRepository<WlyyVersion, Long>, JpaSpecificationExecutor<WlyyVersion> {

    @Query("from WlyyVersion f where f.name=?1 and f.status=1")
    WlyyVersion findByName(String name);

    @Query("from WlyyVersion f where f.name=?1 and f.status=1 and f.code != ?2")
    WlyyVersion findByNameExcludeCode(String name, String code);

    @Query("from WlyyVersion f where f.code=?1 and f.status=1")
    WlyyVersion findByCode(String code);
}
