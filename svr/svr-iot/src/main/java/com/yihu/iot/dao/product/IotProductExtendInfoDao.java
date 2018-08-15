package com.yihu.iot.dao.product;

import com.yihu.jw.entity.iot.product.IotProductExtendInfoDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotProductExtendInfoDao extends PagingAndSortingRepository<IotProductExtendInfoDO,String>,
        JpaSpecificationExecutor<IotProductExtendInfoDO> {

    @Query("from IotProductExtendInfoDO w where w.productId =?1 and w.del=1")
    IotProductExtendInfoDO findByProductId(String productId);
}
