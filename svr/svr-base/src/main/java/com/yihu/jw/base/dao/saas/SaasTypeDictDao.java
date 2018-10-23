package com.yihu.jw.base.dao.saas;

import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by zdm on 2018/10/10.
 */
public interface SaasTypeDictDao extends PagingAndSortingRepository<SaasTypeDictDO, String> {

    SaasTypeDictDO findByName(String name);

    SaasTypeDictDO findById(String id);

    SaasTypeDictDO findByCode(Integer code);

    List<SaasTypeDictDO> findListByStatus(SaasTypeDictDO.Status status);

    @Query("select Max(code) from SaasTypeDictDO ")
    Integer findTopCode();

}
