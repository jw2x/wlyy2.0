package com.yihu.jw.base.service.module;

import com.yihu.jw.base.dao.module.SaasInterfaceDao;
import com.yihu.jw.base.dao.module.SaasInterfaceErrorCodeDao;
import com.yihu.jw.base.dao.module.SaasInterfaceParamDao;
import com.yihu.jw.entity.base.module.*;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 租户接口
 * @author yeshijie on 2018/10/11.
 */
@Service
public class SaasInterfaceService extends BaseJpaService<SaasInterfaceDO, SaasInterfaceDao> {
    @Autowired
    private SaasInterfaceDao interfaceDao;
    @Autowired
    private SaasInterfaceParamDao interfaceParamDao;
    @Autowired
    private SaasInterfaceErrorCodeDao interfaceErrorCodeDao;

    /**
     * 新增接口
     * @param interfaceDO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SaasInterfaceDO addInterface(SaasInterfaceDO interfaceDO){

        if(StringUtils.isNotBlank(interfaceDO.getId())){
            interfaceParamDao.deleteBySaasInterfaceId(interfaceDO.getId());
            interfaceErrorCodeDao.deleteBySaasInterfaceId(interfaceDO.getId());
        }

        List<SaasInterfaceParamDO> entryParams = interfaceDO.getEntryParams();
        List<SaasInterfaceParamDO> outParams = interfaceDO.getOutParams();
        List<SaasInterfaceParamDO> commonEntryParams = interfaceDO.getCommonEntryParams();
        List<SaasInterfaceParamDO> commonOutParams = interfaceDO.getCommonOutParams();
        List<SaasInterfaceErrorCodeDO> errorCodes = interfaceDO.getErrorCodes();
        interfaceDao.save(interfaceDO);
        entryParams.forEach(interfaceParamDO -> {
            interfaceParamDO.setSaasInterfaceId(interfaceDO.getId());
            interfaceParamDO.setCommon(0);
            interfaceParamDO.setDel(1);
            interfaceParamDO.setSaasId(interfaceDO.getSaasId());
            interfaceParamDO.setType(InterfaceParamDO.Type.entry.getValue());
        });
        outParams.forEach(interfaceParamDO -> {
            interfaceParamDO.setSaasInterfaceId(interfaceDO.getId());
            interfaceParamDO.setSaasId(interfaceDO.getSaasId());
            interfaceParamDO.setCommon(0);
            interfaceParamDO.setDel(1);
            interfaceParamDO.setType(InterfaceParamDO.Type.out.getValue());
        });
        commonEntryParams.forEach(interfaceParamDO -> {
            interfaceParamDO.setSaasInterfaceId(interfaceDO.getId());
            interfaceParamDO.setSaasId(interfaceDO.getSaasId());
            interfaceParamDO.setCommon(1);
            interfaceParamDO.setDel(1);
            interfaceParamDO.setType(InterfaceParamDO.Type.entry.getValue());
        });
        commonOutParams.forEach(interfaceParamDO -> {
            interfaceParamDO.setSaasInterfaceId(interfaceDO.getId());
            interfaceParamDO.setSaasId(interfaceDO.getSaasId());
            interfaceParamDO.setCommon(1);
            interfaceParamDO.setDel(1);
            interfaceParamDO.setType(InterfaceParamDO.Type.out.getValue());
        });
        errorCodes.forEach(interfaceErrorCodeDO -> {
            interfaceErrorCodeDO.setSaasInterfaceId(interfaceDO.getId());
            interfaceErrorCodeDO.setSaasId(interfaceDO.getSaasId());
            interfaceErrorCodeDO.setDel(1);
        });
        interfaceParamDao.save(entryParams);
        interfaceParamDao.save(outParams);
        interfaceParamDao.save(commonOutParams);
        interfaceParamDao.save(commonEntryParams);
        interfaceErrorCodeDao.save(errorCodes);

        return interfaceDO;
    }

    /**
     * 返回接口
     * @param id
     * @return
     */
    public SaasInterfaceDO findById(String id){
        SaasInterfaceDO interfaceDO = interfaceDao.findOne(id);

        List<SaasInterfaceParamDO> paramDOList = interfaceParamDao.findBySaasInterfaceId(id);
        List<SaasInterfaceParamDO> entryParams = paramDOList.stream()
                .filter(interfaceParamDO -> (InterfaceParamDO.Type.entry.getValue().equals(interfaceParamDO.getType()))
                        &&InterfaceParamDO.Common.no.getValue().equals(interfaceParamDO.getCommon()))
                .collect(Collectors.toList());
        List<SaasInterfaceParamDO> outParams = paramDOList.stream()
                .filter(interfaceParamDO -> (InterfaceParamDO.Type.out.getValue().equals(interfaceParamDO.getType()))
                        &&InterfaceParamDO.Common.no.getValue().equals(interfaceParamDO.getCommon()))
                .collect(Collectors.toList());
        List<SaasInterfaceParamDO> commonEntryParams = paramDOList.stream()
                .filter(interfaceParamDO -> (InterfaceParamDO.Type.entry.getValue().equals(interfaceParamDO.getType()))
                        &&InterfaceParamDO.Common.yes.getValue().equals(interfaceParamDO.getCommon()))
                .collect(Collectors.toList());
        List<SaasInterfaceParamDO> commonOutParams = paramDOList.stream()
                .filter(interfaceParamDO -> (InterfaceParamDO.Type.out.getValue().equals(interfaceParamDO.getType()))
                        &&InterfaceParamDO.Common.yes.getValue().equals(interfaceParamDO.getCommon()))
                .collect(Collectors.toList());

        List<SaasInterfaceErrorCodeDO> errorCodeDOList = interfaceErrorCodeDao.findBySaasInterfaceId(id);
        interfaceDO.setErrorCodes(errorCodeDOList);
        interfaceDO.setEntryParams(entryParams);
        interfaceDO.setCommonEntryParams(commonEntryParams);
        interfaceDO.setCommonOutParams(commonOutParams);
        interfaceDO.setOutParams(outParams);
        return interfaceDO;
    }

    /**
     * 设置生效和失效
     * @param id
     * @param status
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String id,Integer status){
        interfaceDao.updateStatus(id,status);
    }
}
