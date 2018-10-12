package com.yihu.jw.base.service.module;

import com.yihu.jw.base.dao.module.*;
import com.yihu.jw.entity.base.module.*;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口
 * @author yeshijie on 2018/9/28.
 */
@Service
public class InterfaceService extends BaseJpaService<InterfaceDO, InterfaceDao> {

    @Autowired
    private InterfaceDao interfaceDao;
    @Autowired
    private InterfaceParamDao interfaceParamDao;
    @Autowired
    private InterfaceErrorCodeDao interfaceErrorCodeDao;
    @Autowired
    private SaasInterfaceDao saasInterfaceDao;
    @Autowired
    private SaasModuleDao saasModuleDao;
    @Autowired
    private SaasInterfaceParamDao saasInterfaceParamDao;
    @Autowired
    private SaasInterfaceErrorCodeDao saasInterfaceErrorCodeDao;

    /**
     * 返回接口
     * @param id
     * @return
     */
    public InterfaceDO findById(String id){
        InterfaceDO interfaceDO = interfaceDao.findOne(id);

        List<InterfaceParamDO> paramDOList = interfaceParamDao.findByInterfaceId(id);
        List<InterfaceParamDO> entryParams = paramDOList.stream()
                .filter(interfaceParamDO -> InterfaceParamDO.Type.entry.getValue().equals(interfaceParamDO.getType()))
                .collect(Collectors.toList());
        List<InterfaceParamDO> outParams = paramDOList.stream()
                .filter(interfaceParamDO -> InterfaceParamDO.Type.out.getValue().equals(interfaceParamDO.getType()))
                .collect(Collectors.toList());

        List<InterfaceErrorCodeDO> errorCodeDOList = interfaceErrorCodeDao.findByInterfaceId(id);
        interfaceDO.setErrorCodes(errorCodeDOList);
        interfaceDO.setEntryParams(entryParams);
        interfaceDO.setOutParams(outParams);
        return interfaceDO;
    }

    /**
     * 新增接口
     * @param interfaceDO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public InterfaceDO addInterface(InterfaceDO interfaceDO){

        boolean isNew = true;
        if(StringUtils.isNotBlank(interfaceDO.getId())){
            interfaceParamDao.deleteByInterfaceId(interfaceDO.getId());
            interfaceErrorCodeDao.deleteByInterfaceId(interfaceDO.getId());
            isNew = false;
            InterfaceDO oldInterfaceDO = interfaceDao.findOne(interfaceDO.getId());

            //判断是否修改了状态
            if(!oldInterfaceDO.getStatus().equals(interfaceDO.getStatus())){
                //修改租户的接口状态
                saasInterfaceDao.updateStatusByInterfaceId(interfaceDO.getId(),interfaceDO.getStatus());
            }
        }

        List<InterfaceParamDO> entryParams = interfaceDO.getEntryParams();
        List<InterfaceParamDO> outParams = interfaceDO.getOutParams();
        List<InterfaceErrorCodeDO> errorCodes = interfaceDO.getErrorCodes();
        interfaceDao.save(interfaceDO);
        entryParams.forEach(interfaceParamDO -> {
            interfaceParamDO.setInterfaceId(interfaceDO.getId());
        });
        outParams.forEach(interfaceParamDO -> {
            interfaceParamDO.setInterfaceId(interfaceDO.getId());
        });
        errorCodes.forEach(interfaceErrorCodeDO -> {
            interfaceErrorCodeDO.setInterfaceId(interfaceDO.getId());
        });
        interfaceParamDao.save(entryParams);
        interfaceParamDao.save(outParams);
        interfaceErrorCodeDao.save(errorCodes);

        //为租户新增接口
        if (isNew){
            List<SaasModuleDO> saasModuleDOList = saasModuleDao.findByModuleId(interfaceDO.getModuleId());
            saasModuleDOList.forEach(saasModuleDO -> {

                SaasInterfaceDO saasInterfaceDO = new SaasInterfaceDO();
                saasInterfaceDO.setInterfaceId(interfaceDO.getId());
                saasInterfaceDO.setUrl(interfaceDO.getUrl());
                saasInterfaceDO.setStatus(interfaceDO.getStatus());
                saasInterfaceDO.setCheckLevel(interfaceDO.getCheckLevel());
                saasInterfaceDO.setMethodName(interfaceDO.getMethodName());
                saasInterfaceDO.setModuleId(interfaceDO.getModuleId());
                saasInterfaceDO.setName(interfaceDO.getName());
                saasInterfaceDO.setModuleName(interfaceDO.getModuleName());
                saasInterfaceDO.setOpenLevel(interfaceDO.getOpenLevel());
                saasInterfaceDO.setProtocolType(interfaceDO.getProtocolType());
                saasInterfaceDO.setRemark(interfaceDO.getRemark());
                saasInterfaceDO.setSaasId(saasModuleDO.getSaasId());

                List<SaasInterfaceParamDO> saasEntryParams = new ArrayList<>(16);
                List<SaasInterfaceParamDO> saasOutParams = new ArrayList<>(16);
                List<SaasInterfaceErrorCodeDO> saasErrorCodes = new ArrayList<>(16);
                saasInterfaceDao.save(saasInterfaceDO);
                entryParams.forEach(interfaceParamDO -> {
                    SaasInterfaceParamDO saasInterfaceParamDO = addSaasInterfaceParamDO(saasInterfaceDO,interfaceParamDO);
                    saasEntryParams.add(saasInterfaceParamDO);
                });
                outParams.forEach(interfaceParamDO -> {
                    SaasInterfaceParamDO saasInterfaceParamDO = addSaasInterfaceParamDO(saasInterfaceDO,interfaceParamDO);
                    saasOutParams.add(saasInterfaceParamDO);
                });
                errorCodes.forEach(interfaceErrorCodeDO -> {
                    SaasInterfaceErrorCodeDO saasInterfaceErrorCodeDO = new SaasInterfaceErrorCodeDO();
                    saasInterfaceErrorCodeDO.setSort(interfaceErrorCodeDO.getSort());
                    saasInterfaceErrorCodeDO.setDel(interfaceErrorCodeDO.getDel());
                    saasInterfaceErrorCodeDO.setSaasInterfaceId(saasInterfaceDO.getId());
                    saasInterfaceErrorCodeDO.setDescription(interfaceErrorCodeDO.getDescription());
                    saasInterfaceErrorCodeDO.setName(interfaceErrorCodeDO.getName());
                    saasInterfaceErrorCodeDO.setSaasId(saasInterfaceDO.getSaasId());
                    saasInterfaceErrorCodeDO.setSolution(interfaceErrorCodeDO.getSolution());
                    saasErrorCodes.add(saasInterfaceErrorCodeDO);
                });
                saasInterfaceParamDao.save(saasEntryParams);
                saasInterfaceParamDao.save(saasOutParams);
                saasInterfaceErrorCodeDao.save(saasErrorCodes);
            });
        }

        return interfaceDO;
    }

    /**
     * 新增租户接口参数
     * @param saasInterfaceDO
     * @param interfaceParamDO
     * @return
     */
    private SaasInterfaceParamDO addSaasInterfaceParamDO(SaasInterfaceDO saasInterfaceDO,InterfaceParamDO interfaceParamDO){
        SaasInterfaceParamDO saasInterfaceParamDO = new SaasInterfaceParamDO();
        saasInterfaceParamDO.setSaasId(saasInterfaceDO.getSaasId());
        saasInterfaceParamDO.setDel(interfaceParamDO.getDel());
        saasInterfaceParamDO.setName(interfaceParamDO.getName());
        saasInterfaceParamDO.setDataType(interfaceParamDO.getDataType());
        saasInterfaceParamDO.setDescription(interfaceParamDO.getDescription());
        saasInterfaceParamDO.setExample(interfaceParamDO.getExample());
        saasInterfaceParamDO.setIsRequire(interfaceParamDO.getIsRequire());
        saasInterfaceParamDO.setMaxLength(interfaceParamDO.getMaxLength());
        saasInterfaceParamDO.setParamType(interfaceParamDO.getParamType());
        saasInterfaceParamDO.setSaasInterfaceId(saasInterfaceDO.getId());
        saasInterfaceParamDO.setSort(interfaceParamDO.getSort());
        saasInterfaceParamDO.setType(interfaceParamDO.getType());
        return saasInterfaceParamDO;
    }

    /**
     * 设置生效和失效
     * @param id
     * @param status
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String id,Integer status){
        interfaceDao.updateStatus(id,status);
        //修改租户的接口状态
        saasInterfaceDao.updateStatusByInterfaceId(id,status);
    }
}
