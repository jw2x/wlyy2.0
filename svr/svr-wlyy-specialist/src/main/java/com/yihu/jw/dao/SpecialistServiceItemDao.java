package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/8/16.
 */

import com.yihu.jw.entity.specialist.SpecialistServiceItemDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-08-16 10:58
 * @desc 服务项目Dao
 **/
public interface SpecialistServiceItemDao extends PagingAndSortingRepository<SpecialistServiceItemDO, String>,
        JpaSpecificationExecutor<SpecialistServiceItemDO> {
}
