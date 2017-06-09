package com.yihu.jw.manage.dao.login;

import com.yihu.jw.manage.model.login.ManageLoginLog;
import com.yihu.jw.manage.model.system.ManageMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/9.
 */
public interface ManageLoginLogDao  extends PagingAndSortingRepository<ManageLoginLog, Integer>, JpaSpecificationExecutor<ManageLoginLog> {
}
