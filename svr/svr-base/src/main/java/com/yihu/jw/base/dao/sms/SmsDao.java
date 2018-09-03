package com.yihu.jw.base.dao.sms;

import com.yihu.jw.entity.base.sms.SmsDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 短信记录
 * Created by progr1mmer on 2018/8/23.
 */
public interface SmsDao extends PagingAndSortingRepository<SmsDO, String>, JpaSpecificationExecutor<SmsDO> {

}
