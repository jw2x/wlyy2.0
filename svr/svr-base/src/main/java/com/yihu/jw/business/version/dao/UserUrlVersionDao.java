package com.yihu.jw.business.version.dao;// default package

import com.yihu.jw.business.version.model.BaseUserUrlVersion;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface UserUrlVersionDao extends PagingAndSortingRepository<BaseUserUrlVersion, Long>, JpaSpecificationExecutor<BaseUserUrlVersion> {


    @Query("from BaseUserUrlVersion f where f.code=?1 and f.status=1")
    BaseUserUrlVersion findByCode(String code);

    @Query("from BaseUserUrlVersion f where f.userCode=?1 and f.saasId=?2 and f.status=1")
    BaseUserUrlVersion findByBsvCodeAndSaasId(String userCode, String saasId);

}
