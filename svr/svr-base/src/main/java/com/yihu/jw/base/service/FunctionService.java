package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.FunctionDao;
import com.yihu.jw.base.model.Function;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class FunctionService extends BaseJpaService<Function, FunctionDao> {
    @Autowired
    private FunctionDao functionDao;

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
    }
}
