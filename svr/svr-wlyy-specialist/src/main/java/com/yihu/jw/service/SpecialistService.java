package com.yihu.jw.service;

import com.yihu.jw.dao.*;
import com.yihu.jw.entity.specialist.SpecialistArticleDO;
import com.yihu.jw.entity.specialist.SpecialistConsultDO;
import com.yihu.jw.entity.specialist.SpecialistDO;
import com.yihu.jw.entity.specialist.SpecialistPatientRelationDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.specialist.*;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by Trick on 2018/4/25.
 */
@Service
@Transactional
public class SpecialistService{

    @Autowired
    private SpecialistArticleDao specialistArticleDao;
    @Autowired
    private SpecialistConsultDao specialistConsultDao;
    @Autowired
    private SpecialistPatientRelationDao specialistPatientRelationDao;
    @Autowired
    private SpecialistDao specialistDao;
    @Autowired
    private PatientHospitalRecordDao patientHospitalRecordDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${basedb.name}")
    private String basedb;

    public Envelop<Boolean> createSpecialists(List<SpecialistDO> info){
        specialistDao.save(info);
        return Envelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public Envelop<Boolean> createSpecialistsPatientRelation(SpecialistPatientRelationDO specialistPatientRelationDO){
        specialistPatientRelationDao.save(specialistPatientRelationDO);
        return Envelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public Envelop<Boolean> createSpecialistArticle(SpecialistArticleDO articleDO){
        specialistArticleDao.save(articleDO);
        return Envelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public Envelop<Boolean> createSpscialistConsult(SpecialistConsultDO consultDO){
        specialistConsultDao.save(consultDO);
        return Envelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public Envelop<SpecialistPatientRelationVO> findSpecialistPatientRelation(String doctor,Integer page,Integer size)throws ParseException {
        String sql = "SELECT " +
                " r.id, " +
                " r.doctor, " +
                " r.doctor_name AS doctor_name, " +
                " r.saas_id AS sassId, " +
                " r.patient, " +
                " r.patient_name AS patient_name, " +
                " r.health_doctor AS healthDoctor, " +
                " r.health_doctor_name AS healthDoctorName, " +
                " r.sign_code AS signCode, " +
                " r.sign_year AS signYear, " +
                " r.sign_doctor AS sign_doctor, " +
                " r.sign_doctor_name AS signDoctorName, " +
                " r.create_time AS createTime," +
                " r.status " +
                "FROM " +
                " wlyy_specialist_patient_relation r " +
                "WHERE " +
                " r.doctor = '"+doctor+"' ORDER BY r.create_time DESC LIMIT "+(page-1)*size+","+size+";";
        List<SpecialistPatientRelationVO> specialistPatientRelationVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SpecialistPatientRelationVO.class));

        String sqlcount = "SELECT count(1) AS total " +
                "FROM " +
                " wlyy_specialist_patient_relation r " +
                "WHERE " +
                " r.doctor = '"+doctor+"';";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(SpecialistMapping.api_success,specialistPatientRelationVOs,page,size,count);
    }

    public Envelop<Long> findSpecialistPatientRelationCout(String doctor){
        String sql = "SELECT " +
                " count(1) AS total " +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " WHERE " +
                " r.patient " +
                " NOT IN ( " +
                " SELECT " +
                " patient " +
                " FROM " +
                " "+basedb+".wlyy_sign_patient_label_info i " +
                " WHERE " +
                " i.label_type = '5' " +
                " )";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccess(SpecialistMapping.api_success,count);
    }

//    public Envelop<PatientRelationVO>

}
