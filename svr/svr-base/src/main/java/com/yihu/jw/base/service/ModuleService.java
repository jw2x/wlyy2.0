package com.yihu.jw.base.service;

import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.jw.entity.base.saas.SaasModuleDO;
import com.yihu.jw.base.dao.ModuleDao;
import com.yihu.jw.business.base.dao.SaasModuleDao;
import com.yihu.jw.restmodel.base.base.ModuleVO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public List<ModuleVO> getSaasModules(String saasCode) {
        String sql=" select m.code,m.parent_code,m.name from base_module m,base_saas_module sm where m.code=sm.module_id and m.status=1 and sm.saas_id=?";
       return jdbcTemplate.queryForList(sql,ModuleVO.class,saasCode);
    }

}
