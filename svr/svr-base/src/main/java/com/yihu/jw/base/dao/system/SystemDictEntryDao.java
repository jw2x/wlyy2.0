package com.yihu.jw.base.dao.system;

import com.yihu.jw.entity.base.system.SystemDictEntryDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dao - 系统字典项
 * Created by LiTaohong on 2017/12/01.
 */
public interface SystemDictEntryDao extends PagingAndSortingRepository<SystemDictEntryDO, String>, JpaSpecificationExecutor<SystemDictEntryDO> {

    List<SystemDictEntryDO> findBySaasId(String saasId);

    List<SystemDictEntryDO> findByDictCodeAndCodeAndSaasId(String dictCode, String code, String saasId);

    @Modifying
    @Transactional
    @Query("delete from SystemDictEntryDO  where dictCode=?1")
    void deleteByDictCode(String dictCode);
}
