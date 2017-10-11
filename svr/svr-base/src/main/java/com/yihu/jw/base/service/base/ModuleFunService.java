package com.yihu.jw.base.service.base;

import com.yihu.jw.base.dao.base.ModuleFunctionDao;
import com.yihu.jw.base.model.base.ModuleFunction;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.base.BaseContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ModuleFunService extends BaseJpaService<ModuleFunction, ModuleFunctionDao> {

    @Autowired
    private ModuleFunctionDao moduleFunctionDao;

    /**
     * 根据模块code,获取已存在的功能code
     * @param code
     * @return
     */
    public List<String> getExistFun(String code) {
        List<ModuleFunction> moduleFuns = moduleFunctionDao.findByModuleCode(code);
        List<String> list = new ArrayList<>();
        for(ModuleFunction moduleFun:moduleFuns){
            list.add(moduleFun.getFunctionId());
        }
        return list;
    }



    /**
     * 更改模块关联的功能
     * @param jsonData
     */
    public void changeFun(String jsonData) {
        JSONObject jsonObject =  new JSONObject(jsonData);
        if(!jsonObject.has("moduleCode")){
            throw new ApiException(BaseContants.ModuleFun.moduleCode_is_null, CommonContants.common_error_params_code);
            //filterStr+="saasId="+jsonObject.get("saasId")+";";
        }
        if(!jsonObject.has("funCodes")){
            throw new ApiException(BaseContants.ModuleFun.funCodes_is_null, CommonContants.common_error_params_code);
        }
        String moduleCode = jsonObject.get("moduleCode").toString();//模块code
        String funCodes = jsonObject.get("funCodes").toString();//功能code,多个code  ","  分隔

        List<String> existFun = getExistFun(moduleCode);

        List<String> newFunCodes = new ArrayList<String>(Arrays.asList(funCodes.split(",")));//目前的功能code

        List<String> oldFunCodes = new ArrayList<String>(existFun);//构建existFun的副本
        oldFunCodes.removeAll(newFunCodes);// 去除相同元素--->>需要删除的功能code

        newFunCodes.removeAll(existFun);// 去除相同元素---->>需要添加的功能code

        //先删除权限在添加...
        for(String delCode: oldFunCodes){
            moduleFunctionDao.delete(delCode, moduleCode);
        }
        for(String addCode:newFunCodes){
            ModuleFunction moduleFunction = new ModuleFunction();
            moduleFunction.setFunctionId(addCode);
            moduleFunction.setModuleId(moduleCode);
            moduleFunctionDao.save(moduleFunction);
        }

    }
}
