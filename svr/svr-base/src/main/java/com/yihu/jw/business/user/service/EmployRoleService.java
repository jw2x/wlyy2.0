//package com.yihu.jw.business.user.service;
//
//import com.yihu.base.mysql.query.BaseJpaService;
//import com.yihu.jw.base.user.BaseEmployDO;
//import com.yihu.jw.base.user.BaseEmployRoleDO;
//import com.yihu.jw.base.user.BaseRoleDO;
//import com.yihu.jw.business.user.dao.BaseRoleDao;
//import com.yihu.jw.business.user.dao.EmployRoleDao;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.exception.code.ExceptionCode;
//import com.yihu.jw.rm.base.BaseUserRequestMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by chenweida on 2017/5/11.
// * 用户角色表功能
// */
//@Service
//public class EmployRoleService extends BaseJpaService<BaseEmployRoleDO,EmployRoleDao> {
//
//    @Autowired
//    private EmployRoleDao employRoleDao;
//
//    @Autowired
//    private BaseRoleDao baseRoleDao;
//
//    /**
//     * 新增用户角色
//     * @param baseEmployRoleDO
//     * @return
//     */
//    @Transactional
//    public BaseEmployRoleDO createBaseEmployRoleDO(BaseEmployRoleDO baseEmployRoleDO){
//        if (StringUtils.isEmpty(baseEmployRoleDO.getRoleId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_roleId_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(baseEmployRoleDO.getEmployId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_employId_is_null, ExceptionCode.common_error_params_code);
//        }
//        return this.employRoleDao.save(baseEmployRoleDO);
//    }
//
//    /**
//     * 批量新增用户角色，一个用户可能有多个角色
//     * @param list
//     * @return
//     */
//    @Transactional
//    public Iterable<BaseEmployRoleDO> createBatchBaseEmployRoleDO(List<BaseEmployRoleDO> list){
//       return this.employRoleDao.save(list);
//    }
//
//    /**
//     * 修改用户角色
//     * @param baseEmployRoleDO
//     * @return
//     */
//    @Transactional
//    public BaseEmployRoleDO updateBaseEmployRoleDO(BaseEmployRoleDO baseEmployRoleDO){
//        BaseEmployRoleDO OldbaseEmployRoleDO = this.employRoleDao.findOne(baseEmployRoleDO.getId());
//        if(null == OldbaseEmployRoleDO){
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_employeeRole_no_exist, ExceptionCode.common_error_params_code);
//        }
//        if (baseEmployRoleDO.getRoleId().equals(OldbaseEmployRoleDO.getRoleId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_same_roleId, ExceptionCode.common_error_params_code);
//        }
//        baseEmployRoleDO.setEmployId(OldbaseEmployRoleDO.getEmployId());
//        return this.employRoleDao.save(baseEmployRoleDO);
//    }
//
//
//    /**
//     * 查询某一用户的角色列表（用户与角色为一对多关系）
//     * @param employId
//     * @return
//     */
//    public List<BaseRoleDO> findAllByEmployId(String employId){
//        if (StringUtils.isEmpty(employId)) {
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_employId_is_null, ExceptionCode.common_error_params_code);
//        }
//        List<BaseEmployRoleDO> list = this.employRoleDao.findRoleListByEmployId(employId);
//        if (null == list || list.size() == 0) {
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_employeeRole_no_exist,ExceptionCode.common_error_params_code);
//        }
//        List ids = new ArrayList<>();
//        for(BaseEmployRoleDO baseEmployRoleDO:list){
//            ids.add(baseEmployRoleDO.getRoleId());
//        }
//        Iterable iterable = (Iterable)ids.iterator();
//        List<BaseRoleDO> result = (List<BaseRoleDO>)baseRoleDao.findAll(iterable);
//        return result;
//    }
//
//
//    /**
//     * 根据Id删除用户角色
//     * @param id
//     * @return
//     */
//    @Transactional
//    public void deleteBaseEmployRoleDO(String id){
//        try{
//            this.employRoleDao.delete(id);
//        }
//        catch (ApiException e){
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_id_is_null,ExceptionCode.common_error_params_code);
//        }
//
//    }
//
//    /**
//     * 根据用户Id和角色id删除用户角色
//     * @param employId
//     * @param roleIds
//     * @return
//     */
//    @Transactional
//    public void deleteBaseEmployRoleDO(String employId,String roleIds){
//        try{
//            this.employRoleDao.deleteOneByEmployIdAndRoleId(employId,roleIds);
//        }
//        catch (ApiException e){
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_id_is_null,ExceptionCode.common_error_params_code);
//        }
//
//    }
//
//    /**
//     * 批量删除用户角色
//     * @param employId
//     * @param roleIds
//     * @return
//     */
//    @Transactional
//    public void deleteBatchBaseEmployRoleDO(String employId,String roleIds){
//        try{
//            this.employRoleDao.deleteManyByEmployIdAndRoleIds(employId,roleIds);
//        }
//        catch (ApiException e){
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_id_is_null,ExceptionCode.common_error_params_code);
//        }
//
//    }
//
//}
