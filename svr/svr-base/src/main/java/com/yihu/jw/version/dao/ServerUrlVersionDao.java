package com.yihu.jw.version.dao;

import com.yihu.jw.version.model.BaseServerUrlVersion;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface ServerUrlVersionDao extends PagingAndSortingRepository<BaseServerUrlVersion, Long>, JpaSpecificationExecutor<BaseServerUrlVersion> {
    @Query("from BaseServerUrlVersion f where f.name=?1 and f.status=1")
    BaseServerUrlVersion findByName(String name);

    @Query("from BaseServerUrlVersion f where f.name=?1 and f.status=1 and f.code != ?2")
    BaseServerUrlVersion findByNameExcludeCode(String name, String code);

    @Query("from BaseServerUrlVersion f where f.code=?1 and f.status=1")
    BaseServerUrlVersion findByCode(String code);

    @Query("from BaseServerUrlVersion f where f.serverCode=?1 and f.status=1")
    List<BaseServerUrlVersion> findByServer(String serverCode);

    @Transactional
    @Modifying
    @Query("update BaseServerUrlVersion b set b.status=-1 , b.updateUserName=?2 , b.updateUser =?3 where b.serverCode = ?1")
    void deleteByServer(String serverCode, String userName, String userCode);
}
