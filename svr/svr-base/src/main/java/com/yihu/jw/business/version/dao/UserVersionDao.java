package com.yihu.jw.business.version.dao;

import com.yihu.jw.base.version.BaseUserVersion;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/11/13.
 */
public interface UserVersionDao extends PagingAndSortingRepository<BaseUserVersion, String>, JpaSpecificationExecutor<BaseUserVersion> {
}
