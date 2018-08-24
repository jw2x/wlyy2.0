package com.yihu.iot.dao.product;

import com.yihu.jw.entity.iot.product.IotProductBaseInfoDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotProductBaseInfoDao extends PagingAndSortingRepository<IotProductBaseInfoDO,String>,
        JpaSpecificationExecutor<IotProductBaseInfoDO> {

    @Query("from IotProductBaseInfoDO w where w.id =?1")
    IotProductBaseInfoDO findById(String id);
}
