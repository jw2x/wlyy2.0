package com.yihu.jw.base.service.module;

import com.yihu.jw.base.dao.module.InterfaceDao;
import com.yihu.jw.base.dao.module.InterfaceErrorCodeDao;
import com.yihu.jw.base.dao.module.InterfaceParamDao;
import com.yihu.jw.entity.base.module.InterfaceDO;
import com.yihu.jw.entity.base.module.InterfaceErrorCodeDO;
import com.yihu.jw.entity.base.module.InterfaceParamDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if(StringUtils.isNotBlank(interfaceDO.getId())){
            interfaceParamDao.deleteByInterfaceId(interfaceDO.getId());
            interfaceErrorCodeDao.deleteByInterfaceId(interfaceDO.getId());
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
