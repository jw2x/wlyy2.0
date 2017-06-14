package com.yihu.jw.manage.dao.system;

import com.yihu.jw.manage.model.system.ManageRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/9.
 */
public interface RoleDao extends PagingAndSortingRepository<ManageRole, Long>, JpaSpecificationExecutor<ManageRole> {
}
