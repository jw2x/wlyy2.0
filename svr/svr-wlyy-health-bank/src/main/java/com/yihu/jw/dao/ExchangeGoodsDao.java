package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/5/3.
 */

import com.yihu.jw.entity.health.bank.ExchangeGoodsDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-05-03 15:28
 * @desc exchange goods dao
 **/
public interface ExchangeGoodsDao extends PagingAndSortingRepository<ExchangeGoodsDO,String>,JpaSpecificationExecutor<ExchangeGoodsDO> {
}
