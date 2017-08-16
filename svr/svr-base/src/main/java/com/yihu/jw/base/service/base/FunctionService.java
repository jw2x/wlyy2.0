package com.yihu.jw.base.service.base;

import com.yihu.jw.base.dao.base.FunctionDao;
import com.yihu.jw.base.dao.base.ModuleFunctionDao;
import com.yihu.jw.base.model.base.Function;
import com.yihu.jw.base.model.base.ModuleFunction;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.base.BaseContants;
import com.yihu.jw.restmodel.base.base.MFunction;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
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
        if (StringUtils.isEmpty(function.getCode())) {
            throw new ApiException(BaseContants.Function.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(function.getName())) {
            throw new ApiException(BaseContants.Function.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        Function functionTmp = functionDao.findByName(function.getName());
        if (functionTmp != null) {
            throw new ApiException(BaseContants.Function.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return functionDao.save(function);
    }

    @Transactional
    public Function updateFunction(Function function) {
        if (StringUtils.isEmpty(function.getCode())) {
            throw new ApiException(BaseContants.Function.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(function.getName())) {
            throw new ApiException(BaseContants.Function.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(function.getId())) {
            throw new ApiException(BaseContants.Function.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        Function functionTmp = functionDao.findByNameExcludeCode(function.getName(), function.getCode());
        if (functionTmp != null) {
            throw new ApiException(BaseContants.Function.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return functionDao.save(function);
    }

    public Function findByCode(String code) {
        Function function = functionDao.findByCode(code);
        if (function == null) {
            throw new ApiException(BaseContants.Function.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return function;
    }

    @Transactional
    public void deleteFunction(String code) {
        Function function = functionDao.findByCode(code);
        if (function == null) {
            throw new ApiException(BaseContants.Function.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        function.setStatus(-1);
        functionDao.save(function);
    }
    @Transactional
    public void assignFunction(String moduleCode, String functionCodes) {
        //先删除原来已经分配好的功能
        moduleFunctionDao.deleteByModuleCode(moduleCode);
        //分配新的功能
        String [] functionCodeArr=functionCodes.split(",");
        List<ModuleFunction> saasModuleList=new ArrayList<>();
        for(String functionCode:functionCodeArr){
            ModuleFunction saasModule=new ModuleFunction();
            saasModule.setModuleId(moduleCode);
            saasModule.setFunctionId(functionCode);
            saasModuleList.add(saasModule);
        }
        moduleFunctionDao.save(saasModuleList);
    }

    public List<MFunction> getModuleFunctions(String saasCode) {
        String sql=" select m.code,m.parent_code,m.name from base_function f,base_module_function mf where f.code=mf.function_id and f.status=1 and mf.module_id=?";
        return jdbcTemplate.queryForList(sql,MFunction.class,saasCode);
    }

    /**
     * 根据code获得子节点,并判断是否子节点是否还有孙节点 (treegrid点击查询子节点 用到)
     * @param code
     * @return
     */
    public List<Function> getChildren(String code){
        List<Function> childrens = functionDao.getChildren(code);
        for(Function children:childrens){
            List<Function> children1 = functionDao.getChildren(children.getCode());//判断子节点是否有孙节点
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
                map.put(function.getCode(),function.getName());
            }
        }
        return map;
    }

    /**
     * 根据code获取所有子节点(包括孙节点,曾孙节点....)
     * @param code
     * @return
     */
    public Function getAllChildren(String code){
        Function function = functionDao.findByCode(code);
        List<Function> childrens = functionDao.getChildren(code);
        for(Function children:childrens){
            getAllChildren(children.getCode());
        }
        function.setChildren(childrens);
        return function;
    }
}
