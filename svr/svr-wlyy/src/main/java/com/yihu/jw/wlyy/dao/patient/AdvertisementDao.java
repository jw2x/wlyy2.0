package com.yihu.jw.wlyy.dao.patient;

import com.yihu.jw.wlyy.entity.patient.WlyyAdvertisement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public interface AdvertisementDao extends PagingAndSortingRepository<WlyyAdvertisement, Long>, JpaSpecificationExecutor<WlyyAdvertisement> {

    @Query("from WlyyAdvertisement w where w.code =?1 and w.status!=-1")
    WlyyAdvertisement findByCode(String code);

    @Query("from WlyyAdvertisement w where w.id=?1 and w.status !=-1")
    WlyyAdvertisement findById(Long id);

    @Query("from WlyyAdvertisement w where w.code=?1 and w.status !=-1")
    List<WlyyAdvertisement> getListBySaasCode(String saasCode);
}