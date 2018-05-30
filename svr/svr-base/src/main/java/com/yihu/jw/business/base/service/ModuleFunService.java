package com.yihu.jw.business.base.service;

import com.yihu.jw.base.base.ModuleFunctionDO;
import com.yihu.jw.business.base.dao.ModuleFunctionDao;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.json.JSONException;
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
public class ModuleFunService extends BaseJpaService<ModuleFunctionDO, ModuleFunctionDao> {

    @Autowired
    private ModuleFunctionDao moduleFunctionDao;

    /**
     * 根据模块code,获取已存在的功能code
     * @param id
     * @return
     */
    public List<String> getExistFun(String id) {
        List<ModuleFunctionDO> moduleFuns = moduleFunctionDao.findByModuleId(id);
        List<String> list = new ArrayList<>();
        for(ModuleFunctionDO moduleFun:moduleFuns){
            list.add(moduleFun.getFunctionId());
        }
        return list;
    }



    /**
     * 更改模块关联的功能
     * @param jsonData
     */
    public void changeFun(String jsonData) throws JSONException {
        JSONObject jsonObject =  new JSONObject(jsonData);
        if(!jsonObject.has("moduleCode")){
            throw new ApiException(BaseRequestMapping.ModuleFun.moduleId_is_null, ExceptionCode.common_error_params_code);
            //filterStr+="saasId="+jsonObject.get("saasId")+";";
        }
        if(!jsonObject.has("funCodes")){
            throw new ApiException(BaseRequestMapping.ModuleFun.funIds_is_null, ExceptionCode.common_error_params_code);
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
            ModuleFunctionDO moduleFunction = new ModuleFunctionDO();
            moduleFunction.setFunctionId(addCode);
            moduleFunction.setModuleId(moduleCode);
            moduleFunctionDao.save(moduleFunction);
        }

    }
}
