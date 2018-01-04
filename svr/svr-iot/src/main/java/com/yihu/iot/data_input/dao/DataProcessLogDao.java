package com.yihu.iot.data_input.dao;

import com.yihu.jw.iot.data_input.DataProcessLogDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DataProcessLogDao extends PagingAndSortingRepository<DataProcessLogDO, Long>, JpaSpecificationExecutor<DataProcessLogDO> {

}
