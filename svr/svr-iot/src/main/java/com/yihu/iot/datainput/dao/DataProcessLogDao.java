package com.yihu.iot.datainput.dao;

import com.yihu.jw.iot.data_input.DataProcessLogDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DataProcessLogDao extends PagingAndSortingRepository<DataProcessLogDO, String>, JpaSpecificationExecutor<DataProcessLogDO> {

}
