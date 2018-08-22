package com.yihu.iot.dao.product;

import com.yihu.jw.entity.iot.product.IotProductMeasuredDataDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotProductMeasuredDataDao extends PagingAndSortingRepository<IotProductMeasuredDataDO,String>,
        JpaSpecificationExecutor<IotProductMeasuredDataDO> {

    @Query("from IotProductMeasuredDataDO w where w.productId =?1 and w.del=1")
    List<IotProductMeasuredDataDO> findByProductId(String productId);

}
