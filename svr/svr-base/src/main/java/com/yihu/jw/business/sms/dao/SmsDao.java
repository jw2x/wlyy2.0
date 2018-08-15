package com.yihu.jw.business.sms.dao;

import com.yihu.jw.entity.base.sms.BaseSmsDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface SmsDao extends PagingAndSortingRepository<BaseSmsDO, String>, JpaSpecificationExecutor<BaseSmsDO> {

    @Query("select count(a.id) from BaseSmsDO a where a.mobile = ?1 and a.createTime  between ?2 and ?3 ")
    int countByMobile(String mobile,Date begin, Date end);

    @Query("select a from BaseSmsDO a where a.mobile = ?1 and a.type = ?2 and a.saasId= ?3 order by a.createTime desc")
    List<BaseSmsDO> findByMobileType(String mobile, int type,String saasId);
}
