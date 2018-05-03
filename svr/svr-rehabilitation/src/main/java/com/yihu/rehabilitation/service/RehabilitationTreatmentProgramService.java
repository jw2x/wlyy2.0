package com.yihu.rehabilitation.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rehabilitation.RehabilitationTreatmentProgramDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.rehabilitation.RehabilitationTreatmentProgramVO;
import com.yihu.jw.rm.rehabilitation.RehabilitationRequestMapping;
import com.yihu.rehabilitation.dao.RehabilitationTreatmentProgramDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author humingfen on 2018/4/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RehabilitationTreatmentProgramService extends BaseJpaService<RehabilitationTreatmentProgramDO, RehabilitationTreatmentProgramDao> {

    @Autowired
    private RehabilitationTreatmentProgramDao treatmentProgramDao;

    /**
     * 分页查找健康档案
     * @param page
     * @param size
     * @param name
     * @return
     * @throws ParseException
     */
    public Envelop<RehabilitationTreatmentProgramVO> queryTreatmentPage(Integer page, Integer size, String name) throws ParseException {
        String filters = "";
        String semicolon = "";
        if(StringUtils.isNotBlank(name)){
            filters = "name?"+name+"";
            semicolon = ";";
        }
        String sorts = "-updateTime";
        //得到list数据
        List<RehabilitationTreatmentProgramDO> list = search(null, filters, sorts, page, size);
        //获取总数
        long count = getCount(filters);
        //DO转VO
        List<RehabilitationTreatmentProgramVO> treatmentProgramVOs = convertToModels(list,new ArrayList<>(list.size()), RehabilitationTreatmentProgramVO.class);

        return Envelop.getSuccessListWithPage(RehabilitationRequestMapping.Common.message_success_find_functions,treatmentProgramVOs, page, size,count);
    }

    public RehabilitationTreatmentProgramDO create(RehabilitationTreatmentProgramDO treatmentProgramDO) {
        treatmentProgramDO.setSaasId(getCode());
        treatmentProgramDO = treatmentProgramDao.save(treatmentProgramDO);
        return treatmentProgramDO;
    }

    public RehabilitationTreatmentProgramDO findById(String id) {
        RehabilitationTreatmentProgramDO treatmentProgramDO = treatmentProgramDao.findById(id);
        return treatmentProgramDO;
    }

    /**
     * 修改
     * @param treatmentProgramDO
     */
    public void update(RehabilitationTreatmentProgramDO treatmentProgramDO){
        RehabilitationTreatmentProgramDO oldTreatmentProgramDO = treatmentProgramDao.findById(treatmentProgramDO.getId());
        oldTreatmentProgramDO.setName(treatmentProgramDO.getName());
        oldTreatmentProgramDO.setFrequency(treatmentProgramDO.getFrequency());
        oldTreatmentProgramDO.setTimesDaily(treatmentProgramDO.getTimesDaily());
        oldTreatmentProgramDO.setDescription(treatmentProgramDO.getDescription());
        treatmentProgramDao.save(oldTreatmentProgramDO);
    }
}
