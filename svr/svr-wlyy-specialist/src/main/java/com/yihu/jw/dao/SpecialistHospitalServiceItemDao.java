package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/8/28.
 */

import com.yihu.jw.entity.specialist.HospitalServiceItemDO;
import com.yihu.jw.entity.specialist.SpecialistServiceItemDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author wangzhinan
 * @create 2018-08-28 19:54
 * @desc 机构服务项目
 **/
public interface SpecialistHospitalServiceItemDao extends PagingAndSortingRepository<HospitalServiceItemDO, String>,
        JpaSpecificationExecutor<HospitalServiceItemDO> {

    @Query("select p from HospitalServiceItemDO  p where p.hospital =?1 and p.serviceItemId =?2")
    public List<HospitalServiceItemDO> findByHospitalAndServiceItemId(String hospital,String serviceItemId);
}
