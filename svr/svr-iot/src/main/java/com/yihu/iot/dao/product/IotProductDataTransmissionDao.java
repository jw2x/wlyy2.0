package com.yihu.iot.dao.product;

import com.yihu.jw.entity.iot.product.IotProductDataTransmissionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotProductDataTransmissionDao extends PagingAndSortingRepository<IotProductDataTransmissionDO,String>,
        JpaSpecificationExecutor<IotProductDataTransmissionDO> {

    @Query("from IotProductDataTransmissionDO w where w.productId =?1")
    List<IotProductDataTransmissionDO> findByProductId(String productId);
}
