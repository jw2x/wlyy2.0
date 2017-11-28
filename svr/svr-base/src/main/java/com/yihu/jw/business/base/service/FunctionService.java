package com.yihu.jw.business.base.service;

import com.yihu.jw.base.base.ModuleFunctionDO;
import com.yihu.jw.business.base.dao.FunctionDao;
import com.yihu.jw.business.base.dao.ModuleFunctionDao;
import com.yihu.jw.base.base.FunctionDO;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.base.FunctionVO;
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
public class FunctionService extends BaseJpaService<FunctionDO, FunctionDao> {
    @Autowired
    private FunctionDao functionDao;
    @Autowired
    private ModuleFunctionDao moduleFunctionDao;
    @Autowired
    private ModuleFunService moduleFunService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public FunctionDO createFunction(FunctionDO function) throws ApiException {
        if (StringUtils.isEmpty(function.getId())) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(function.getName())) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        FunctionDO functionTmp = functionDao.findByName(function.getName());
        if (functionTmp != null) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return functionDao.save(function);
    }

    @Transactional
    public FunctionDO updateFunction(FunctionDO function) {
        if (StringUtils.isEmpty(function.getName())) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(function.getId())) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        FunctionDO functionTmp = functionDao.findByNameExcludeId(function.getName(), function.getId());
        if (functionTmp != null) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return functionDao.save(function);
    }

    public FunctionDO findById(String id) {
        FunctionDO function = functionDao.findById(id);
        if (function == null) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_id_no_exist, ExceptionCode.common_error_params_code);
        }
        return function;
    }

    @Transactional
    public void deleteFunction(String id) {
        FunctionDO function = functionDao.findById(id);
        if (function == null) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_id_no_exist, ExceptionCode.common_error_params_code);
        }
        function.setStatus(-1);
        functionDao.save(function);
    }
    @Transactional
    public void assignFunction(String moduleId, String functionIds) {
        //先删除原来已经分配好的功能
        moduleFunctionDao.deleteByModuleId(moduleId);
        //分配新的功能
        String [] functionCodeArr=functionIds.split(",");
        List<ModuleFunctionDO> saasModuleList=new ArrayList<>();
        for(String functionId:functionCodeArr){
            ModuleFunctionDO saasModule=new ModuleFunctionDO();
            saasModule.setModuleId(moduleId);
            saasModule.setFunctionId(functionId);
            saasModuleList.add(saasModule);
        }
        moduleFunctionDao.save(saasModuleList);
    }

    public List<FunctionVO> getModuleFunctions(String saasId) {
        String sql=" select m.code,m.parent_code,m.name from base_function f,base_module_function mf where f.code=mf.function_id and f.status=1 and mf.module_id=?";
        return jdbcTemplate.queryForList(sql,FunctionVO.class,saasId);
    }

    /**
     * 根据code获得子节点,并判断是否子节点是否还有孙节点 (treegrid点击查询子节点 用到)
     * @param code
     * @return
     */
    public List<FunctionDO> getChildren(String code){
        List<FunctionDO> childrens = functionDao.getChildren(code);
        for(FunctionDO children:childrens){
            List<FunctionDO> children1 = functionDao.getChildren(children.getId());//判断子节点是否有孙节点
            children.setChildren(children1);
        }
        return childrens;
    }

    public List<FunctionDO> findAll(){
        return functionDao.findAll();
    }

    /**
     * key为code ,value为功能名称
     * @return
     */
    public Map<String,String> getName(){
        List<FunctionDO> functions = findAll();
        Map<String, String> map = new HashMap<>();
        if(null!=functions){
            for(FunctionDO function: functions){
                map.put(function.getId(),function.getName());
            }
        }
        return map;
    }

    /**
     * 根据code获取所有子节点(包括孙节点,曾孙节点....)
     * @param id
     * @return
     */
    public FunctionDO getAllChildren(String id){
        FunctionDO function = functionDao.findById(id);
        List<FunctionDO> childrens = functionDao.getChildren(id);
        for(FunctionDO children:childrens){
            getAllChildren(children.getId());
        }
        function.setChildren(childrens);
        return function;
    }
}
