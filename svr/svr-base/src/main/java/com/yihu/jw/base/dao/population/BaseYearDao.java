package com.yihu.jw.base.dao.population;

import com.yihu.jw.entity.base.population.BaseYearDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by zdm on 2018/10/12.
 */
public interface BaseYearDao extends PagingAndSortingRepository<BaseYearDO, String>, JpaSpecificationExecutor<BaseYearDO>  {
    @Query("select year from BaseYearDO ")
    List<String> findYears();
}
