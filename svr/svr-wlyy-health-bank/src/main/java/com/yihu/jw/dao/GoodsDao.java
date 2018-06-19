package com.yihu.jw.dao;

import com.yihu.jw.entity.health.bank.GoodsDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by wang zhinan  on 2018/4/27.
 */
public interface GoodsDao extends PagingAndSortingRepository<GoodsDO,String>,JpaSpecificationExecutor<GoodsDO> {
}
