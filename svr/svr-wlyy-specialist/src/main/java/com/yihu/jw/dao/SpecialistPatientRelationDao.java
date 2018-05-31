package com.yihu.jw.dao;

import com.yihu.jw.entity.specialist.SpecialistPatientRelationDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Trick on 2018/4/25.
 */
public interface SpecialistPatientRelationDao extends PagingAndSortingRepository<SpecialistPatientRelationDO, String>,
        JpaSpecificationExecutor<SpecialistPatientRelationDO> {
    public List<SpecialistPatientRelationDO> findByDoctorAndStatus(String doctor,String status);
}
