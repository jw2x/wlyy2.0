package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/8/16.
 */

import com.yihu.jw.entity.specialist.SpecialistServiceItemDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author wangzhinan
 * @create 2018-08-16 10:58
 * @desc 服务项目Dao
 **/
public interface SpecialistServiceItemDao extends PagingAndSortingRepository<SpecialistServiceItemDO, String>,
        JpaSpecificationExecutor<SpecialistServiceItemDO> {

    @Query("select p from SpecialistServiceItemDO p where p.title =?1")
    public List<SpecialistServiceItemDO> findByTitle(String title);

    @Query("select p from SpecialistServiceItemDO p where p.title =?1 and p.content=?2")
    public List<SpecialistServiceItemDO> findByTitleAndContent(String title,String content);


}
