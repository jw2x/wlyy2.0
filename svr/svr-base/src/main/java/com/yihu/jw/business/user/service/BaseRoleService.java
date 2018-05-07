//package com.yihu.jw.business.user.service;
//
//import com.netflix.discovery.converters.Auto;
//import com.yihu.base.mysql.query.BaseJpaService;
//import com.yihu.jw.base.user.BaseRoleDO;
//import com.yihu.jw.business.user.dao.BaseRoleDao;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.exception.code.ExceptionCode;
//import com.yihu.jw.rm.base.BaseUserRequestMapping;
//import net.bytebuddy.implementation.bytecode.Throw;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//
///**
// * Created by LiTaohong on 2017/11/28.
// * 基础角色功能
// */
//@Service
//public class BaseRoleService extends BaseJpaService<BaseRoleDO,BaseRoleDao>{
//    @Autowired
//    private BaseRoleDao baseRoleDao;
//
//    /**
//     * 创建role
//     * @param baseRoleDO
//     * @return
//     */
//    @Transactional
//    public BaseRoleDO createBaseRole(BaseRoleDO baseRoleDO){
//        if (StringUtils.isEmpty(baseRoleDO.getId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_id_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(baseRoleDO.getSaasId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_saasid_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(baseRoleDO.getName())) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_name_is_null, ExceptionCode.common_error_params_code);
//        }
//        return this.baseRoleDao.save(baseRoleDO);
//    }
//
//    /**
//     * 更新role
//     * @param baseRoleDO
//     * @return
//     */
//    @Transactional
//    public BaseRoleDO updateBaseRole(BaseRoleDO baseRoleDO){
//        if (StringUtils.isEmpty(baseRoleDO.getId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_id_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(baseRoleDO.getSaasId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_saasid_is_null, ExceptionCode.common_error_params_code);
//        }
//        return this.baseRoleDao.save(baseRoleDO);
//    }
//
//    /**
//     * 根据roleId查询role
//     * @param id
//     * @return
//     */
//    public BaseRoleDO findById(String id){
//        if (StringUtils.isEmpty(id)) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_id_is_null,ExceptionCode.common_error_params_code);
//        }
//        BaseRoleDO baseRoleDO = this.baseRoleDao.findOneById(id);
//        if (null == baseRoleDO) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_id_no_exist,ExceptionCode.common_error_params_code);
//        }
//        return baseRoleDO;
//    }
//
//    /**
//     * 查询某要saasId平台下的所有role
//     * @param saasId
//     * @return
//     */
//    public List<BaseRoleDO> findAllBySaasId(String saasId){
//        if (StringUtils.isEmpty(saasId)) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_param_saasid_is_null,ExceptionCode.common_error_params_code);
//        }
//        List<BaseRoleDO> list = this.baseRoleDao.findAllBySaasId(saasId);
//        if (null == list || list.size() == 0) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_saasid_no_exist,ExceptionCode.common_error_params_code);
//        }
//        return list;
//    }
//
//    /**
//     * 查找某一平台下某一个role
//     * @param saasId
//     * @param name
//     * @return
//     */
//    public BaseRoleDO findBySaasIdAndName(String saasId,String name){
//        if (StringUtils.isEmpty(saasId)) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_param_saasid_is_null,ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(name)) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_name_is_null,ExceptionCode.common_error_params_code);
//        }
//        BaseRoleDO baseRoleDO = this.baseRoleDao.findOneBySaasIdAndName(name,saasId);
//        if (null == baseRoleDO) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_role_no_exist,ExceptionCode.common_error_params_code);
//        }
//        return baseRoleDO;
//    }
//
//    /**
//     * 根据name查询所有的role，包括不同平台saasid的
//     * @param name
//     * @return
//     */
//    public List<BaseRoleDO> getRoleListByName(String name){
//        if (StringUtils.isEmpty(name)) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_param_name_is_null,ExceptionCode.common_error_params_code);
//        }
//        List<BaseRoleDO> list = this.baseRoleDao.findAllByName(name);
//        if (null == list || list.size() == 0) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_role_no_exist,ExceptionCode.common_error_params_code);
//        }
//        return list;
//    }
//
//
//    /**
//     * 根据roleId删除role
//     * @param baseRoleDO
//     */
//    @Transactional
//    public void deleteBaseRole(BaseRoleDO baseRoleDO){
//        if (StringUtils.isEmpty(baseRoleDO.getId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_id_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(baseRoleDO.getSaasId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_saasid_is_null, ExceptionCode.common_error_params_code);
//        }
//        baseRoleDO.setStatus(-1);
//        this.baseRoleDao.save(baseRoleDO);
//    }
//
//    /**
//     * 删除某一saasId相关的所有role
//     * @param saasId
//     */
//    @Transactional
//    public void deleteBaseRolesBySaasId(String saasId){
//        if (StringUtils.isEmpty(saasId)) {
//            throw new ApiException(BaseUserRequestMapping.BaseRole.message_fail_saasid_is_null, ExceptionCode.common_error_params_code);
//        }
//        List<BaseRoleDO> list = this.baseRoleDao.findAllBySaasId(saasId);
//        for(BaseRoleDO baseRoleDO:list){
//            baseRoleDO.setStatus(-1);
//        }
//        this.baseRoleDao.save(list);
//    }
//
//    /**
//     * 根据saasId和name删除角色
//     * @param saasId
//     * @param name
//     */
//    @Transactional
//    public void deleteBaseRoleByNameAndSaasId(String saasId,String name){
//        BaseRoleDO baseRoleDO = this.findBySaasIdAndName(saasId,name);
//        baseRoleDO.setStatus(-1);
//        this.baseRoleDao.save(baseRoleDO);
//    }
//}
