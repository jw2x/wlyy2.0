package com.yihu.jw.dao;

import com.yihu.jw.entity.specialist.SpecialistPatientRelationDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/4/25.
 */
public interface SpecialistPatientRelationDao extends PagingAndSortingRepository<SpecialistPatientRelationDO, String>,
        JpaSpecificationExecutor<SpecialistPatientRelationDO> {
}
