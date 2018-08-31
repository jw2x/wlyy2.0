package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationPlanTemplateDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RehabilitationPlanTemplateDao extends PagingAndSortingRepository<RehabilitationPlanTemplateDO, Long>,JpaSpecificationExecutor<RehabilitationPlanTemplateDO> {

    @Query("select t from RehabilitationPlanTemplateDO t where t.adminTeamCode = ?1 and t.del = 1 ORDER BY t.createTime DESC ")
    List<RehabilitationPlanTemplateDO> findByAdminTeamCode(Long adminTeamCode);

    @Modifying
    @Query("update RehabilitationPlanTemplateDO t set t.del = 0 where t.id = ?1")
    void updateDelById(String id);
}
