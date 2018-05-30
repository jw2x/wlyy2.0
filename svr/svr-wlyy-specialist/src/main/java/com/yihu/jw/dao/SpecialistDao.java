package com.yihu.jw.dao;

import com.yihu.jw.entity.specialist.SpecialistDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/4/25.
 */
public interface SpecialistDao extends PagingAndSortingRepository<SpecialistDO, String>,
        JpaSpecificationExecutor<SpecialistDO> {
}
