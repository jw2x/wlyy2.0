//package com.yihu.jw.business.user.service;
//
//import com.yihu.base.mysql.query.BaseJpaService;
//import com.yihu.jw.base.user.BaseMenuDO;
//import com.yihu.jw.base.user.BaseMenuDO;
//import com.yihu.jw.business.user.dao.BaseMenuDao;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.exception.code.ExceptionCode;
//import com.yihu.jw.rm.base.BaseUserRequestMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//
///**
// * Created by LiTaohong on 2017/12/5.
// * 基础菜单功能
// */
//@Service
//public class BaseMenuService extends BaseJpaService<BaseMenuDO,BaseMenuDao>{
//    @Autowired
//    private BaseMenuDao baseMenuDao;
//
//    /**
//     * 创建菜单
//     * @param baseMenuDO
//     * @return
//     */
//    @Transactional
//    public BaseMenuDO createBaseMenu(BaseMenuDO baseMenuDO){
//        if (StringUtils.isEmpty(baseMenuDO.getId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_id_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(baseMenuDO.getSaasId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
//        }
//        return this.baseMenuDao.save(baseMenuDO);
//    }
//
//    /**
//     * 更新菜单
//     * @param baseMenuDO
//     * @return
//     */
//    @Transactional
//    public BaseMenuDO updateBaseMenuDO(BaseMenuDO baseMenuDO){
//        if (StringUtils.isEmpty(baseMenuDO.getId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_id_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(baseMenuDO.getSaasId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
//        }
//        return this.baseMenuDao.save(baseMenuDO);
//    }
//
//    /**
//     * 根据Id查询菜单
//     * @param id
//     * @return
//     */
//    public BaseMenuDO findById(String id){
//        if (StringUtils.isEmpty(id)) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_id_is_null,ExceptionCode.common_error_params_code);
//        }
//        BaseMenuDO baseRoleDO = this.baseMenuDao.findOne(id);
//        if (null == baseRoleDO) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_id_no_exist,ExceptionCode.common_error_params_code);
//        }
//        return baseRoleDO;
//    }
//
//    /**
//     * 查询某要saasId平台下的所有菜单
//     * @param saasId
//     * @return
//     */
//    public List<BaseMenuDO> findAllBySaasId(String saasId){
//        if (StringUtils.isEmpty(saasId)) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_saasId_is_null,ExceptionCode.common_error_params_code);
//        }
//        List<BaseMenuDO> list = this.baseMenuDao.findAllBySaasId(saasId);
//        if (null == list || list.size() == 0) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_saasId_no_exist,ExceptionCode.common_error_params_code);
//        }
//        return list;
//    }
//
//    /**
//     * 根据Id删除菜单
//     * @param baseMenuDO
//     */
//    @Transactional
//    public void deleteBaseMenu(BaseMenuDO baseMenuDO){
//        if (StringUtils.isEmpty(baseMenuDO.getId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_id_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(baseMenuDO.getSaasId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
//        }
//        baseMenuDO.setStatus(-1);
//        this.baseMenuDao.save(baseMenuDO);
//    }
//
//    /**
//     * 删除某一saasId相关的所有菜单
//     * @param saasId
//     */
//    @Transactional
//    public void deleteBaseMenusBySaasId(String saasId){
//        if (StringUtils.isEmpty(saasId)) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
//        }
//        List<BaseMenuDO> list = this.baseMenuDao.findAllBySaasId(saasId);
//        for(BaseMenuDO baseMenuDO:list){
//            baseMenuDO.setStatus(-1);
//        }
//        this.baseMenuDao.save(list);
//    }
//
//    /**
//     * 获取某一平台下某一父级菜单的所有子菜单
//     * @param saasId
//     * @param parentId
//     * @return
//     */
//    public List<BaseMenuDO> getChlidrenMenuList(String saasId,String parentId){
//        if (StringUtils.isEmpty(saasId)) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_param_saasid_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(parentId)) {
//            throw new ApiException(BaseUserRequestMapping.BaseMenu.message_param_parentId_is_null, ExceptionCode.common_error_params_code);
//        }
//        return this.baseMenuDao.getChildrenMenuList(saasId,parentId);
//    }
//}
