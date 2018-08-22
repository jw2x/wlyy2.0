package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 刘文彬 on 2018/8/16.
 */
public interface PatientRehabilitationPlanDao extends PagingAndSortingRepository<PatientRehabilitationPlanDO, Long>,JpaSpecificationExecutor<PatientRehabilitationPlanDO> {


}
