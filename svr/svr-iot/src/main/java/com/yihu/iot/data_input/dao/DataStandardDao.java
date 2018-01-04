package com.yihu.iot.data_input.dao;

import com.yihu.jw.iot.data_input.DataStandardDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DataStandardDao extends PagingAndSortingRepository<DataStandardDO, Long>, JpaSpecificationExecutor<DataStandardDO> {

    @Query("baseName,itemCode,itemName,itemType,required,itemValueMin,itemValueMax from DataStandardDO" )
    List<DataStandardDO>  getList();
}
