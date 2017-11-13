package com.yihu.jw.business.base.service;

import com.yihu.jw.base.base.ModuleFunction;
import com.yihu.jw.business.base.dao.FunctionDao;
import com.yihu.jw.business.base.dao.ModuleFunctionDao;
import com.yihu.jw.base.base.Function;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.base.MFunction;
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
public class FunctionService extends BaseJpaService<Function, FunctionDao> {
    @Autowired
    private FunctionDao functionDao;
    @Autowired
    private ModuleFunctionDao moduleFunctionDao;
    @Autowired
    private ModuleFunService moduleFunService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Function createFunction(Function function) throws ApiException {
        if (StringUtils.isEmpty(function.getId())) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(function.getName())) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        Function functionTmp = functionDao.findByName(function.getName());
        if (functionTmp != null) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return functionDao.save(function);
    }

    @Transactional
    public Function updateFunction(Function function) {
        if (StringUtils.isEmpty(function.getId())) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(function.getName())) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(function.getId())) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        Function functionTmp = functionDao.findByNameExcludeId(function.getName(), function.getId());
        if (functionTmp != null) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return functionDao.save(function);
    }

    public Function findById(String id) {
        Function function = functionDao.findById(id);
        if (function == null) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        }
        return function;
    }

    @Transactional
    public void deleteFunction(String id) {
        Function function = functionDao.findById(id);
        if (function == null) {
            throw new ApiException(BaseRequestMapping.Function.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
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
        List<ModuleFunction> saasModuleList=new ArrayList<>();
        for(String functionId:functionCodeArr){
            ModuleFunction saasModule=new ModuleFunction();
            saasModule.setModuleId(moduleId);
            saasModule.setFunctionId(functionId);
            saasModuleList.add(saasModule);
        }
        moduleFunctionDao.save(saasModuleList);
    }

    public List<MFunction> getModuleFunctions(String saasId) {
        String sql=" select m.code,m.parent_code,m.name from base_function f,base_module_function mf where f.code=mf.function_id and f.status=1 and mf.module_id=?";
        return jdbcTemplate.queryForList(sql,MFunction.class,saasId);
    }

    /**
     * 根据code获得子节点,并判断是否子节点是否还有孙节点 (treegrid点击查询子节点 用到)
     * @param code
     * @return
     */
    public List<Function> getChildren(String code){
        List<Function> childrens = functionDao.getChildren(code);
        for(Function children:childrens){
            List<Function> children1 = functionDao.getChildren(children.getId());//判断子节点是否有孙节点
            children.setChildren(children1);
        }
        return childrens;
    }

    public List<Function> findAll(){
        return functionDao.findAll();
    }

    /**
     * key为code ,value为功能名称
     * @return
     */
    public Map<String,String> getName(){
        List<Function> functions = findAll();
        Map<String, String> map = new HashMap<>();
        if(null!=functions){
            for(Function function: functions){
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
    public Function getAllChildren(String id){
        Function function = functionDao.findById(id);
        List<Function> childrens = functionDao.getChildren(id);
        for(Function children:childrens){
            getAllChildren(children.getId());
        }
        function.setChildren(childrens);
        return function;
    }
}
