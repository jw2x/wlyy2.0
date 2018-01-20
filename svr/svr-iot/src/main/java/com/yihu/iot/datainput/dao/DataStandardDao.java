package com.yihu.iot.datainput.dao;

import com.yihu.jw.iot.datainput.DataStandardDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DataStandardDao extends PagingAndSortingRepository<DataStandardDO, String>, JpaSpecificationExecutor<DataStandardDO> {

    @Query("from DataStandardDO where baseName = ?1" )
    List<DataStandardDO>  getList(String baseName);
}
