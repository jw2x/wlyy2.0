package com.yihu.jw.base.service.servicePackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.elasticsearch.ElasticSearchHelper;
import com.yihu.elasticsearch.ElasticSearchUtil;
import com.yihu.jw.base.dao.servicePackage.ServicePackageDao;
import com.yihu.jw.base.dao.servicePackage.ServicePackageDetailsDao;
import com.yihu.jw.base.dao.servicePackage.ServicePackageSignRecordDao;
import com.yihu.jw.entity.base.servicePackage.ServicePackageDO;
import com.yihu.jw.entity.base.servicePackage.ServicePackageDetailsDO;
import com.yihu.jw.entity.base.servicePackage.ServicePackageSignRecordDO;
import com.yihu.jw.restmodel.base.servicePackage.RehabilitationVO;
import com.yihu.jw.restmodel.base.servicePackage.ServicePackageLogVO;
import com.yihu.jw.restmodel.base.servicePackage.ServicePackageVO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yeshijie on 2018/8/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ServicePackageService extends BaseJpaService<ServicePackageDO, ServicePackageDao> {

    private Logger logger = LoggerFactory.getLogger(ServicePackageService.class);

    @Autowired
    private ServicePackageDao servicePackageDao;
    @Autowired
    private ServicePackageDetailsDao servicePackageDetailsDao;
    @Autowired
    private ServicePackageSignRecordDao servicePackageSignRecordDao;
    @Autowired
    private ElasticSearchUtil elasticSearchUtil;
    @Autowired
    private ElasticSearchHelper elastricSearchHelper;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${es.index.servicePackLog}")
    private String servicePackLongIndex;
    @Value("${es.type.servicePackLog}")
    private String servicePackLongType;

    /**
     * 新增康复计划
     */
    public ServicePackageDO addRehabilitation(RehabilitationVO rehabilitationVO,ServicePackageSignRecordDO signRecordDO) throws Exception{
        ServicePackageVO servicePackageVO = rehabilitationVO.getServicePackageVO();
        ServicePackageDO servicePackageDO = convertToModelDO(servicePackageVO);
        List<ServicePackageDetailsDO> detailsDOList = servicePackageDO.getDetailsDOList();
        //新增服务包主表
        servicePackageDO.setCreateTime(new Date());
        servicePackageDO.setDel(1);
        servicePackageDO.setLevel(ServicePackageDO.Level.doctor.getValue());
        servicePackageDO.setLevelCode(servicePackageDO.getCreater());
        servicePackageDO.setStatus(ServicePackageDO.Status.pass.getValue());
        servicePackageDO.setNum(detailsDOList.size());
        servicePackageDO.setType(ServicePackageDO.Type.rehabilitation.getValue());
        servicePackageDao.save(servicePackageDO);
        //新增服务项明细表
        detailsDOList.forEach(one->{
            one.setCreateTime(new Date());
            one.setSaasId(servicePackageDO.getSaasId());
            one.setServicePackageId(servicePackageDO.getId());
        });
        servicePackageDetailsDao.save(detailsDOList);
        //新增签约服务包记录表
        signRecordDO.setCreateTime(new Date());
        signRecordDO.setServicePackageId(servicePackageDO.getId());
        signRecordDO.setStatus(ServicePackageSignRecordDO.Status.create.getValue());
        servicePackageSignRecordDao.save(signRecordDO);
        //新增服务包日志
        ServicePackageLogVO logVO = new ServicePackageLogVO();
        logVO.setCreateTime(new Date());
        logVO.setFlag(ServicePackageLogVO.Flag.success.getValue());
        logVO.setFinish(0);
        logVO.setId(getCode());
        String message = "新增了一条康复计划";
        if(StringUtils.isNotBlank(signRecordDO.getSignDoctorName())){
            message = signRecordDO.getSignDoctorName()+message;
        }
        logVO.setMessage(message);
        logVO.setSaasId(servicePackageVO.getSaasId());
        logVO.setSevicePackageId(servicePackageDO.getId());
        logVO.setUserType(ServicePackageLogVO.UserType.doctor.getValue());
        logVO.setUserCode(signRecordDO.getSignDoctor());
        logVO.setUserName(signRecordDO.getSignDoctorName());
        elastricSearchHelper.save(servicePackLongIndex,servicePackLongType, objectMapper.writeValueAsString(logVO));

        return servicePackageDO;
    }

    /**
     * 获取完成度
     * @param servicePackageId
     * @return
     */
    public Integer getFinish(String servicePackageId) throws Exception{
        Integer finish = 0;
        String sql = "select finish from " + servicePackLongIndex + " where servicePackageId='" + servicePackageId + "' and sevicePackageDetailId is not null order by createTime desc limit 1";
        List<Map<String, Object>> returnList = elastricSearchHelper.executeSQL(sql);
        if(returnList!=null&&returnList.size()>0){
            finish = Integer.valueOf(returnList.get(0).get("finish").toString());
        }

        return finish;
    }

    /**
     * 保存康复计划日志
     * @param logVO
     * @return
     */
    public ServicePackageLogVO addRehabilitationLog(ServicePackageLogVO logVO) throws Exception{
        logVO.setId(getCode());
        int finish = 0;
        String sql = "select finish from " + servicePackLongIndex + " where servicePackageId='" + logVO.getSevicePackageId() + "' and sevicePackageDetailId is not null order by createTime desc limit 1";
        List<Map<String, Object>> returnList = elastricSearchHelper.executeSQL(sql);
        if(returnList!=null&&returnList.size()>0){
            finish = Integer.valueOf(returnList.get(0).get("finish").toString());
            if(StringUtils.isNotBlank(logVO.getSevicePackageDetailId())){
                finish++;
            }
        }
        logVO.setCreateTime(new Date());
        logVO.setFinish(finish);
        if(logVO.getFlag()==null){
            logVO.setFlag(ServicePackageLogVO.Flag.success.getValue());
        }
        List<ServicePackageLogVO> logVOList = new ArrayList<>(1);
        logVOList.add(logVO);
        elastricSearchHelper.save(servicePackLongIndex,servicePackLongType,logVOList);
        return logVO;
    }


    /**
     * 单个转换
     * @return
     */
    public ServicePackageDO convertToModelDO(ServicePackageVO servicePackageVO){
        ServicePackageDO target = new ServicePackageDO();
        BeanUtils.copyProperties(servicePackageVO, target);

        List<ServicePackageDetailsDO> voList = convertToModels(servicePackageVO.getDetailsVOList(),new ArrayList<>(servicePackageVO.getDetailsVOList().size()),ServicePackageDetailsDO.class);
        target.setDetailsDOList(voList);
        return target;
    }
}
