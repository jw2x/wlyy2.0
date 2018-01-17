package com.yihu.iot.dao.product;

import com.yihu.jw.iot.product.IotProductAttachmentDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotProductAttachmentDao extends PagingAndSortingRepository<IotProductAttachmentDO,String>,
        JpaSpecificationExecutor<IotProductAttachmentDO> {


}
