package com.yihu.jw.service.rehabilitation;

import com.yihu.jw.dao.rehabilitation.GuidanceMessageLogDao;
import com.yihu.jw.dao.rehabilitation.PatientRehabilitationPlanDao;
import com.yihu.jw.dao.rehabilitation.RehabilitationDetailDao;
import com.yihu.jw.entity.specialist.rehabilitation.GuidanceMessageLogDO;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationDetailDO;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.util.common.IdCardUtil;
import com.yihu.jw.util.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 刘文彬 on 2018/8/16.
 */
@Service
@Transactional
public class RehabilitationManageService {

    @Value("${basedb.name}")
    private String basedb;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RehabilitationDetailDao rehabilitationDetailDao;
    @Autowired
    private PatientRehabilitationPlanDao patientRehabilitationPlanDao;
    @Autowired
    private GuidanceMessageLogDao guidanceMessageLogDao;

    /**
     * 康复管理（专科）-- 计划列表
     * @param doctorType 1.专科，2.家医
     * @param doctorCode
     * @param diseaseCode
     * @param planType
     * @param todaybacklog
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MixEnvelop<Map<String,Object>, Map<String,Object>> findRehabilitationPlan(Integer doctorType,String doctorCode, String diseaseCode, Integer planType,Integer todaybacklog, String patientCondition,Integer page, Integer pageSize) throws Exception{

        String leftSql =" left join "+basedb+".wlyy_sign_family f on f.patient=p.patient and f.expenses_status='1' and f.status=1 ";
//        if(StringUtils.isNotEmpty(diseaseCode)){
//            leftSql += " left join "+basedb+".wlyy_patient_disease_server s on p.patient=s.patient and s.del=1 and s.disease ='"+diseaseCode+"'" ;
//        }
        String sql = " select p.*,f.idcard,f.hospital_name from wlyy_specialist.wlyy_patient_rehabilitation_plan p  " +leftSql+
                " where 1=1 " ;
        if(doctorType==1){//专科医生是根据计划的创建者字段过滤
            sql+=" and (p.create_user in (select r.doctor assistant from " +
                    " wlyy_specialist.wlyy_specialist_patient_relation r where r.health_assistant ='"+doctorCode+"') " +
                    " or p.create_user='"+doctorCode+"') ";
        }
        if(planType!=null){
            sql += " and p.plan_type="+planType;
        }
        if(StringUtils.isNotEmpty(diseaseCode)){
            sql+=" and p.disease='"+diseaseCode+"'";
        }
        if(StringUtils.isNotEmpty(patientCondition)){
            sql += " and (f.idcard like '%"+patientCondition+"%' or p.name like '%"+patientCondition+"%') ";
        }
        String finalSql = "";

        String todayStart = DateUtil.getStringDateShort()+" "+"00:00:00";
        String todayEnd = DateUtil.getStringDateShort()+" "+"23:59:59";
        String condition ="";
        if(doctorType==2){//家医是根据计划详情表的执行者字段过滤
            condition+=" and doctor='"+doctorCode+"' ";
        }
        if(todaybacklog!=null&&todaybacklog==1){
            condition += " and execute_time>='"+todayStart+"' and execute_time<='"+todayEnd+"'";
        }
        finalSql =" select DISTINCT b.* from (select DISTINCT plan_id from wlyy_specialist.wlyy_rehabilitation_plan_detail where  1=1 "+condition+") a " +
                " LEFT JOIN ("+sql+") b on a.plan_id=b.id ";
//        if(todaybacklog!=null&&todaybacklog==1){
//            finalSql = " select DISTINCT b.* from (select DISTINCT plan_id from wlyy_specialist.wlyy_rehabilitation_plan_detail where execute_time>='"+todayStart+"' and execute_time<='"+todayEnd+"') a "+
//                    "LEFT JOIN ("+sql+") b on a.plan_id=b.id";
//        }else{
//            finalSql = " select b.* from ("+sql+") b ";
//        }
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(finalSql);
        int count = 0;
        if(rstotal!=null&&rstotal.size()>0){
            count = rstotal.size();
        }
        finalSql += " ORDER BY b.create_time DESC LIMIT "+(page-1)*pageSize+","+pageSize;
        List<Map<String,Object>> patientRehabilitationPlanDOList = jdbcTemplate.queryForList(finalSql);
        List<Map<String,Object>> resultList = new ArrayList<>();
        for(Map<String,Object> one:patientRehabilitationPlanDOList){
            Map<String,Object> resultMap = new HashMap<>();
            Integer age = IdCardUtil.getAgeForIdcard(one.get("idcard")+"");
            String sex = IdCardUtil.getSexForIdcard_new(one.get("idcard")+"");
            resultMap.put("age",age);
            resultMap.put("hospitalName",one.get("hospital_name"));
            resultMap.put("sex","1".equals(sex)?"男":("2".equals(sex)?"女":"未知"));
            resultMap.put("patientName",one.get("name"));
            resultMap.put("id",one.get("id"));
            //健康情况
            resultMap.put("healthyCondition","康复期");
            //安排类型
            String planTypeName = null;
            Integer planTypeTemp = (Integer)one.get("plan_type");
            switch (planTypeTemp){
                case 1:planTypeName="康复计划" ;break;
                case 2:planTypeName="（转）社区医院" ;break;
                case 3:planTypeName="（转）转家庭病床" ;break;
            }
            resultMap.put("planTypeName",planTypeName);
            //今日待办
            Date beginTime = DateUtil.strToDateLong(DateUtil.getStringDateShort()+ " "+"00:00:00");
            Date endTime = DateUtil.strToDateLong(DateUtil.getStringDateShort()+ " "+"23:59:59");
            Integer todayBacklogCount = rehabilitationDetailDao.todayBacklogCount(1,one.get("id").toString(),beginTime,endTime);
            resultMap.put("todayBacklogCount",todayBacklogCount);//今日待办总数
            //已完成
            Integer finishedCount = rehabilitationDetailDao.completenessCount(1,one.get("id").toString());
            resultMap.put("finishedCount",finishedCount);//已完成
            //未完成
            Integer notstartedCount = rehabilitationDetailDao.completenessCount(0,one.get("id").toString());//未开始
            Integer underwayCount = rehabilitationDetailDao.completenessCount(2,one.get("id").toString());//已预约
            Integer unfinishedCount = notstartedCount+underwayCount;
            resultMap.put("unfinishedCount",unfinishedCount);//未完成
            //完成度（已完成/（已完成+未完成））
            resultMap.put("allCount",finishedCount+unfinishedCount);//全部

            resultList.add(resultMap);

        }
        return MixEnvelop.getSuccessListWithPage(SpecialistMapping.api_success,resultList,page,pageSize,Long.valueOf(count));
    }


//    public MixEnvelop<Map<String,Object>, Map<String,Object>> findRehabilitationPlanDetailList(String doctorCode, String diseaseCode, Integer planType,Integer todaybacklog,String patientCode,Integer page, Integer pageSize) throws Exception{
//        List<Map<String,Object>> resultList = new ArrayList<>();
//        String leftSql =" left join "+basedb+".wlyy_sign_family f on f.patient=p.patient ";
//        if(StringUtils.isNotEmpty(diseaseCode)){
//            leftSql += " left join "+basedb+".wlyy_patient_disease_server s on p.patient=s.patient and s.del=1 and s.disease ='"+diseaseCode+"'" ;
//        }
//        String sql = " select p.*,f.idcard,f.hospital_name,f.admin_team_code from wlyy_specialist.wlyy_patient_rehabilitation_plan p  " +leftSql+
//                " where (p.create_user in (select r.health_assistant assistant from " +
//                " wlyy_specialist.wlyy_specialist_patient_relation r where r.health_assistant is not null) " +
//                " or p.create_user='"+doctorCode+"') and p.patient='"+patientCode+"'" ;
//        if(planType!=null){
//            sql += " and p.plan_type="+planType;
//        }
//        String finalSql = "";
//        if(todaybacklog!=null&&todaybacklog==1){
//            finalSql = " select b.* from (select DISTINCT program_id wlyy_rehabilitation_plan_detail where execute_time>='' and execute_time<='') a "+
//                    "LEFT JOIN ("+sql+") b on a.program_id=b.id";
//        }else{
//            finalSql = " select b.* from ("+sql+") b ";
//        }
//        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(finalSql);
//        int count = 0;
//        if(rstotal!=null&&rstotal.size()>0){
//            count = rstotal.size();
//        }
//        finalSql += " ORDER BY b.create_time DESC LIMIT "+(page-1)*pageSize+","+pageSize;
//        List<Map<String,Object>> patientRehabilitationPlanDOList = jdbcTemplate.queryForList(finalSql);
//        for(Map<String,Object> one:patientRehabilitationPlanDOList){
//            Map<String,Object> resultMap = new HashMap<>();
//            Integer age = IdCardUtil.getAgeForIdcard(one.get("idcard")+"");
//            String sex = IdCardUtil.getSexForIdcard_new(one.get("idcard")+"");
//            resultMap.put("age",age);
//            resultMap.put("hospitalName",one.get("hospital_name"));
//            resultMap.put("sex","1".equals(sex)?"男":("2".equals(sex)?"女":"未知"));
//            resultMap.put("patientName",one.get("name"));
//
//            //疾病类型
//            String diseaseSql = " select s.* from "+basedb+".wlyy_patient_disease_server s where s.del=1 and s.patient='"+patientCode+"' ";
//            List<Map<String,Object>> diseaseList = jdbcTemplate.queryForList(diseaseSql);
//            List<String> disease = new ArrayList<>();
//            for(Map<String,Object> one2:diseaseList){
//                disease.add(one2.get("disease_name")+"");
//            }
//            resultMap.put("diseaseList",disease);
//
//            //家庭医生
//            Integer adminTeamCode = Integer.valueOf(one.get("admin_team_code").toString());
//            String adminTeamSql = " select t.*,h.name as hospitalName from "+basedb+".wlyy_admin_team t left join "+basedb+".dm_hospital h on h.code=t.org_code where t.available = 1 and t.id="+adminTeamCode;
//            List<Map<String,Object>> adminTeamList = jdbcTemplate.queryForList(adminTeamSql);
//            String teanName = adminTeamList.size()>0?adminTeamList.get(0).get("name").toString():"";
//            String hospitalName = adminTeamList.size()>0?adminTeamList.get(0).get("hospitalName").toString():"";
//
//            resultMap.put("teanName",teanName);
//            resultMap.put("teanHospitalName",hospitalName);
//
//            //专科医生
//            String specialistSql = " select * from wlyy_specialist.wlyy_specialist_patient_relation where doctor='"+doctorCode+
//                    "' and patient ='"+patientCode+"' and status in ('1','0') and sign_status ='1' " ;
//            List<Map<String,Object>> specialistList = jdbcTemplate.queryForList(specialistSql);
//            Integer specialistAdminTeamCode = specialistList.size()>0?(Integer)specialistList.get(0).get("team_code"):0;
//            String specialistAdminTeamSql = " select t.*,h.name as hospitalName from "+basedb+".wlyy_admin_team t left join "+basedb+".dm_hospital h on h.code=t.org_code where t.available = 1 and t.id="+adminTeamCode;
//            List<Map<String,Object>> specialistAdminTeamList = jdbcTemplate.queryForList(specialistAdminTeamSql);
//            String specialistTeanName = specialistAdminTeamList.size()>0?specialistAdminTeamList.get(0).get("name").toString():"";
//            String specialistHospitalName = specialistAdminTeamList.size()>0?specialistAdminTeamList.get(0).get("hospitalName").toString():"";
//            resultMap.put("specialistTeanName",specialistTeanName);
//            resultMap.put("specialistHospitalName",specialistHospitalName);
//
//            //安排类型
//            String planTypeName = null;
//            Integer planTypeTemp = (Integer)one.get("plan_type");
//            switch (planTypeTemp){
//                case 1:planTypeName="康复计划" ;break;
//                case 2:planTypeName="（转）社区医院" ;break;
//                case 3:planTypeName="（转）转家庭病床" ;break;
//            }
//
//            //服务项列表
//            String planDetailSql = "select DISTINCT d.service_item_id from wlyy_rehabilitation_plan_detail d  where d.program_id ='"+one.get("id")+"'";
//            List<Map<String,Object>> planDetailList = jdbcTemplate.queryForList(planDetailSql);
//            List<Map<String,Object>> planDetail = new ArrayList<>();
//            for(Map<String,Object> one3:planDetailList){
//                Map<String,Object> temp = new HashMap<>();
//                temp.put("planTypeName",planTypeName);
//                temp.put("status",one3.get("status"));
//                one3.get("status");
//            }
//        }
//
//        return MixEnvelop.getSuccessListWithPage(SpecialistMapping.api_success,resultList,page,pageSize,Long.valueOf(count));
//    }


    /**
     * 康复管理更多计划
     * @param doctorCode
     * @param patientCode
     * @return
     * @throws Exception
     */
    public MixEnvelop findRehabilitationPlanDetailList(String doctorCode,String patientCode) throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        //专科医生
        String specialistRelationSql = "select r.*,t.name as teamName from wlyy_specialist_patient_relation r left join wlyy_admin_team t on r.team_code=t.id where r.sign_status ='1' and r.status in('0','1') and r.patient='"+patientCode+"' and r.doctor='"+doctorCode+"'";
        List<Map<String,Object>> specialistRelationList = jdbcTemplate.queryForList(specialistRelationSql);
        Map<String,Object> specialistMap = specialistRelationList.get(0);
        resultMap.put("specialistAdminTeamName",specialistMap.get("teamName"));
        String specialistFinishItemSql = "";
//        Integer familyUnfinishCount = rehabilitationDetailDao.unfinishItemByDoctor(signFamilyMap.get("doctor").toString(),signFamilyMap.get("doctor_health").toString(),patientCode,1);
//        Integer familyFinishCount = rehabilitationDetailDao.findItemByDoctor(signFamilyMap.get("doctor").toString(),signFamilyMap.get("doctor_health").toString(),patientCode);
//        Integer familyServiceCount = rehabilitationDetailDao.completeServiceByDoctor(signFamilyMap.get("doctor").toString(),signFamilyMap.get("doctor_health").toString(),patientCode,1);

        resultMap.put("specialistFinishItemCount",specialistMap.get("teamName"));//完成项目
        resultMap.put("specialistServiceRecordCount",specialistMap.get("teamName"));//服务记录

        //家庭医生（包括全科医生、健管师）
        String signFamilySql = "SELECT f.*,t.name as teamName FROM wlyy.wlyy_sign_family f LEFT JOIN wlyy.wlyy_admin_team t on f.admin_team_code=t.id where f.status =1 and f.expenses_status='1' and f.patient='"+patientCode+"'";
        List<Map<String,Object>> signFamilyList = jdbcTemplate.queryForList(signFamilySql);
        Map<String,Object> signFamilyMap = signFamilyList.get(0);
        resultMap.put("signFamilyAdminTeamName",signFamilyMap.get("teamName"));
        Integer familyUnfinishCount = rehabilitationDetailDao.unfinishItemByDoctor(signFamilyMap.get("doctor").toString(),signFamilyMap.get("doctor_health").toString(),patientCode,1);
        Integer familyFinishCount = rehabilitationDetailDao.findItemByDoctor(signFamilyMap.get("doctor").toString(),signFamilyMap.get("doctor_health").toString(),patientCode);
        Integer familyServiceCount = rehabilitationDetailDao.completeServiceByDoctor(signFamilyMap.get("doctor").toString(),signFamilyMap.get("doctor_health").toString(),patientCode,1);
        resultMap.put("signFamilyFinishItemCount",familyFinishCount-familyUnfinishCount);//完成项目
        resultMap.put("signFamilyServiceRecordCount",familyServiceCount);//服务次数

        //基础信息
        resultMap.put("hospitalName",signFamilyMap.get("hospital_name"));
        Integer age = IdCardUtil.getAgeForIdcard(signFamilyMap.get("idcard")+"");
        String sex = IdCardUtil.getSexForIdcard_new(signFamilyMap.get("idcard")+"");
        resultMap.put("age",age);
        resultMap.put("sex","1".equals(sex)?"男":("2".equals(sex)?"女":"未知"));
        resultMap.put("patientName",signFamilyMap.get("name"));
        //疾病类型
        String diseaseSql = " select s.* from "+basedb+".wlyy_patient_disease_server s where s.del=1 and s.patient='"+patientCode+"' ";
        List<Map<String,Object>> diseaseList = jdbcTemplate.queryForList(diseaseSql);
        List<String> disease = new ArrayList<>();
        for(Map<String,Object> one2:diseaseList){
            disease.add(one2.get("disease_name")+"");
        }
        resultMap.put("diseaseList",disease);

        //计划列表
        List<PatientRehabilitationPlanDO> list = patientRehabilitationPlanDao.findByPatientAndCreateUser(patientCode,doctorCode);
        List<Map<String,Object>> rehabilitationPlanList = new ArrayList<>();
        for(PatientRehabilitationPlanDO one:list){
            Map<String,Object> planMap = new HashMap<>();
            //安排类型
            String planTypeName = null;
            Integer planTypeTemp = one.getPlanType();
            switch (planTypeTemp){
                case 1:planTypeName="康复计划" ;break;
                case 2:planTypeName="（转）社区医院" ;break;
                case 3:planTypeName="（转）转家庭病床" ;break;
            }
            planMap.put("planTypeName",planTypeName);
            planMap.put("status",one.getStatus());//0已中止，1进行中，2已完成

            //今日待办
            Date beginTime = DateUtil.strToDateLong(DateUtil.getStringDateShort()+ " "+"00:00:00");
            Date endTime = DateUtil.strToDateLong(DateUtil.getStringDateShort()+ " "+"23:59:59");
            Integer todayBacklogCount = rehabilitationDetailDao.todayBacklogCount(1,one.getId(),beginTime,endTime);
            resultMap.put("todayBacklogCount",todayBacklogCount);
            //已完成
            Integer finishedCount = rehabilitationDetailDao.completenessCount(2,one.getId());
            resultMap.put("finishedCount",finishedCount);//已完成
            //未完成
            Integer notstartedCount = rehabilitationDetailDao.completenessCount(1,one.getId());//未开始
            Integer underwayCount = rehabilitationDetailDao.completenessCount(1,one.getId());//进行中
            Integer unfinishedCount = notstartedCount+underwayCount;
            resultMap.put("unfinishedCount",unfinishedCount);//未完成
            //完成度（已完成/（已完成+未完成））
            resultMap.put("allCount",finishedCount+unfinishedCount);//总数
            rehabilitationPlanList.add(resultMap);
        }
        resultMap.put("rehabilitationPlanList",rehabilitationPlanList);
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,resultMap);
    }


    /**
     * 日历
     * @param planId 计划id
     * @param searchTask 快速查找任务（1、我的任务，2、随访，3、复诊，4、健康教育）
     * @param status 任务状态（0未完成，1已完成，2已预约）
     * @param role 1、家庭医生，2、专科医生
     * @param doctorCode 登陆医生
     */
    public MixEnvelop calendarPlan(String planId,Integer searchTask,Integer status,Integer role,String doctorCode){
        String executeStartTime = DateUtil.getFristDayOfMonth()+" "+"00:00:00";
        String executeEndTime = DateUtil.getLastDayOfMonth()+" "+"23:59:59";
        String sql = " select d.* wlyy_rehabilitation_plan_detail d " +
                "LEFT JOIN wlyy_service_item i on d.service_item_id=i.id " +
                "where d.execute_time>='"+executeStartTime+"' and d.execute_time<='"+executeEndTime+"' and d.plan_id='"+planId+"' " ;
        if(searchTask!=null){
            if(searchTask==1){
//                if(role==1){
//                    sql+="and d.type='"+role+"' " ;
//                }
                sql+="and d.doctor='"+doctorCode+"' " ;
            }else{
                sql+=" and i.type='"+searchTask+"' " ;
            }
        }
        if(status!=null){
            sql+= "and d.status="+status;
        }

        List<Map<String,Object>> rehabilitationDetailList = jdbcTemplate.queryForList(sql);
//        List<RehabilitationDetailDO> rehabilitationDetailList = rehabilitationDetailDao.findByPlanId(DateUtil.strToDate(executeStartTime),DateUtil.strToDate(executeEndTime),planId);
        Map<String,Map<String,Object>> map = new HashMap<>();
        for(Map<String,Object> one:rehabilitationDetailList){
            String executeTime = DateUtil.dateToStr((Date) one.get("execute_time"),DateUtil.YYYY_MM_DD);
            Map<String,Object> m = null;
            if(map.containsKey(executeTime)){
                m = map.get(executeTime);
                Map<String,Integer> family = null;
                Map<String,Integer> specialist = null;
                if((Integer)one.get("type")==1){//家庭医生
                    if(m.containsKey("family")){
                        family = (Map<String,Integer>)m.get("family");
                    }else{
                        family = new HashMap<>();
                        family.put("all",0);
                        family.put("finish",0);
                    }
                    if((Integer)one.get("status")!=1){
                        family.put("finish",family.get("finish")+1);
                    }
                    family.put("all",family.get("all")+1);
                    m.put("family",family);
                }else if((Integer)one.get("type")==2){

                    if(m.containsKey("specialist")){

                        specialist = (Map<String,Integer>)m.get("specialist");
                    }else{
                        specialist = new HashMap<>();
                        specialist.put("all",0);
                        specialist.put("finish",0);
                    }
                    if((Integer)one.get("status")!=1){
                        specialist.put("finish",((Integer)family.get("finish"))+1);
                    }
                    specialist.put("all",((Integer)family.get("all"))+1);
                    m.put("specialist",specialist);
                }
            }else{
                m = new HashMap<>();
//                m.put("specialist",new HashMap<String,Object>());
                if((Integer)one.get("type")==1){//家庭医生
                    Map<String,Integer> family = new HashMap<>();
                    family.put("all",0);
                    family.put("finish",0);
                    if((Integer)one.get("status")!=1){
                        family.put("finish",family.get("finish")+1);
                    }
                    family.put("all",family.get("all")+1);
                    m.put("family",family);
                }else if((Integer)one.get("type")==2){//专科医生
                    Map<String,Integer> specialist = new HashMap<>();
                    specialist.put("all",0);
                    specialist.put("finish",0);
                    if((Integer)one.get("status")!=1){
                        specialist.put("finish",specialist.get("finish")+1);
                    }
                    specialist.put("all",specialist.get("all")+1);
                    m.put("specialist",specialist);
                }
            }
            if(m.containsKey("myTaskFlag")){
                if((Integer)m.get("myTaskFlag")==0){
                    if(doctorCode.equals(one.get("doctor").toString())){
                        m.put("myTaskFlag",1);
                    }else{
                        m.put("myTaskFlag",0);
                    }
                }
            }else{
                m.put("myTaskFlag",0);
            }
            map.put(executeTime,m);
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,map);
    }

    /**
     * 日历列表
     * @param planId 计划id
     * @param searchTask 快速查找任务（1、我的任务，2、随访，3、复诊，4、健康教育）
     * @param status 任务状态（0未完成，1已完成，2已预约）
     * @param doctorCode 登陆医生
     */
    public MixEnvelop calendarPlanList(String planId,Integer searchTask,Integer status,String doctorCode,String executeStartTime,String executeEndTime,Integer page,Integer pageSize){

        String sql = " select d.* wlyy_rehabilitation_plan_detail d " +
                "LEFT JOIN wlyy_service_item i on d.service_item_id=i.id " +
                "where d.execute_time>='"+executeStartTime+"' and d.execute_time<='"+executeEndTime+"' and d.plan_id='"+planId+"' " ;
        if(searchTask!=null){
            if(searchTask==1){
                sql+="and d.doctor='"+doctorCode+"' ";
            }else{
                sql+=" and i.type='"+searchTask+"' " ;
            }
        }
        if(status!=null){
            sql+= "and d.status="+status;
        }
        List<Map<String,Object>> rehabilitationDetailList = jdbcTemplate.queryForList(sql);
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,rehabilitationDetailList);
    }

    /**
     *
     * @param planDetailId
     * @return
     * @throws Exception
     */
    public MixEnvelop serviceItemList(String planDetailId) throws Exception{
        String sql = "select i.title,i.content,d.execute_time,d.hospital_name,d.status,d.type,d.expense,p.doctor " +
                " from wlyy_rehabilitation_plan_detail d " +
                " LEFT JOIN wlyy_service_item i on d.service_item_id=i.id " +
                " LEFT JOIN wlyy_patient_rehabilitation_plan p on d.plan_id=p.id " +
                " where d.id='"+planDetailId+"'";
        List<Map<String,Object>> serviceItemList = jdbcTemplate.queryForList(sql);
        Map<String,Object> resultMap = new HashMap<>();
        if(serviceItemList.size()>0){
            Map<String,Object> serviceItem = serviceItemList.get(0);
            resultMap.put("title",serviceItem.get("title"));//项目标题
            resultMap.put("shortExecuteTime",DateUtil.dateToStr((Date) serviceItem.get("execute_time"),DateUtil.HH_MM));//项目标题
            resultMap.put("content",serviceItem.get("content"));//项目内容
            resultMap.put("hospitalName",serviceItem.get("hospital_name"));//地点
            resultMap.put("executeTime",serviceItem.get("execute_time"));//执行时间
            resultMap.put("expense",serviceItem.get("expense"));//收费
            Integer status = Integer.valueOf(serviceItem.get("status").toString());//状态（0未完成，1已完成，2已预约）
            String statusName = "";
            switch (status){
                case 0:{statusName="未完成";break;}
                case 1:{statusName="已完成";break;}
                case 2:{statusName="已预约";break;}
            }
            resultMap.put("statusName",statusName);//状态
            //指导与汇报
            List<GuidanceMessageLogDO> messageList = guidanceMessageLogDao.findByPlanDetailId(planDetailId);
            List<Map<String,Object>> messageMapList = new ArrayList<>();
            for(GuidanceMessageLogDO one:messageList){
                Map<String,Object> map = new HashMap<>();
                map.put("doctorName",one.getDoctorName());
                map.put("adminTeamName",one.getAdminTeamName());
                map.put("content",one.getContent());
                map.put("createTime",DateUtil.dateToStr(one.getCreateTime(),"MM-dd HH:mm"));
                messageMapList.add(map);
            }
            resultMap.put("messageList",messageMapList);//指导与汇报记录
            return MixEnvelop.getSuccess(SpecialistMapping.api_success,resultMap);
        }
        return MixEnvelop.getError("没有该服务项详情信息！");
    }

    /**
     * 居民详情新增康复管理
     * @param patientCode
     * @param healthDoctor
     * @param healthDoctorName
     * @param generalDoctor
     * @param generalDoctorName
     * @return
     */
    public MixEnvelop patientRehabilitationDetail(String patientCode,String healthDoctor,String healthDoctorName,String generalDoctor,String generalDoctorName){

        Map<String,Object> resultMap = new HashMap<>();

        //个人基础信息（康复机构）
        String patientInfoSql = " SELECT DISTINCT hospital_name from wlyy_specialist.wlyy_rehabilitation_plan_detail d LEFT JOIN wlyy_specialist.wlyy_patient_rehabilitation_plan p on d.plan_id=p.id " +
                " where p.patient='"+patientCode+"' and p.status=1 ";
        List<Map<String,Object>> patientInfoList = jdbcTemplate.queryForList(patientInfoSql);
        Map<String,Object> patientInfo = new HashMap<>();
        String rehabilitationOrg = "";
        for(Map<String,Object> one:patientInfoList){
            rehabilitationOrg+=","+one.get("hospital_name");
        }
        patientInfo.put("rehabilitationOrg",StringUtils.isNotEmpty(rehabilitationOrg)?rehabilitationOrg.substring(1):"");
        resultMap.put("patientInfo",patientInfo);

        //服务医生
        //完成项目=全部的服务项目-未完成的服务项目
        List<Map<String,Object>> serviceDoctorList = new ArrayList<>();
        Map<String,Object> generalDoctorMap =  new HashMap<>();
        generalDoctorMap.put("type","全科医生");
        generalDoctorMap.put("doctorName",generalDoctorName);
        Integer generalUnfinishCount = rehabilitationDetailDao.unfinishItemByDoctor(generalDoctor,patientCode,1);
        Integer generalFinishCount = rehabilitationDetailDao.findItemByDoctor(generalDoctor,patientCode);
        Integer generalServiceCount = rehabilitationDetailDao.completeServiceByDoctor(generalDoctor,patientCode,1);
        generalDoctorMap.put("finishedItem",generalFinishCount-generalUnfinishCount);
        generalDoctorMap.put("serviceCount",generalServiceCount);
        serviceDoctorList.add(generalDoctorMap);

        Map<String,Object> healthDoctorMap =  new HashMap<>();
        healthDoctorMap.put("type","健管师");
        healthDoctorMap.put("doctorName",healthDoctorName);
        Integer healthUnfinishCount = rehabilitationDetailDao.unfinishItemByDoctor(healthDoctor,patientCode,1);
        Integer healthFinishCount = rehabilitationDetailDao.findItemByDoctor(healthDoctor,patientCode);
        Integer healthServiceCount = rehabilitationDetailDao.completeServiceByDoctor(healthDoctor,patientCode,1);
        healthDoctorMap.put("finishedItem",healthFinishCount-healthUnfinishCount);
        healthDoctorMap.put("serviceCount",healthServiceCount);
        serviceDoctorList.add(healthDoctorMap);

        String specialistRelationSql = "select * from wlyy_specialist.wlyy_specialist_patient_relation where patient='' and sign_status='1' and status='1'";
        List<Map<String,Object>> specialistRelationList = jdbcTemplate.queryForList(specialistRelationSql);
        for(Map<String,Object> one:specialistRelationList){
            String doctor = one.get("doctor").toString();
            String doctorName = one.get("doctorName").toString();
            Integer unfinishCount = rehabilitationDetailDao.unfinishItemByDoctor(doctor,patientCode,1);
            Integer finishCount = rehabilitationDetailDao.findItemByDoctor(doctor,patientCode);
            Integer serviceCount = rehabilitationDetailDao.completeServiceByDoctor(doctor,patientCode,1);
            Map<String,Object> map =  new HashMap<>();
            map.put("finishedItem",finishCount-unfinishCount);
            map.put("serviceCount",serviceCount);
            map.put("doctorName",doctorName);
            healthDoctorMap.put("type","专科医生");
            serviceDoctorList.add(map);
        }
        resultMap.put("serviceDoctorList",serviceDoctorList);

        //康复计划
        List<Map<String,Object>> planList = new ArrayList<>();
        List<PatientRehabilitationPlanDO> list = patientRehabilitationPlanDao.findbyPatient(patientCode);
        Integer planUnderway = 0;//进行中
        Integer planFinish = 0;//已完成
        for(PatientRehabilitationPlanDO one:list){
            if(one.getStatus()==1){
                planUnderway+=1;
            }else if(one.getStatus()==2){
                planFinish+=1;
            }
            Map<String,Object> map =  new HashMap<>();
            //安排类型
            String planTypeName = null;
            Integer planTypeTemp = one.getPlanType();
            switch (planTypeTemp){
                case 1:planTypeName="康复计划" ;break;
                case 2:planTypeName="（转）社区医院" ;break;
                case 3:planTypeName="（转）转家庭病床" ;break;
            }
            map.put("planTypeName",planTypeName);
            String statusName = "";
            Integer status = one.getStatus();
            switch (status){
                case 0:{statusName="已中止";break;}
                case 1:{statusName="进行中";break;}
                case 2:{statusName="已完成";break;}
            }
            map.put("planTypeName",planTypeName);//安排类型名称
            map.put("statusName",statusName);//状态名称
            //已完成
            Integer finishedCount = rehabilitationDetailDao.completenessCount(2,one.getId());
            map.put("finishedCount",finishedCount);//已完成
            //未完成
            Integer notstartedCount = rehabilitationDetailDao.completenessCount(1,one.getId());//未开始
            Integer underwayCount = rehabilitationDetailDao.completenessCount(1,one.getId());//进行中
            Integer unfinishedCount = notstartedCount+underwayCount;
            map.put("unfinishedCount",unfinishedCount);//未完成
            //完成度（已完成/（已完成+未完成））
            map.put("allCount",finishedCount+unfinishedCount);//总数
            List<RehabilitationDetailDO> detailList = rehabilitationDetailDao.getAllRehabilitationDetail(one.getId());
            Date executeTimeStart = detailList.get(0).getExecuteTime();
            Date executeTimeEnd = detailList.get(detailList.size()-1).getExecuteTime();
            String executeStart = DateUtil.dateToStr(executeTimeStart,"yyyy/MM/dd");
            String executeEnd = DateUtil.dateToStr(executeTimeEnd,"yyyy/MM/dd");
            map.put("time",executeStart+"-"+executeEnd);
            planList.add(map);
        }
        resultMap.put("planList",planList);
        //康复计划-已完成、进行中
        resultMap.put("planUnderway",planUnderway);//进行中
        resultMap.put("planFinish",planFinish);//已完成

        //近期康复相关记录
        String currentTime = DateUtil.getStringDate();
        String planDetailSql = " select d.*,i.content,i.title from wlyy_specialist.wlyy_rehabilitation_plan_detail d LEFT JOIN wlyy_hospital_service_item h on d.hospital_service_item_id=h.id" +
                " LEFT JOIN wlyy_service_item i on i.id=h.service_item_id LEFT JOIN wlyy_specialist.wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where p.patient='"+patientCode+"' and d.executeTime<='"+currentTime+"' order by executeTime desc";
        List<Map<String,Object>> planDetails = jdbcTemplate.queryForList(planDetailSql);
        List<Map<String,Object>> planDetailList = new ArrayList<>();
        for(Map<String,Object> one:planDetails){
            Date executeTimeDate = (Date)one.get("execute_time");
            String executeTime = DateUtil.dateToStr(executeTimeDate,"yyyy/MM/dd HH:mm");
            String content = one.get("content").toString();
            String title = one.get("title").toString();
            Integer status = (Integer)one.get("status");
            String statusName = "";
            switch (status){
                case 0:{statusName="未完成";break;}
                case 1:{statusName="已完成";break;}
                case 2:{statusName="已预约";break;}
            }
            String id = one.get("id").toString();
            Map<String,Object> map = new HashMap<>();
            map.put("id",id);//id
            map.put("executeTime",executeTime);//执行时间
            map.put("title",title);//项目标题
            map.put("content",content);//项目内容
            map.put("statusName",statusName);//状态名称
            planDetailList.add(map);
        }
        resultMap.put("planDetailList",planDetailList);//康复相关记录列表
        String planDetailCountSql = " select d.status as num from wlyy_specialist.wlyy_rehabilitation_plan_detail d LEFT JOIN wlyy_hospital_service_item h on d.hospital_service_item_id=h.id" +
                " LEFT JOIN wlyy_service_item i on i.id=h.service_item_id LEFT JOIN wlyy_specialist.wlyy_patient_rehabilitation_plan p on d.plan_id=p.id where p.patient='"+patientCode+"'";
        List<Map<String,Object>> planDetailList2 = jdbcTemplate.queryForList(planDetailCountSql);
        Integer planDetailFinish = 0;
        Integer planDetailUnfinish = 0;
        for(Map<String,Object> one:planDetailList2){

            Integer status = (Integer)one.get("status");
            if(status==1){
                planDetailFinish+=1;
            }else{
                planDetailUnfinish+=1;
            }
        }
        resultMap.put("planDetailFinish",planDetailFinish);//已完成
        resultMap.put("planDetailUnfinish",planDetailUnfinish);//未完成
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,resultMap);
    }
}
