package com.yihu.jw.quota.dao.jpa.dict;

import com.yihu.jw.quota.model.jpa.dict.SystemDict;
import com.yihu.jw.quota.model.jpa.dimension.TjDimensionMain;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/1.
 */
public interface SystemDictDao extends PagingAndSortingRepository<SystemDict, Long>, JpaSpecificationExecutor<SystemDict> {
}
