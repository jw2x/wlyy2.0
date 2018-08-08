package com.yihu.jw.business.user.service;

import com.yihu.jw.base.user.BaseEmployDO;
import com.yihu.jw.base.user.BaseRoleDO;
import com.yihu.jw.business.user.dao.EmployDao;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.jw.restmodel.common.base.BaseEnvelopStatus;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.servlet.Servlet;
import java.util.List;

/**
 * Created by chenweida on 2017/5/11.
 * 用户信息功能
 */

@Service
@EnableCaching
@CacheConfig(cacheNames = "employ")
public class EmployService extends BaseJpaService<BaseEmployDO,EmployDao> {
    @Autowired
    private EmployDao employDao;

    /**
     * 创建用户
     * @param employeeDO
     * @return
     */
    @Cacheable(value = "employ#600#300")
    @Transactional
    public BaseEmployDO createBaseEmployDO(BaseEmployDO employeeDO){
        if (StringUtils.isEmpty(employeeDO.getId())) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(employeeDO.getSaasId())) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_saasid_is_null, ExceptionCode.common_error_params_code);
        }
        return this.employDao.save(employeeDO);
    }

    /**
     * 更新用户
     * @param employeeDO
     * @return
     */
    @CachePut(value = "employ#600#300",key = "#employeeDO.id")
    @Transactional
    public BaseEmployDO updateBaseEmployDO(BaseEmployDO employeeDO){
        if (StringUtils.isEmpty(employeeDO.getId())) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(employeeDO.getSaasId())) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_saasid_is_null, ExceptionCode.common_error_params_code);
        }
        return this.employDao.save(employeeDO);
    }

    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
//    @Cacheable(value = "employ#600#300",key = "#employeeDO.id")
    public BaseEmployDO findById(String id){
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_id_is_null,ExceptionCode.common_error_params_code);
        }
        BaseEmployDO BaseEmployDO = this.employDao.findOne(id);
        if (null == BaseEmployDO) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_id_no_exist,ExceptionCode.common_error_params_code);
        }
        return BaseEmployDO;
    }


    /**
     * 根据手机号和saasId查询用户，一个用户可能注册多个saas平台，所以根据手机号查找要限定saasId
     * @param phone
     * @param saasId
     * @return
     */
    public BaseEmployDO findByPhoneAndSaasId(String phone,String saasId) throws ApiException{
        BaseEmployDO BaseEmployDO = this.employDao.findByPhoneAndSaasId(phone,saasId);
        if (null == BaseEmployDO) {
            throw new ApiException(BaseEnvelopStatus.status_10100.getName(), BaseEnvelopStatus.status_10100.getCode());
        }
        return BaseEmployDO;
    }

    /**
     * 查询某saasId平台下的所有用户
     * @param saasId
     * @return
     */
    @Cacheable(value = "employ#600#300",key = "#employeeDO.id")
    public List<BaseEmployDO> findAllBySaasId(String saasId){
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_param_saasid_is_null,ExceptionCode.common_error_params_code);
        }
        List<BaseEmployDO> list = this.employDao.findAllBySaasId(saasId);
        if (null == list || list.size() == 0) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_saasid_no_exist,ExceptionCode.common_error_params_code);
        }
        return list;
    }

    /**
     * 查询name模糊查询某saasId平台下的所有用户
     * @param saasId
     * @return
     */
    @Cacheable(value = "employ#600#300",key = "#employeeDO.id")
    public List<BaseEmployDO> findAllByNameAndSaasId(String name,String saasId){
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_param_saasid_is_null,ExceptionCode.common_error_params_code);
        }
        List<BaseEmployDO> list = this.employDao.findAllByNameAndSaasId(name,saasId);
        if (null == list || list.size() == 0) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_saasid_no_exist,ExceptionCode.common_error_params_code);
        }
        return list;
    }


    /**
     * 根据Id删除用户
     * @param employDO
     */
    @CacheEvict(key = "#employDO.id")
    @Transactional
    public void deleteBaseEmploy(BaseEmployDO employDO){
        if (StringUtils.isEmpty(employDO.getId())) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(employDO.getSaasId())) {
            throw new ApiException(BaseUserRequestMapping.BaseEmploy.message_fail_saasid_is_null, ExceptionCode.common_error_params_code);
        }
        employDO.setStatus(-1);
        this.employDao.save(employDO);
    }

}
