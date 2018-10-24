package com.yihu.jw.base.dao.system;

import com.yihu.jw.entity.base.system.SystemDictDO;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Dao - 系统字典
 * Created by LiTaohong on 2017/12/01.
 */
public interface SystemDictDao extends PagingAndSortingRepository<SystemDictDO, String>, JpaSpecificationExecutor<SystemDictDO> {

    List<SystemDictDO> findBySaasId(String saasId);

    @Query("select code as code,name as name from SystemDictDO where saasId = :saasId")
    List<Map<String, Object>> findCodeAndNameBySaasId(@Param("saasId") String saasId, Pageable pageable);

    @Query("select code as code,name as name from SystemDictDO")
    List<Map<String, Object>> findCodeAndName(Pageable pageable);

    @Modifying
    @Query("delete from SystemDictDO  where code=?1")
    void deleteByCode(String code);
}
