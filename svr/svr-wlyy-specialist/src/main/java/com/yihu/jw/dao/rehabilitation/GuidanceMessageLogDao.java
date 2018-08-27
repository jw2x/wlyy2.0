package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.rehabilitation.GuidanceMessageLogDO;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by 刘文彬 on 2018/8/23.
 */
public interface GuidanceMessageLogDao extends PagingAndSortingRepository<GuidanceMessageLogDO, Long>,JpaSpecificationExecutor<GuidanceMessageLogDO> {

    List<GuidanceMessageLogDO> findByPlanDetailId(String planDetailId);
}
