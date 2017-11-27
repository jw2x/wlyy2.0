package com.yihu.jw.wlyy.dao.patient;

import com.yihu.jw.wlyy.patient.WlyyAdvertisementDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public interface AdvertisementDao extends PagingAndSortingRepository<WlyyAdvertisementDO, String>, JpaSpecificationExecutor<WlyyAdvertisementDO> {


    @Query("from WlyyAdvertisementDO w where w.id=?1 and w.status !=-1")
    WlyyAdvertisementDO findById(String id);

    //根据saasCode查询广告
    @Query("from WlyyAdvertisementDO w where w.saasId=?1 and w.status !=-1 order by w.sort")
    List<WlyyAdvertisementDO> getListBySaasId(String saasCode);

    //查询默认广告
    @Query("from WlyyAdvertisementDO w where w.saasId ='0' and w.status !=-1 order by w.sort")
    List<WlyyAdvertisementDO> getDefaultList();
}