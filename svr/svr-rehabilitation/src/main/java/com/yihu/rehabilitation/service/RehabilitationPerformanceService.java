package com.yihu.rehabilitation.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rehabilitation.RehabilitationPerformanceDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.rehabilitation.RehabilitationPerformanceVO;
import com.yihu.jw.rm.rehabilitation.RehabilitationRequestMapping;
import com.yihu.rehabilitation.dao.RehabilitationPerformanceDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author humingfen on 2018/5/2.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RehabilitationPerformanceService extends BaseJpaService<RehabilitationPerformanceDO, RehabilitationPerformanceDao>  {
    @Autowired
    private RehabilitationPerformanceDao performanceDao;

    /**
     * 分页查找康复计划执行情况
     * @param page
     * @param size
     * @param patientId
     * @return
     * @throws ParseException
     */
    public Envelop<RehabilitationPerformanceVO> queryPerformancePage(Integer page, Integer size, String patientId) throws ParseException {
        String filters = "";
        String semicolon = "";
        if(StringUtils.isNotBlank(patientId)){
            filters = "createUser="+patientId+"";
            semicolon = ";";
        }
        String sorts = "-updateTime";
        //得到list数据
        List<RehabilitationPerformanceDO> list = search(null, filters, sorts, page, size);
        //获取总数
        long count = getCount(filters);
        //DO转VO
        List<RehabilitationPerformanceVO> PerformanceVOs = convertToModels(list,new ArrayList<>(list.size()), RehabilitationPerformanceVO.class);

        return Envelop.getSuccessListWithPage(RehabilitationRequestMapping.Common.message_success_find_functions,PerformanceVOs, page, size,count);
    }

    public RehabilitationPerformanceDO create(RehabilitationPerformanceDO performanceDO) {
        performanceDO.setSaasId(getCode());
        performanceDO.setStatus(0);
        performanceDO = performanceDao.save(performanceDO);
        return performanceDO;
    }

    public RehabilitationPerformanceDO findById(String id) {
        RehabilitationPerformanceDO performanceDO = performanceDao.findById(id);
        return performanceDO;
    }

    /**
     * 修改
     * @param performanceDO
     */
    public void update(RehabilitationPerformanceDO performanceDO){
        RehabilitationPerformanceDO oldPerformanceDO = performanceDao.findById(performanceDO.getId());
        oldPerformanceDO.setProgramId(performanceDO.getProgramId());
        oldPerformanceDO.setStartTime(performanceDO.getStartTime());
        oldPerformanceDO.setEndTime(performanceDO.getEndTime());
        oldPerformanceDO.setDescription(performanceDO.getDescription());
        performanceDao.save(oldPerformanceDO);
    }
}
