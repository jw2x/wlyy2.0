package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationDetailDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘文彬 on 2018/8/20.
 */
public interface RehabilitationDetailDao extends PagingAndSortingRepository<RehabilitationDetailDO, Long>,JpaSpecificationExecutor<RehabilitationDetailDO> {

    @Query("select count(1) from RehabilitationDetailDO where status=?1 and planId=?2 ")
    Integer findByStatusAndPlanId(Integer status,String planId);

    @Query("select count(1) from RehabilitationDetailDO where planId=?1 ")
    Integer findAllByPlanId(String planId);

    @Query("select count(1) from RehabilitationDetailDO where planId=?1 and executeTime>=?2 and executeTime<=?3 ")
    Integer todayBacklogCount(String programId,Date executeStartTime,Date executeEndTime);

    @Query("select count(1) from RehabilitationDetailDO where status =?1 and planId=?2 and executeTime>=?3 and executeTime<=?4 ")
    Integer completenessCount(Integer status,String planId,Date executeStartTime,Date executeEndTime);

    @Query("select d from RehabilitationDetailDO d where d.planId=?1 order by d.executeTime desc")
    List<RehabilitationDetailDO> getAllRehabilitationDetail(String programId);

    @Query("select d from RehabilitationDetailDO d where d.executeTime<=?1 and d.executeTime>=?2 and d.planId=?3")
    List<RehabilitationDetailDO> findByPlanId(Date executeStartTime,Date executeEndTime,String planId);

    @Query(value ="select count(DISTINCT d.plan_id,d.hospital_service_item_id) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.doctor=?1 and p.patient=?2  and d.status!=?3",nativeQuery = true)
    Integer unfinishItemByDoctor(String doctor,String patient,Integer status);

    @Query(value ="select count(DISTINCT d.plan_id,d.hospital_service_item_id) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.doctor in (?1,?2) and p.patient=?3  and d.status!=?4",nativeQuery = true)
    Integer unfinishItemByDoctor(String generalDoctor,String healthDoctor,String patient,Integer status);

    @Query(value ="select count(DISTINCT d.plan_id,d.hospital_service_item_id) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.doctor=?1 and p.patient=?2",nativeQuery = true)
    Integer findItemByDoctor(String doctor,String patient);

    @Query(value ="select count(DISTINCT d.plan_id,d.hospital_service_item_id) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.doctor in (?1,?2) and p.patient=?3",nativeQuery = true)
    Integer findItemByDoctor(String generalDoctor,String healthDoctor,String patient);

//    @Query("select count(distinct serviceItemId) from RehabilitationDetailDO d left join PatientRehabilitationPlanDO p on d.planId=p.id where doctor=?1 and p.patient=?2 ")
//    Integer finishItemByDoctor(String doctor,String patient);
//
    @Query(value ="select count(1) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where doctor=?1 and p.patient=?2  and d.status=?3",nativeQuery = true)
    Integer completeServiceByDoctor(String doctor,String patient,Integer status);

    @Query(value ="select count(1) from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where doctor in (?1,?2) and p.patient=?3  and d.status=?4",nativeQuery = true)
    Integer completeServiceByDoctor(String generalDoctor,String healthDoctor,String patient,Integer status);

    RehabilitationDetailDO findById(String planDetailId);

    @Query(value ="select d.doctor,p.patient,count(1) as num from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.status!=1 and d.execute_time>=?1 and d.execute_time<=?2 GROUP BY d.doctor,p.patient",nativeQuery = true)
    List<Map> dailyJob(String startTime,String endTime);

    @Query(value = "select d.id from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.status!=1 and d.execute_time>=?1 and d.execute_time<=?2 and d.doctor=?3 and p.patient=?4",nativeQuery = true)
    List<String> findByPatientAndDoctor(String startTime,String endTime,String doctorCode,String patientCode);

    @Modifying
    @Query("update RehabilitationDetailDO t set t.status = ?1 where t.id=?2 ")
    int updateStatusById(Integer status,String id);

    @Query(value ="select p.patient from wlyy_rehabilitation_plan_detail d left join wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where d.id=?1",nativeQuery = true)
    List<String> findPatientById(String planDetailId);
}
