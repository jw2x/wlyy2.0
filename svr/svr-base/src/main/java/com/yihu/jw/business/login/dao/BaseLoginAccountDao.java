//package com.yihu.jw.business.login.dao;
//
//import com.yihu.jw.base.login.BaseLoginAccountDO;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
///**
// * Created by LiTaohong on 2017/12/05.
// * 账户表，识别用户类型，医生，行政人员等
// */
//public interface BaseLoginAccountDao extends PagingAndSortingRepository<BaseLoginAccountDO, String>, JpaSpecificationExecutor<BaseLoginAccountDO> {
//
//    /**
//     * 删除用户账号
//     * @param id
//     */
//    @Query("update BaseLoginAccountDO ba set ba.accountStatus = -1 where ba.id = ?1")
//    void deleteAccount(String id);
//
//    /**
//     * 锁定用户账号
//     * @param id
//     */
//    @Query("update BaseLoginAccountDO ba set ba.accountStatus = -2 where ba.id = ?1")
//    void updateAccount(String id);
//}
