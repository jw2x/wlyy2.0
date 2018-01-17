package com.yihu.iot.dao.product;

import com.yihu.jw.iot.product.IotProductExtendInfoDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotProductExtendInfoDao extends PagingAndSortingRepository<IotProductExtendInfoDO,String>,
        JpaSpecificationExecutor<IotProductExtendInfoDO> {


}
