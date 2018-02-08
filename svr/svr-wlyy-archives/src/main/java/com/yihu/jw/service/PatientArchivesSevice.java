package com.yihu.jw.service;


import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.PatientArchivesDao;
import com.yihu.jw.dao.PatientArchivesInfoDao;
import com.yihu.jw.entity.archives.PatientArchives;
import com.yihu.jw.entity.archives.PatientArchivesInfo;
import com.yihu.jw.iot.company.IotCompanyDO;
import com.yihu.jw.restmodel.archives.PatientArchivesInfoVO;
import com.yihu.jw.restmodel.archives.PatientArchivesVO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyTypeVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import com.yihu.jw.rm.archives.PatientArchivesMapping;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Trick on 2018/2/7.
 */
@Service
@Transactional
public class PatientArchivesSevice extends BaseJpaService<PatientArchives,PatientArchivesDao> {

    @Autowired
    private PatientArchivesInfoDao patientArchivesInfoDao;
    @Autowired
    private PatientArchivesDao patientArchivesDao;

    /**
     * 分页查找健康档案
     * @param page
     * @param size
     * @param status
     * @param name
     * @return
     * @throws ParseException
     */
    public Envelop<PatientArchivesVO> queryPatientArchivesPage(Integer page, Integer size, String status,String cancelReseanType ,String name) throws ParseException {
        String filters = "";
        String semicolon = "";
        if(StringUtils.isNotBlank(name)){
            filters = "patientName?"+name+"";
            semicolon = ";";
        }
        if(StringUtils.isNotBlank(status)){
            filters += semicolon +"status="+status;
            semicolon = ";";
        }
        if(StringUtils.isBlank(cancelReseanType)){
            filters+= semicolon + "cancelReseanType="+cancelReseanType+"";
            semicolon = ";";
        }
        String sorts = "-createTime";
        //得到list数据
        List<PatientArchivesVO> list = search(null, filters, sorts, page, size);

        //获取总数
        long count = getCount(filters);

        //DO转VO
        List<PatientArchivesVO> patientArchivesVOs = convertToModelVOs(list,new ArrayList<>(list.size()));

        return Envelop.getSuccessListWithPage(PatientArchivesMapping.api_success,patientArchivesVOs, page, size,count);
    }

    /**
     * 获取档案详情
     * @return
     * @throws ParseException
     */
    public Envelop<PatientArchivesInfoVO> queryPatientArchivesInfoPage(String code) throws ParseException {

        List<PatientArchivesInfo> list = patientArchivesInfoDao.findByCodeOrderByLevel1(code);

        List<PatientArchivesInfoVO> patientArchivesinfoVOs = convertToModelVOs2(list,new ArrayList<>(list.size()));

        return Envelop.getSuccessList(PatientArchivesMapping.api_success,patientArchivesinfoVOs);
    }

    public Envelop<Boolean> createPatientArchives(PatientArchives patientArchives,List<PatientArchivesInfo> list){

        patientArchivesDao.save(patientArchives);
        patientArchivesInfoDao.save(list);
        Envelop envelop = new Envelop();
        envelop.setObj(true);
        return envelop;
    }

    public  Envelop<Boolean> updatePatientArchives(PatientArchives patientArchives,List<PatientArchivesInfo> list){

        patientArchivesDao.save(patientArchives);
        patientArchivesInfoDao.deleteByArchivesCode(patientArchives.getId());
        patientArchivesInfoDao.save(list);
        Envelop envelop = new Envelop();
        envelop.setObj(true);
        return envelop;
    }

    /**
     * 转换
     * @param sources
     * @param targets
     * @return
     */
    public List<PatientArchivesVO> convertToModelVOs(Collection sources, List<PatientArchivesVO> targets){
        sources.forEach(one -> {
            PatientArchivesVO target = new PatientArchivesVO();
            BeanUtils.copyProperties(one, target);
            targets.add(target);
        });
        return targets;
    }

    /**
     * 转换
     * @param sources
     * @param targets
     * @return
     */
    public List<PatientArchivesInfoVO> convertToModelVOs2(Collection sources, List<PatientArchivesInfoVO> targets){
        sources.forEach(one -> {
            PatientArchivesInfoVO target = new PatientArchivesInfoVO();
            BeanUtils.copyProperties(one, target);
            targets.add(target);
        });
        return targets;
    }
}
