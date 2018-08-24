package com.yihu.jw.service;/**
 * Created by nature of king on 2018/8/17.
 */

import com.yihu.jw.dao.SpecialistServiceItemOperateLogDao;
import com.yihu.jw.entity.specialist.SpecialistServiceItemOperateLogDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangzhinan
 * @create 2018-08-17 8:48
 * @desc 服务项目操作记录
 **/
@Service
@Transactional
public class SpecialistServiceItemOperateLogService {

    @Autowired
    private SpecialistServiceItemOperateLogDao specialistServiceItemOperateLogDao;

    /**
     *
     * @param specialistServiceItemOperateLogDO
     * @return
     */
    public MixEnvelop<Boolean,Boolean> insert(SpecialistServiceItemOperateLogDO specialistServiceItemOperateLogDO){
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        specialistServiceItemOperateLogDao.save(specialistServiceItemOperateLogDO);
        envelop.setObj(true);
        return envelop;
    }
}
