//package com.yihu.jw.business.user.service;
//
//import com.yihu.base.mysql.query.BaseJpaService;
//import com.yihu.jw.base.user.BaseMenuDO;
//import com.yihu.jw.base.user.BaseRoleMenuDO;
//import com.yihu.jw.business.user.dao.BaseMenuDao;
//import com.yihu.jw.business.user.dao.BaseRoleMenuDao;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.exception.code.ExceptionCode;
//import com.yihu.jw.rm.base.BaseUserRequestMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by LiTaohong on 2017/12/05.
// * 基础角色菜单功能
// */
//@Service
//public class BaseRoleMenuService extends BaseJpaService<BaseRoleMenuDO,BaseRoleMenuDao> {
//
//    @Autowired
//    private BaseRoleMenuDao baseRoleMenuDao;
//
//    @Autowired
//    private BaseMenuDao baseMenuDao;
//
//    /**
//     * 新增角色菜单
//     * @param baseRoleMenuDO
//     * @return
//     */
//    @Transactional
//    public BaseRoleMenuDO createBaseRoleMenuDO(BaseRoleMenuDO baseRoleMenuDO){
//        if (StringUtils.isEmpty(baseRoleMenuDO.getRoleId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseEmployRole.message_fail_roleId_is_null, ExceptionCode.common_error_params_code);
//        }
//        return this.baseRoleMenuDao.save(baseRoleMenuDO);
//    }
//
//    /**
//     * 批量新增角色菜单，一个角色拥有多个菜单的权限
//     * @param list
//     * @return
//     */
//    @Transactional
//    public Iterable<BaseRoleMenuDO> createBatchBaseRoleMenuDO(List<BaseRoleMenuDO> list){
//        return this.baseRoleMenuDao.save(list);
//    }
//
//    /**
//     * 修改角色菜单信息
//     * @param baseRoleMenuDO
//     * @return
//     */
//    @Transactional
//    public BaseRoleMenuDO updateBaseEmployRoleDO(BaseRoleMenuDO baseRoleMenuDO){
//        BaseRoleMenuDO OldbaseRoleMenuDO = this.baseRoleMenuDao.findOne(baseRoleMenuDO.getId());
//        if(null == OldbaseRoleMenuDO){
//            throw new ApiException(BaseUserRequestMapping.BaseRoleMenu.message_fail_baseRoleMenu_no_exist, ExceptionCode.common_error_params_code);
//        }
//        //菜单id一样，表示菜单没有变，则不修改
//        if (baseRoleMenuDO.getMenuId().equals(OldbaseRoleMenuDO.getMenuId())) {
//            throw new ApiException(BaseUserRequestMapping.BaseRoleMenu.message_fail_same_menuId, ExceptionCode.common_error_params_code);
//        }
//        baseRoleMenuDO.setRoleId(OldbaseRoleMenuDO.getRoleId());
//        return this.baseRoleMenuDao.save(baseRoleMenuDO);
//    }
//
//
//    /**
//     * 查询某一角色的菜单列表（角色与菜单为一对多关系）
//     * @param roleId
//     * @return
//     */
//    public List<BaseMenuDO> findAllByRoleId(String roleId){
//        if (StringUtils.isEmpty(roleId)) {
//            throw new ApiException(BaseUserRequestMapping.BaseRoleMenu.message_fail_roleId_is_null, ExceptionCode.common_error_params_code);
//        }
//        List<BaseRoleMenuDO> list = this.baseRoleMenuDao.findRoleMenuListByRoleId(roleId);
//        if (null == list || list.size() == 0) {
//            throw new ApiException(BaseUserRequestMapping.BaseRoleMenu.message_fail_baseRoleMenu_no_exist,ExceptionCode.common_error_params_code);
//        }
//        List<String> ids = new ArrayList<>();
//        for(BaseRoleMenuDO baseRoleMenuDO:list){
//            ids.add(baseRoleMenuDO.getMenuId());
//        }
//        Iterable<String> iterable = (Iterable<String>)ids.iterator();
//        List<BaseMenuDO> result = (List<BaseMenuDO>)baseMenuDao.findAll(iterable);
//        return result;
//    }
//
//
//    /**
//     * 根据id删除角色菜单
//     * @param id
//     * @return
//     */
//    @Transactional
//    public void deleteBaseRoleMenuDO(String id){
//        try{
//            this.baseRoleMenuDao.delete(id);
//        }
//        catch (ApiException e){
//            throw new ApiException(BaseUserRequestMapping.BaseRoleMenu.message_fail_id_is_null,ExceptionCode.common_error_params_code);
//        }
//
//    }
//
//    /**
//     * 根据roleId和menuId删除角色菜单
//     * @param roleId
//     * @param menuId
//     * @return
//     */
//    @Transactional
//    public void deleteBaseRoleMenuDO(String roleId,String menuId){
//        try{
//            this.baseRoleMenuDao.deleteOneByRoleIdAndMenuId(roleId,menuId);
//        }
//        catch (ApiException e){
//            throw new ApiException(BaseUserRequestMapping.BaseRoleMenu.message_fail_id_is_null,ExceptionCode.common_error_params_code);
//        }
//    }
//
//    /**
//     * 根据roleId和menuId删除角色菜单
//     * @param roleId
//     * @param menuIds
//     * @return
//     */
//    @Transactional
//    public void deleteBatchBaseRoleMenuDO(String roleId,String menuIds){
//        try{
//            this.baseRoleMenuDao.deleteManyByRoleIdAndMenuIds(roleId,menuIds);
//        }
//        catch (ApiException e){
//            throw new ApiException(BaseUserRequestMapping.BaseRoleMenu.message_fail_id_is_null,ExceptionCode.common_error_params_code);
//        }
//    }
//
//
//
//
//
//
//
//
//
//}
