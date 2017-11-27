package com.yihu.jw.business.sms.dao;

import com.yihu.jw.base.sms.BaseSms;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmsDao extends PagingAndSortingRepository<BaseSms, String>, JpaSpecificationExecutor<BaseSms> {

}
