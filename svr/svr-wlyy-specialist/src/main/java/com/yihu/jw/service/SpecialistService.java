package com.yihu.jw.service;

import com.yihu.jw.dao.*;
import com.yihu.jw.entity.specialist.*;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.specialist.*;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Trick on 2018/4/25.
 */
@Service
@Transactional
public class SpecialistService{

//    @Autowired
//    private SpecialistArticleDao specialistArticleDao;
//    @Autowired
//    private SpecialistConsultDao specialistConsultDao;
//    @Autowired
//    private SpecialistDao specialistDao;
    @Autowired
    private SpecialistPatientRelationDao specialistPatientRelationDao;
    @Autowired
    private PatientHospitalRecordDao patientHospitalRecordDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${basedb.name}")
    private String basedb;

    public MixEnvelop<Boolean, Boolean> createSpecialistsPatientRelation(SpecialistPatientRelationDO specialistPatientRelationDO){
        specialistPatientRelationDao.save(specialistPatientRelationDO);
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public MixEnvelop<SpecialistPatientRelationVO, SpecialistPatientRelationVO> findSpecialistPatientRelation(String doctor, Integer page, Integer size)throws ParseException {
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
                " r.doctor = '"+doctor+"' AND r.status >=0  AND r.sign_status >0 ORDER BY r.create_time DESC LIMIT "+(page-1)*size+","+size;
        List<SpecialistPatientRelationVO> specialistPatientRelationVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SpecialistPatientRelationVO.class));

        String sqlcount = "SELECT count(1) AS total " +
                "FROM " +
                " wlyy_specialist_patient_relation r " +
                "WHERE " +
                " r.doctor = '"+doctor+"'  AND r.status >=0  AND r.sign_status >0 ;";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(SpecialistMapping.api_success,specialistPatientRelationVOs,page,size,count);
    }

    public MixEnvelop<Long, Long> findSpecialistPatientRelationCout(String doctor){
        String sql = "SELECT " +
                " count(1) AS total " +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " WHERE " +
                " r.doctor ='"+doctor+"'" +
                " AND r.patient " +
                " NOT IN ( " +
                " SELECT " +
                " patient " +
                " FROM " +
                " "+basedb+".wlyy_sign_patient_label_info i " +
                " WHERE " +
                " i.label_type = '7' AND " +
                " i.status = 1" +
                " )" +
                " AND r. STATUS >= 0 " +
                " AND r.sign_status > 0";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,count);
    }

    public MixEnvelop<PatientRelationVO, PatientRelationVO> findNoLabelPatientRelation(String doctor){
        String sql ="SELECT " +
                " r.patient, " +
                " r.patient_name AS patientName, " +
                " IFNULL(year( from_days( datediff( now(), p.birthday))),'未知') age, " +
                " p.photo, " +
                " rd.create_time AS createTime ," +
                " p.sex " +
                " FROM " +
                " wlyy_specialist_patient_relation r JOIN "+basedb+".wlyy_patient p ON p.code = r.patient  " +
                " LEFT JOIN wlyy_patient_hospital_record rd ON r.discharge_record = rd.id " +
                " WHERE " +
                " r.doctor = '"+doctor+"' AND r.status >=0  AND r.sign_status >0" +
                " AND r.patient " +
                " NOT IN ( " +
                "  SELECT " +
                "   i.patient " +
                "  FROM " +
                "   "+basedb+".wlyy_sign_patient_label_info i " +
                "  WHERE " +
                "   i.label_type = '7' AND " +
                "   i.status = 1 " +
                " )";
        List<PatientRelationVO> patientRelationVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(PatientRelationVO.class));

        return MixEnvelop.getSuccess(SpecialistMapping.api_success,patientRelationVOs);
    }

    public MixEnvelop<Boolean, Boolean> saveHealthAssistant(List<SpecialistPatientRelationDO> specialistPatientRelationDOs){
        for(SpecialistPatientRelationDO r : specialistPatientRelationDOs){
            SpecialistPatientRelationDO relationDO = specialistPatientRelationDao.findByDoctorAndPatient(r.getDoctor(),r.getPatient());
            if(relationDO!=null){
                relationDO.setHealthAssistant(r.getHealthAssistant());
                relationDO.setHealthAssistantName(r.getHealthAssistantName());
                specialistPatientRelationDao.save(relationDO);
            }
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public MixEnvelop<PatientRelationVO, PatientRelationVO> findPatientRelatioByAssistant(String doctor , String assistant, Integer page, Integer size){
        String sql ="SELECT " +
                " r.patient, " +
                " r.patient_name AS patientName, " +
                " IFNULL(year( from_days( datediff( now(), p.birthday))),'未知') age, " +
                " p.photo ," +
                " p.sex," +
                " h.label_name as health, " +
                " h.label AS healthcode " +
                " FROM " +
                " wlyy_specialist_patient_relation r JOIN "+basedb+".wlyy_patient p ON p.`code` = r.patient " +
                " LEFT JOIN ( " +
                " SELECT " +
                "  t.label, " +
                "  t.label_name, " +
                "  t.patient " +
                " FROM " +
                "  "+basedb+".wlyy_sign_patient_label_info t " +
                " WHERE " +
                "  t.label_type = '8' " +
                " AND t.`status` = '1' " +
                " ) h ON h.patient = r.patient " +
                " WHERE " +
                "  r.doctor = '"+doctor+"' AND r.status >=0  AND r.sign_status >0 "+
                " AND r.health_assistant = '"+assistant+"' LIMIT "+(page-1)*size+","+size;

        List<PatientRelationVO> patientRelationVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(PatientRelationVO.class));

        return MixEnvelop.getSuccess(SpecialistMapping.api_success,patientRelationVOs);
    }


    public MixEnvelop<PatientLabelVO, PatientLabelVO> getPatientByLabel(String doctor, String labelType, String labelCode, Integer page, Integer size){
        String sql="SELECT " +
                " p. NAME, " +
                " p. CODE, " +
                " p.sex," +
                " IFNULL( " +
                "  YEAR ( " +
                "   from_days(datediff(now(), p.birthday)) " +
                "  ), " +
                "  '未知' " +
                " ) age, " +
                " lb.labelName, " +
                " lb.labelType, " +
                " lb.label, " +
                " p.photo, " +
                " h.label_name as health, " +
                " h.label AS healthcode ," +
                " s.health_assistant AS healthAssistant," +
                " s.health_assistant_name AS healthAssistantName" +
                " FROM " +
                " ( " +
                "  SELECT " +
                "   i.label_type AS labelType, " +
                "   i.label, " +
                "   i.label_name AS labelName, " +
                "   i.patient " +
                "  FROM " +
                "   "+basedb+".wlyy_sign_patient_label_info i " +
                "  WHERE " +
                "   i.label = '"+labelCode+"' " +
                "  AND i.label_type = '"+labelType+"' " +
                "  AND i.`status` = '1' " +
                " ) lb " +
                " JOIN "+basedb+".wlyy_patient p ON p. CODE = lb.patient " +
                " JOIN wlyy_specialist_patient_relation s ON s.patient = lb.patient " +
                " LEFT JOIN ( " +
                " SELECT " +
                "  t.label, " +
                "  t.label_name, " +
                "  t.patient " +
                " FROM " +
                "  "+basedb+".wlyy_sign_patient_label_info t " +
                " WHERE " +
                "  t.label_type = '8' " +
                " AND t.`status` = '1' " +
                " ) h ON h.patient = lb.patient " +
                " WHERE s.doctor ='"+doctor+"' AND s.status >=0  AND s.sign_status >0"+
                " LIMIT "+(page-1)*size+","+size;

        List<PatientLabelVO> PatientLabelVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(PatientLabelVO.class));

        return MixEnvelop.getSuccess(SpecialistMapping.api_success,PatientLabelVOs);
    }

    public MixEnvelop<Long, Long> getLabelpatientCount(String doctor, String label, String labelType){
        String sql = "SELECT " +
                " COUNT(1) as total " +
                " FROM " +
                " ( " +
                "  SELECT " +
                "   i.label_type AS labelType, " +
                "   i.label, " +
                "   i.label_name AS labelName, " +
                "   i.patient " +
                "  FROM " +
                "   "+basedb+".wlyy_sign_patient_label_info i " +
                "  WHERE " +
                "   i.label = '"+label+"' "+
                "  AND i.label_type = '"+labelType+"' " +
                "  AND i.`status` = '1' " +
                " ) lb " +
                " JOIN wlyy_specialist_patient_relation s ON s.patient = lb.patient " +
                " WHERE " +
                " s.doctor = '"+doctor+"' AND s.status >=0  AND s.sign_status >0";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,count);
    }

    public MixEnvelop<Long, Long> getAssistantPatientCount(String doctor, String assistant){

        String sql = "SELECT COUNT(1) AS total FROM wlyy_specialist_patient_relation r WHERE r.doctor ='"+doctor+"'  AND r.health_assistant = '"+assistant+"' AND r.status >=0  AND r.sign_status >0";

        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,count);
    }

    public MixEnvelop<PatientRelationVO, PatientRelationVO> getDoctorPatientByName(String doctor, String nameKey, Integer page, Integer size){
        String sql ="SELECT " +
                " p.code AS patient, " +
                " p.`name` AS patientName, " +
                " p.photo, " +
                " IFNULL(year( from_days( datediff( now(), p.birthday))),'未知') age, " +
                " p.sex ," +
                " h.label_name as health, " +
                " h.label AS healthcode " +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " JOIN "+basedb+".wlyy_patient p ON r.patient = p.`code` " +
                " LEFT JOIN ( " +
                " SELECT " +
                "  t.label, " +
                "  t.label_name, " +
                "  t.patient " +
                " FROM " +
                "  "+basedb+".wlyy_sign_patient_label_info t " +
                " WHERE " +
                "  t.label_type = '8' " +
                " AND t.`status` = '1' " +
                " ) h ON h.patient = r.patient " +
                " WHERE " +
                " r.doctor='"+doctor+"' " +
                " AND r.status >=0  AND r.sign_status >0 " +
                " AND r.patient_name LIKE '%"+nameKey+"%' "+
                " LIMIT "+(page-1)*size+","+size;

        List<PatientRelationVO> patientRelationVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(PatientRelationVO.class));

        return MixEnvelop.getSuccess(SpecialistMapping.api_success,patientRelationVOs);
    }

    public MixEnvelop<PatientRelationVO, PatientRelationVO> findPatientNoAssistant(String doctor, Integer page, Integer size){

        String sqlTotal ="SELECT " +
                " COUNT(1) AS total" +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " JOIN "+basedb+".wlyy_patient p ON r.patient = p.`code` " +
                " JOIN ( " +
                " SELECT " +
                "  t.label, " +
                "  t.label_name, " +
                "  t.patient " +
                " FROM " +
                "  "+basedb+".wlyy_sign_patient_label_info t " +
                " WHERE " +
                "  t.label_type = '8' " +
                " AND t.`status` = '1' " +
                " ) h ON h.patient = r.patient " +
                " WHERE " +
                " r.doctor='"+doctor+"' " +
                " AND r.status >=0  AND r.sign_status >0" +
                " AND r.health_assistant IS NULL ";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTotal);
        Long total = 0L;
        if(list!=null&&list.size()>0){
            total =  (Long) list.get(0).get("total");
        }

        String sql ="SELECT " +
                " p.code AS patient, " +
                " p.`name` AS patientName, " +
                " p.photo, " +
                " IFNULL(year( from_days( datediff( now(), p.birthday))),'未知') age, " +
                " p.sex, " +
                " h.label_name as health, " +
                " h.label AS healthcode " +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " JOIN "+basedb+".wlyy_patient p ON r.patient = p.`code` " +
                " JOIN ( " +
                " SELECT " +
                "  t.label, " +
                "  t.label_name, " +
                "  t.patient " +
                " FROM " +
                "  "+basedb+".wlyy_sign_patient_label_info t " +
                " WHERE " +
                "  t.label_type = '8' " +
                " AND t.`status` = '1' " +
                " ) h ON h.patient = r.patient " +
                " WHERE " +
                " r.doctor='"+doctor+"' " +
                " AND r.status >=0  AND r.sign_status >0 " +
                " AND r.health_assistant IS NULL "+
                " LIMIT "+(page-1)*size+","+size;
        List<PatientRelationVO> patientRelationVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(PatientRelationVO.class));

        return MixEnvelop.getSuccessList(SpecialistMapping.api_success,patientRelationVOs,total.intValue());

    }

    public MixEnvelop<SpecialistTeamVO, SpecialistTeamVO> signSpecialistTeam(String patient, String patientName, String doctor, String doctorName, Long teamCode){

        //1.查询该居民是否已经与该专科医生签约
        String checkDoctorSql = "SELECT " +
                " r.id AS relationCode," +
                " r.patient, " +
                " r.patient_name AS patientName, " +
                " r.team_code AS teamCode, " +
                " t.`name`, " +
                " d.photo ," +
                " d.code AS doctor," +
                " d.name AS doctorName" +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " JOIN "+basedb+".wlyy_admin_team t ON t.id = r.team_code " +
                " JOIN "+basedb+".wlyy_doctor d ON d. CODE = r.doctor " +
                " WHERE " +
                " r.patient = '"+patient+"' " +
                " AND r.doctor = '"+doctor+"' " +
                " AND r.`status` >= 0 " +
                " AND r.sign_status >=0 " +
                " AND t.available = '1' ";
        List<SpecialistTeamVO> specialistTeamVOs = jdbcTemplate.query(checkDoctorSql,new BeanPropertyRowMapper(SpecialistTeamVO.class));

        if(specialistTeamVOs!=null&&specialistTeamVOs.size()>0){

            for(SpecialistTeamVO specialistTeamVO:specialistTeamVOs){
                String menberSql = "SELECT " +
                        " m.doctor_code AS doctorCode, " +
                        " d.`name` AS doctorName " +
                        " FROM " +
                        " "+basedb+".wlyy_admin_team_member m " +
                        " JOIN "+basedb+".wlyy_doctor d ON m.doctor_code = d.`code` " +
                        " WHERE  " +
                        " m.available='1' " +
                        " AND m.team_id="+specialistTeamVO.getTeamCode();
                List<AdminTeamMemberVO> adminTeamMemberVOs = jdbcTemplate.query(menberSql,new BeanPropertyRowMapper(AdminTeamMemberVO.class));
                specialistTeamVO.setMembers(adminTeamMemberVOs);
            }
            return MixEnvelop.getSuccess(SpecialistMapping.doctor_exist,specialistTeamVOs.get(0));
        }

        //验证团队是否已经签约
        String checkTeamSql = "SELECT " +
                " r.id AS relationCode," +
                " r.patient, " +
                " r.patient_name AS patientName, " +
                " r.team_code AS teamCode, " +
                " t.`name`, " +
                " d.photo " +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " JOIN "+basedb+".wlyy_admin_team t ON t.id = r.team_code " +
                " JOIN "+basedb+".wlyy_doctor d ON d. CODE = r.doctor " +
                " WHERE " +
                " r.patient = '"+patient+"' " +
                " AND r.team_code = " +teamCode+
                " AND r.`status` >=0 " +
                " AND r.sign_status >=0 " +
                " AND t.available = '1' ";
        List<SpecialistTeamVO> teamVOs = jdbcTemplate.query(checkTeamSql,new BeanPropertyRowMapper(SpecialistTeamVO.class));
        if(teamVOs!=null&&teamVOs.size()>0){

            for(SpecialistTeamVO specialistTeamVO:teamVOs){
                String menberSql = "SELECT " +
                        " m.doctor_code AS doctorCode, " +
                        " d.`name` AS doctorName " +
                        " FROM " +
                        " "+basedb+".wlyy_admin_team_member m " +
                        " JOIN "+basedb+".wlyy_doctor d ON m.doctor_code = d.`code` " +
                        " WHERE  " +
                        " m.available='1' " +
                        " AND m.team_id="+specialistTeamVO.getTeamCode();
                List<AdminTeamMemberVO> adminTeamMemberVOs = jdbcTemplate.query(menberSql,new BeanPropertyRowMapper(AdminTeamMemberVO.class));
                specialistTeamVO.setMembers(adminTeamMemberVOs);
            }
            return MixEnvelop.getSuccess(SpecialistMapping.team_exist,teamVOs.get(0));
        }

        //存储签约关系
        SpecialistPatientRelationDO relation = new SpecialistPatientRelationDO();
        relation.setDoctor(doctor);
        relation.setDoctorName(doctorName);
        relation.setPatient(patient);
        relation.setPatientName(patientName);
        relation.setTeamCode(teamCode.intValue());
        relation.setStatus("0");
        relation.setSignStatus("0");
        relation.setCreateTime(new Date());
        SpecialistPatientRelationDO relationDO = specialistPatientRelationDao.save(relation);

        return MixEnvelop.getSuccess(SpecialistMapping.api_success,relationDO.getId());
    }

    public MixEnvelop<Boolean, Boolean> agreeSpecialistTeam(String state, String relationCode, String remark){

        SpecialistPatientRelationDO relation = specialistPatientRelationDao.findOne(relationCode);

        if("0".equals(state)){
            relation.setSignStatus("-2");
            relation.setStatus("-1");
            relation.setRemark(remark);
            specialistPatientRelationDao.save(relation);
        }else{
            relation.setSignStatus("1");
            specialistPatientRelationDao.save(relation);
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,relation);
    }

    public MixEnvelop<PatientSignInfoVO, PatientSignInfoVO> findPatientSigninfo(String code){
        String sql = "SELECT " +
                " r.patient, " +
                " r.patient_name AS patientName, " +
                " r.doctor, " +
                " r.doctor_name AS doctorName, " +
                " d.hospital, " +
                " d.hospital_name AS hospitalName, " +
                " d.photo, " +
                " d.dept, " +
                " d.dept_name AS deptName, " +
                " t.`name` AS teamName, " +
                " t.id As teamCode, " +
                " r.create_time AS createTime," +
                " r.status," +
                " r.sign_status AS signStatus  " +
                " r.team_code AS teamCode  " +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " JOIN "+basedb+".wlyy_doctor d ON r.doctor = d.code " +
                " JOIN "+basedb+".wlyy_admin_team t ON t.id = r.team_code " +
                " WHERE " +
                " r.id = '"+code+"'";
        List<PatientSignInfoVO> patientSignInfoVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(PatientSignInfoVO.class));
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,patientSignInfoVOs.get(0));
    }

    public MixEnvelop<SpecialistTeamVO, SpecialistTeamVO> findPatientTeamList(String patient){
        String sql = "SELECT " +
                " r.id AS relationCode, " +
                " r.patient, " +
                " r.patient_name AS patientName, " +
                " r.team_code, " +
                " t.`name` AS name," +
                " d.photo," +
                " md.code AS doctor," +
                " md.name AS doctorName" +
                " FROM " +
                " wlyy_specialist_patient_relation r  " +
                " JOIN "+basedb+".wlyy_admin_team t ON t.id = r.team_code " +
                " JOIN "+basedb+".wlyy_doctor d ON t.leader_code = d.`code` " +
                " JOIN "+basedb+".wlyy_doctor md ON md.code = r.doctor " +
                " WHERE " +
                " r.patient = '"+patient+"' " +
                " AND r.`status` >=0  " +
                " AND r.sign_status >0";
        List<SpecialistTeamVO> specialistTeamVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SpecialistTeamVO.class));
        if(specialistTeamVOs!=null&&specialistTeamVOs.size()>0){

            for(SpecialistTeamVO specialistTeamVO:specialistTeamVOs){
                String menberSql = "SELECT " +
                        " m.doctor_code AS doctorCode, " +
                        " d.`name` AS doctorName " +
                        " FROM " +
                        " "+basedb+".wlyy_admin_team_member m " +
                        " JOIN "+basedb+".wlyy_doctor d ON m.doctor_code = d.`code` " +
                        " WHERE  " +
                        " m.available='1' " +
                        " AND m.team_id="+specialistTeamVO.getTeamCode();
                List<AdminTeamMemberVO> adminTeamMemberVOs = jdbcTemplate.query(menberSql,new BeanPropertyRowMapper(AdminTeamMemberVO.class));
                specialistTeamVO.setMembers(adminTeamMemberVOs);
            }
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,specialistTeamVOs);
    }

    public MixEnvelop findPatientSignSpecialist(String patient){
        String sql = "SELECT " +
                " r.patient, " +
                " r.team_code AS teamCode," +
                " r.patient_name AS patientName, " +
                " d.name AS doctorName, " +
                " d.code AS doctor, " +
                " d.photo, " +
                " d.dept, " +
                " d.dept_name AS deptName, " +
                " d.hospital, " +
                " d.hospital_name AS hospital_name, " +
                " d.`level` " +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " JOIN "+basedb+".wlyy_doctor d ON r.doctor = d.`code` " +
                " WHERE  " +
                " r.patient ='"+patient+"' " +
                " AND r.`status`>=0 " +
                " AND r.sign_status >0";
        List<PatientSignInfoVO> patientSignInfoVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(PatientSignInfoVO.class));
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,patientSignInfoVOs);
    }

    public MixEnvelop findPatientSignSpecialistInfo(String patient, String doctor){
        String sql = "SELECT " +
                " r.id AS relationCode," +
                " r.patient, " +
                " r.team_code AS teamCode," +
                " r.patient_name AS patientName, " +
                " r.health_assistant AS healthAssistant," +
                " r.health_assistant_name AS healthAssistantName," +
                " r.create_time AS createTime," +
                " d.name AS doctorName, " +
                " d.code AS doctor, " +
                " d.photo, " +
                " d.dept, " +
                " d.dept_name AS deptName, " +
                " d.hospital, " +
                " d.hospital_name AS hospital_name, " +
                " d.`level` " +
                " FROM " +
                " wlyy_specialist_patient_relation r " +
                " JOIN "+basedb+".wlyy_doctor d ON r.doctor = d.`code` " +
                " WHERE  " +
                " r.patient ='"+patient+"' " +
                " AND r.doctor ='"+doctor+"' " +
                " AND r.`status`>=0 " +
                " AND r.sign_status >0";
        List<PatientSignInfoVO> patientSignInfoVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(PatientSignInfoVO.class));
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,patientSignInfoVOs.get(0));
    }
    
    public MixEnvelop findDoctorAndDoctorHealthBySpecialDoctor(String doctor) {
        String sql = "SELECT " +
                "doctor.CODE AS CODE," +
                "doctor.NAME AS NAME," +
                "doctor.sex AS sex," +
                "doctor.birthday AS birthday," +
                "doctor.photo AS photo," +
                "doctor.mobile AS mobile," +
                "doctor.hospital AS hospital," +
                "doctor.hospital_name AS hospitalName," +
                "doctor.dept AS dept," +
                "doctor.dept_name AS deptName," +
                "doctor.job AS job," +
                "doctor.job_name AS jobName," +
                "doctor.LEVEL AS LEVEL," +
                "doctor.qrcode AS qrcode," +
                "doctor.czrq AS czrq," +
                "doctor.del AS del," +
                "doctor.idcard AS idcard " +
                "FROM wlyy.wlyy_doctor doctor RIGHT JOIN ( " +
                "SELECT a.doctor AS doctorcode FROM wlyy.wlyy_sign_family a RIGHT JOIN ( " +
                "SELECT patient FROM wlyy_specialist_patient_relation WHERE sign_status> 0 AND `status`>=0 AND doctor='"+doctor+"') b ON a.patient=b.patient WHERE a.`status`=1 " +
                "UNION  " +
                "SELECT a.doctor_health AS doctorcode FROM wlyy.wlyy_sign_family a RIGHT JOIN ( " +
                "SELECT patient FROM wlyy_specialist_patient_relation WHERE sign_status> 0 AND `status`>=0 AND doctor='"+doctor+"') b ON a.patient=b.patient WHERE a.`status`=1) " +
                "t ON doctor.CODE=t.doctorcode";
        List<SignFamilyDoctorVO> patientSignInfoVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SignFamilyDoctorVO.class));
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,patientSignInfoVOs);
    }
    
    public MixEnvelop<Long,Long> findSpecialistSignFamilyPatientCout(String specialdoctor, String familydoctor) {
    
        String sql = "SELECT count(1) AS total " +
                "FROM wlyy.wlyy_sign_family a WHERE a.`status`=1 AND a.expenses_status=1 " +
                "AND patient IN (" +
                "SELECT patient FROM wlyy_specialist.wlyy_specialist_patient_relation WHERE sign_status> 0 AND `status`>=0 " +
                "AND doctor='"+specialdoctor+"') " +
                "AND (a.doctor='"+familydoctor+"' OR a.doctor_health='"+familydoctor+"') ";
    
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,count);
    }
    
    public MixEnvelop<PatientRelationVO,PatientRelationVO> getSpecialistSignFamilyPatientByName(String specialdoctor, String familydoctor, String nameKey, Integer page, Integer size) {
        String sql ="SELECT " +
                "p.CODE AS patient," +
                "p.`name` AS patientName," +
                "p.photo," +
                "IFNULL(YEAR (from_days(datediff(now(),p.birthday))),'未知') age," +
                "p.sex " +
                "FROM wlyy.wlyy_patient p " +
                "JOIN ( " +
                " SELECT a.patient FROM wlyy.wlyy_sign_family a WHERE patient IN (" +
                "  SELECT patient FROM wlyy_specialist_patient_relation WHERE sign_status> 0 AND `status`>=0 " +
                "  AND doctor='"+specialdoctor+"') " +
                "  AND (a.doctor='"+familydoctor+"' OR a.doctor_health='"+familydoctor+"') ";
        if(StringUtils.isNotBlank(nameKey)){
            sql= sql + "  AND a.NAME LIKE '%"+nameKey+"%' ";
        }
        sql= sql +" AND a.`status`=1 AND a.expenses_status=1) s ON p.CODE=s.patient" +
                " LIMIT "+(page-1)*size+","+size;
    
        List<PatientRelationVO> patientRelationVOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(PatientRelationVO.class));
    
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,patientRelationVOs);
    }


//    public Envelop<Boolean> createSpecialists(List<SpecialistDO> info){
//        specialistDao.save(info);
//        return Envelop.getSuccess(SpecialistMapping.api_success,true);
//    }

//    public Envelop<Boolean> createSpecialistArticle(SpecialistArticleDO articleDO){
//        specialistArticleDao.save(articleDO);
//        return Envelop.getSuccess(SpecialistMapping.api_success,true);
//    }
//
//    public Envelop<Boolean> createSpscialistConsult(SpecialistConsultDO consultDO){
//        specialistConsultDao.save(consultDO);
//        return Envelop.getSuccess(SpecialistMapping.api_success,true);
//    }
}
