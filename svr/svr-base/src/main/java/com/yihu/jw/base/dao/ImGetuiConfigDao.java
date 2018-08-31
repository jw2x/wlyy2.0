package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.im.ImGetuiConfigDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/8/29.
 */
public interface ImGetuiConfigDao extends PagingAndSortingRepository<ImGetuiConfigDO, String>, JpaSpecificationExecutor<ImGetuiConfigDO> {
}
