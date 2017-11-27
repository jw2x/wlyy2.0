package com.yihu.jw.business.base.service;

import com.yihu.jw.base.base.ModuleDO;
import com.yihu.jw.base.base.SaasModuleDO;
import com.yihu.jw.business.base.dao.ModuleDao;
import com.yihu.jw.business.base.dao.SaasModuleDao;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.base.MModule;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ModuleService extends BaseJpaService<ModuleDO, ModuleDao> {
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private SaasModuleDao saasModuleDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    public ModuleDO createModule(ModuleDO module) throws ApiException {
        if (StringUtils.isEmpty(module.getId())) {
            throw new ApiException(BaseRequestMapping.Module.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(module.getName())) {
            throw new ApiException(BaseRequestMapping.Module.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(module.getSaasId())) {
            throw new ApiException(BaseRequestMapping.Module.message_fail_saasid_is_null, ExceptionCode.common_error_params_code);
        }
        ModuleDO moduleTmp = moduleDao.findByName(module.getName());
        if (moduleTmp != null) {
            throw new ApiException(BaseRequestMapping.Module.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return moduleDao.save(module);
    }

    @Transactional
    public ModuleDO updateModule(ModuleDO module) {
        if (StringUtils.isEmpty(module.getName())) {
            throw new ApiException(BaseRequestMapping.Module.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(module.getId())) {
            throw new ApiException(BaseRequestMapping.Module.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        ModuleDO moduleTmp = moduleDao.findByNameExcludeId(module.getName(), module.getId());
        if (moduleTmp != null) {
            throw new ApiException(BaseRequestMapping.Module.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return moduleDao.save(module);
    }

    public ModuleDO findById(String Id) {
        ModuleDO module = moduleDao.findById(Id);
        if (module == null) {
            throw new ApiException(BaseRequestMapping.Module.message_fail_id_no_exist, ExceptionCode.common_error_params_code);
        }
        return module;
    }

    @Transactional
    public void deleteModule(String Id) {
        ModuleDO module = moduleDao.findById(Id);
        if (module == null) {
            throw new ApiException(BaseRequestMapping.Module.message_fail_id_no_exist, ExceptionCode.common_error_params_code);
        }
        module.setStatus(-1);
    }

    @Transactional
    public void assignModule(String saasCode, String moduleCodes) {
        //先删除原来已经分配好的模块
        saasModuleDao.deleteBySaasCode(saasCode);
        //分配新的模块
        String [] moduleCodeArr=moduleCodes.split(",");
        List<SaasModuleDO> saasModuleList=new ArrayList<>();
        for(String moduleCode:moduleCodeArr){
            SaasModuleDO saasModule=new SaasModuleDO();
            saasModule.setModuleId(moduleCode);
            saasModule.setSaasId(saasCode);
            saasModuleList.add(saasModule);
        }
        saasModuleDao.save(saasModuleList);
    }

    public List<MModule> getSaasModules(String saasCode) {
        String sql=" select m.code,m.parent_code,m.name from base_module m,base_saas_module sm where m.code=sm.module_id and m.status=1 and sm.saas_id=?";
       return jdbcTemplate.queryForList(sql,MModule.class,saasCode);
    }

    public List<ModuleDO> getChildren(String code){
        List<ModuleDO> childrens = moduleDao.getChildren(code);
        for(ModuleDO children:childrens){
            List<ModuleDO> children1 = moduleDao.getChildren(children.getId());//判断子节点是否有孙节点
            //没有children    state
            //“open”表示是子节点，“closed”表示为父节点；
            if (children1.size()>0){
                children.setState("closed");
            }else{
                children.setState("open");
            }
        }
        return childrens;
    }

    public List<ModuleDO> findAll(){
        return moduleDao.findAll();
    }

    /**
     * key为code ,value为模块名称
     * @return
     */
    public Map<String,String> getName(){
        List<ModuleDO> modules = findAll();
        Map<String, String> map = new HashMap<>();
        if(null!=modules){
            for(ModuleDO module: modules){
                map.put(module.getId(),module.getName());
            }
        }
        return map;
    }

}
