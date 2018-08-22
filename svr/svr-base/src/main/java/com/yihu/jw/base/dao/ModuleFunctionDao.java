package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.module.ModuleFunctionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface ModuleFunctionDao extends PagingAndSortingRepository<ModuleFunctionDO, Integer>, JpaSpecificationExecutor<ModuleFunctionDO> {

}
