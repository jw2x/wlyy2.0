package com.yihu.jw.base.dao.org;

import com.yihu.jw.entity.base.org.BaseOrgDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

/**
 * 
 * 机构信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseOrgDao extends PagingAndSortingRepository<BaseOrgDO, String>, JpaSpecificationExecutor<BaseOrgDO>  {

    @Query(value="select b.* from base_org b where b.code= ?1 and b.saasid= ?2 limit 1",nativeQuery = true)
    BaseOrgDO findByCodeAndSaasId(String code,String saasId);

    boolean existsByCode(String code);

    BaseOrgDO findByCode(String code);

    @Modifying
    @Query("delete from BaseOrgDO p where p.saasid=?1 ")
    void deleteBySaasId(String saasId);

    @Query("select id from BaseOrgDO where del = 1 and saasid = ?1")
    List findOrgCodeBySaasId(String saasId);

   /* @Query("select code as code,name as name from BaseOrgDO where del = 1 and saasid = ?1 ")
    List<Map<String,Object>> findOrgListBySaasId(String saasId);*/

    @Query("select code as code,name as name from BaseOrgDO where del = 1")
    List<Map<String,Object>> findOrgListBySaasId();
}