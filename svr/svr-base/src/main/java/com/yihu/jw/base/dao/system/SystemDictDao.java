package com.yihu.jw.base.dao.system;

import com.yihu.jw.entity.base.system.SystemDictDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Dao - 系统字典
 * Created by LiTaohong on 2017/12/01.
 */
public interface SystemDictDao extends PagingAndSortingRepository<SystemDictDO, String>, JpaSpecificationExecutor<SystemDictDO> {

    List<SystemDictDO> findBySaasId(String saasId);
}
