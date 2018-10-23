package com.yihu.jw.base.dao.system;

import com.yihu.jw.entity.base.system.SystemDictEntryDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Dao - 系统字典项
 * Created by LiTaohong on 2017/12/01.
 */
public interface SystemDictEntryDao extends PagingAndSortingRepository<SystemDictEntryDO, String>, JpaSpecificationExecutor<SystemDictEntryDO> {

    List<SystemDictEntryDO> findBySaasId(String saasId);

    List<SystemDictEntryDO> findByDictCodeAndCodeAndSaasId(String dictCode,String code,String saasId);
}
