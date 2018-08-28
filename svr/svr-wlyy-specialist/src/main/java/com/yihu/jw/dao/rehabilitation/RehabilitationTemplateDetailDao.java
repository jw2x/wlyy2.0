package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationTemplateDetailDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RehabilitationTemplateDetailDao extends PagingAndSortingRepository<RehabilitationTemplateDetailDO, Long>,JpaSpecificationExecutor<RehabilitationTemplateDetailDO> {

    List<RehabilitationTemplateDetailDO> findTemplateDetailByTemplateId(String templateId);

    void deleteByTemplateId(String templateId);

    @Query("SELECT r.hospitalServiceItemId FROM RehabilitationTemplateDetailDO r where r.templateId = ?1")
    List<String> findHospitalServiceItemIdByTemplateId(String templateId);
}
