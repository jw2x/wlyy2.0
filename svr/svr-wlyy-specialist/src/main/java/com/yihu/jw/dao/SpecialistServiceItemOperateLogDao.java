package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/8/16.
 */

import com.yihu.jw.entity.specialist.SpecialistServiceItemOperateLogDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-08-16 11:04
 * @desc 服务项目操作日志
 **/
public interface SpecialistServiceItemOperateLogDao  extends PagingAndSortingRepository<SpecialistServiceItemOperateLogDO, String>,
        JpaSpecificationExecutor<SpecialistServiceItemOperateLogDO> {

}
