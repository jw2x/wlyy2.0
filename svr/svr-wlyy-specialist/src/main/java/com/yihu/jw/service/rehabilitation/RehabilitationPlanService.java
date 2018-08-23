package com.yihu.jw.service.rehabilitation;

import com.yihu.jw.dao.rehabilitation.PatientRehabilitationPlanDao;
import com.yihu.jw.dao.rehabilitation.RehabilitationDetailDao;
import com.yihu.jw.dao.rehabilitation.RehabilitationPlanTemplateDao;
import com.yihu.jw.dao.rehabilitation.RehabilitationTemplateDetailDao;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationDetailDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationPlanTemplateDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationTemplateDetailDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by humingfen on 2018/8/17.
 */
@Service
@Transactional
public class RehabilitationPlanService {

    @Autowired
    private RehabilitationPlanTemplateDao templateDao;
    @Autowired
    private RehabilitationTemplateDetailDao templateDetailDao;
    @Autowired
    private PatientRehabilitationPlanDao patientRehabilitationPlanDao;
    @Autowired
    private RehabilitationDetailDao rehabilitationDetailDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MixEnvelop<String, String> createRehabilitationTemplate(RehabilitationPlanTemplateDO templateDO) {
        templateDO.setCreateTime(new Date());
        templateDO.setDel(1);
        templateDO = templateDao.save(templateDO);
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,templateDO.getId());
    }

    public MixEnvelop<Boolean, Boolean> createRehabilitationTemplateDetail(List<RehabilitationTemplateDetailDO> details) {
        for(RehabilitationTemplateDetailDO detail : details){
            detail.setCreateTime(new Date());
            templateDetailDao.save(detail);
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public MixEnvelop<Boolean,Boolean> updateRehabilitationTemplateDetail(List<RehabilitationTemplateDetailDO> details) {
        String templateId = details.get(0).getTemplateId();
        if(templateId != null && templateId.length() > 0){
            templateDetailDao.deleteByTemplateId(templateId);
        }
        for(RehabilitationTemplateDetailDO detail : details){
            detail.setCreateTime(new Date());
            templateDetailDao.save(detail);
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public MixEnvelop<RehabilitationPlanTemplateDO, RehabilitationPlanTemplateDO> findRehabilitationPlanTemplate(Integer adminTeamCode, Integer page, Integer size) {

        if(page != null && size != null){
            String sql = "select * from wlyy_rehabilitation_plan_template t where t.admin_team_code = '" + adminTeamCode + "' ORDER BY t.create_time DESC LIMIT "+(page-1)*size+","+size;
            List<RehabilitationPlanTemplateDO> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper(RehabilitationPlanTemplateDO.class));
            String countSql = "select count(1) from wlyy_rehabilitation_plan_template t where t.admin_team_code = '" + adminTeamCode + "'";
            Long count = jdbcTemplate.queryForObject(countSql, Long.class);
            return MixEnvelop.getSuccessListWithPage(SpecialistMapping.api_success,list,page,size,count);
        }else {
            List<RehabilitationPlanTemplateDO> list = templateDao.findByAdminTeamCode(adminTeamCode);
            return MixEnvelop.getSuccessList(SpecialistMapping.api_success,list, list.size());
        }
    }

    public MixEnvelop<RehabilitationTemplateDetailDO, RehabilitationTemplateDetailDO> findTemplateDetailByTemplateId(String templateId) {
        List<RehabilitationTemplateDetailDO> list = templateDetailDao.findTemplateDetailByTemplateId(templateId);
        return MixEnvelop.getSuccessList(SpecialistMapping.api_success,list, list.size());
    }

    public PatientRehabilitationPlanDO createPatientRehabilitationPlan(PatientRehabilitationPlanDO planDO) {
        planDO.setCreateTime(new Date());
        planDO.setStatus(1);
        return patientRehabilitationPlanDao.save(planDO);
    }

    public List<RehabilitationDetailDO> createRehabilitationDetail(List<RehabilitationDetailDO> details, String planId) {
        for(RehabilitationDetailDO detail : details) {
            detail.setPlanId(planId);
            detail.setStatus(0);
        }
        return (List<RehabilitationDetailDO>)rehabilitationDetailDao.save(details);
    }
}
