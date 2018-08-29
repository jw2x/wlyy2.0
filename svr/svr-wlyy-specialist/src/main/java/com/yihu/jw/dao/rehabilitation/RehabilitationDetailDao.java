package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationDetailDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by 刘文彬 on 2018/8/20.
 */
public interface RehabilitationDetailDao extends PagingAndSortingRepository<RehabilitationDetailDO, Long>,JpaSpecificationExecutor<RehabilitationDetailDO> {

    @Query("select count(1) from RehabilitationDetailDO where status !=?1 and planId=?2 and executeTime>=?3 and executeTime<=?4 ")
    Integer todayBacklogCount(Integer status,String programId,Date executeStartTime,Date executeEndTime);

    @Query("select count(1) from RehabilitationDetailDO where status =?1 and planId=?2 ")
    Integer completenessCount(Integer status,String programId);

    @Query("select d from RehabilitationDetailDO d where d.planId=?1 order by d.executeTime desc")
    List<RehabilitationDetailDO> getAllRehabilitationDetail(String programId);

    @Query("select d from RehabilitationDetailDO d where d.executeTime<=?1 and d.executeTime>=?2 and d.planId=?3")
    List<RehabilitationDetailDO> findByPlanId(Date executeStartTime,Date executeEndTime,String planId);

    @Query(value ="select count(distinct d.hospital_service_item_id) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.doctor=?1 and p.patient=?2  and d.status!=?3",nativeQuery = true)
    Integer unfinishItemByDoctor(String doctor,String patient,Integer status);

    @Query(value ="select count(distinct d.hospital_service_item_id) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.doctor in (?1,?2) and p.patient=?3  and d.status!=?4",nativeQuery = true)
    Integer unfinishItemByDoctor(String generalDoctor,String healthDoctor,String patient,Integer status);

    @Query(value ="select count(distinct d.hospital_service_item_id) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.doctor=?1 and p.patient=?2",nativeQuery = true)
    Integer findItemByDoctor(String doctor,String patient);

    @Query(value ="select count(distinct d.hospital_service_item_id) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.doctor in (?1,?2) and p.patient=?3",nativeQuery = true)
    Integer findItemByDoctor(String generalDoctor,String healthDoctor,String patient);

//    @Query("select count(distinct serviceItemId) from RehabilitationDetailDO d left join PatientRehabilitationPlanDO p on d.planId=p.id where doctor=?1 and p.patient=?2 ")
//    Integer finishItemByDoctor(String doctor,String patient);
//
    @Query(value ="select count(1) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where doctor=?1 and p.patient=?2  and d.status=?3",nativeQuery = true)
    Integer completeServiceByDoctor(String doctor,String patient,Integer status);

    @Query(value ="select count(1) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where doctor in (?1,?2) and p.patient=?3  and d.status=?4",nativeQuery = true)
    Integer completeServiceByDoctor(String generalDoctor,String healthDoctor,String patient,Integer status);
}
