package com.yihu.jw.business.base.dao;

import com.yihu.jw.base.base.SystemDictDO;
import com.yihu.jw.base.base.SystemDictListDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by LiTaohong on 2017/12/01.
 */
public interface SystemDictListDao extends PagingAndSortingRepository<SystemDictListDO, String>, JpaSpecificationExecutor<SystemDictListDO> {
}
