package com.yihu.jw.healthyhouse.dao.user;

import com.yihu.jw.healthyhouse.model.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author HZY
 * @created 2018/9/18 19:21
 */
public interface UserDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByIdAndActivated(String id,String activated);
    User findById(String id);

    User findByLoginCodeAndActivated(String loginCode,String activated);

    User findByLoginCode(String loginCode);


    @Query("from User u where u.name=?1 and u.activated=?2 ")
    User findByNameAAndActivated(String name,String activated);

    @Override
    @Transactional
    @Modifying
    @Query("update User u set u.activated = 0 where u.loginCode = ?1 ")
    void delete(String loginCode);

    @Query("select sum (u.facilityUsedCount) from User u")
    Long sumFacilityUseCout();

    Long countAllByActivatedAndUserType(Integer activated,String userType);

    Long countAllByUserType(String userType);

    Long countAllByUserTypeAndCreateTimeBetween(String userType,Date start,Date end);

    User findByLoginCodeAndUserType(String loginCode,String userType);
    User findByTelephoneAndUserType(String telephone,String userType);

}
