package com.yihu.rehabilitation.service;

import com.yihu.jw.rehabilitation.RehabilitationPlanningDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.rehabilitation.RehabilitationPlanningVO;
import com.yihu.jw.rm.rehabilitation.RehabilitationRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.rehabilitation.dao.RehabilitationPlanningDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RehabilitationPlanningService extends BaseJpaService<RehabilitationPlanningDO, RehabilitationPlanningDao> {
    @Autowired
    private RehabilitationPlanningDao planningDao;

    /**
     * 分页查找康复计划
     * @param page
     * @param size
     * @param patientId
     * @return
     * @throws ParseException
     */
    public Envelop<RehabilitationPlanningVO> queryPlanningPage(Integer page, Integer size, String patientId, String programId) throws ParseException {
        String filters = "status=0;";
        String semicolon = "";
        if(StringUtils.isNotBlank(patientId)){
            filters = "patientId="+patientId+"";
            semicolon = ";";
        }
        if(StringUtils.isNotBlank(programId)){
            filters += semicolon + "programId="+programId+"";
            semicolon = ";";
        }
        String sorts = "-updateTime";
        //得到list数据
        List<RehabilitationPlanningDO> list = search(null, filters, sorts, page, size);

        //获取总数
        long count = getCount(filters);
        //DO转VO
        List<RehabilitationPlanningVO> planningVOs = convertToModels(list,new ArrayList<>(list.size()), RehabilitationPlanningVO.class);

        return Envelop.getSuccessListWithPage(RehabilitationRequestMapping.Common.message_success_find_functions,planningVOs, page, size,count);
    }

    public RehabilitationPlanningDO create(RehabilitationPlanningDO planningDO) {
        planningDO.setSaasId(getCode());
        planningDO.setStatus(0);
        planningDO = planningDao.save(planningDO);
        return planningDO;
    }

    public RehabilitationPlanningDO findById(String id) {
        RehabilitationPlanningDO planningDO = planningDao.findById(id);
        return planningDO;
    }

    /**
     * 修改
     * @param planningDO
     */
    public void update(RehabilitationPlanningDO planningDO){
        RehabilitationPlanningDO oldPlanningDO = planningDao.findById(planningDO.getId());
        oldPlanningDO.setPatientId(planningDO.getPatientId());
        oldPlanningDO.setProgramId(planningDO.getProgramId());
        oldPlanningDO.setRecheckTime(planningDO.getRecheckTime());
        oldPlanningDO.setDescription(planningDO.getDescription());
        planningDao.save(oldPlanningDO);
    }
}
