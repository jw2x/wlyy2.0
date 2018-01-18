package com.yihu.iot.dao.product;

import com.yihu.jw.iot.company.IotCompanyDO;
import com.yihu.jw.iot.product.IotProductAttachmentDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotProductAttachmentDao extends PagingAndSortingRepository<IotProductAttachmentDO,String>,
        JpaSpecificationExecutor<IotProductAttachmentDO> {

    @Query("from IotProductAttachmentDO w where w.productId =?1")
    List<IotProductAttachmentDO> findByProductId(String productId);
}
