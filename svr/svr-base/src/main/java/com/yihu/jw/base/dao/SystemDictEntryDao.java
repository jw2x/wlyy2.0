package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.system.SystemDictEntryDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by LiTaohong on 2017/12/01.
 */
public interface SystemDictEntryDao extends PagingAndSortingRepository<SystemDictEntryDO, Integer>, JpaSpecificationExecutor<SystemDictEntryDO> {
}
