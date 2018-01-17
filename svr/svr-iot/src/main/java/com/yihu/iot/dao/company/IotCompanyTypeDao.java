package com.yihu.iot.dao.company;

import com.yihu.jw.iot.company.IotCompanyTypeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 企业类型表
 * @author yeshijie on 2018/1/16.
 */
public interface IotCompanyTypeDao extends PagingAndSortingRepository<IotCompanyTypeDO, String>,
        JpaSpecificationExecutor<IotCompanyTypeDO> {

    @Query("select s from IotCompanyTypeDO s where s.companyId = ?1 ")
    List<IotCompanyTypeDO> findByCompanyId(String companyId);
}
