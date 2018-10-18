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

    @Query("select id as id,code as code,name as name,case del when 1 then '有效' else '失效' end as status,concat(provinceName,cityName,townName,streetName) as address from BaseOrgDO where code like ?1")
    List<Map<String,Object>> findByCode(String code,Pageable pageable);

    @Query("select id as id,code as code,name as name,case del when 1 then '有效' else '失效' end as status,concat(provinceName,cityName,townName,streetName) as address from BaseOrgDO where code like ?1 and del = ?2")
    List<Map<String,Object>> findByCodeAndDel(String code, String del,Pageable pageable);

    @Query("select id as id,code as code,name as name,case del when 1 then '有效' else '失效' end as status,concat(provinceName,cityName,townName,streetName) as address from BaseOrgDO where name like ?1")
    List<Map<String,Object>> findByName(String name,Pageable pageable);

    @Query("select id as id,code as code,name as name,case del when 1 then '有效' else '失效' end as status,concat(provinceName,cityName,townName,streetName) as address from BaseOrgDO where name like ?1 and del = ?2")
    List<Map<String,Object>> findByNameAndDel(String name, String del,Pageable pageable);

    @Query("select id as id,code as code,name as name,case del when 1 then '有效' else '失效' end as status,concat(provinceName,cityName,townName,streetName) as address from BaseOrgDO")
    List<Map<String,Object>> findBaseInfo(Pageable pageable);

    @Query("select id as id,code as code,name as name,case del when 1 then '有效' else '失效' end as status,concat(provinceName,cityName,townName,streetName) as address from BaseOrgDO where del = ?1")
    List<Map<String,Object>> findBaseInfoByDel(String del, Pageable pageable);

    @Query("select new BaseOrgDO(provinceCode,provinceName,cityCode,cityName,townCode,townName,code,name) from BaseOrgDO")
    List<BaseOrgDO> findOrgByArea();

    boolean existsByCode(String code);

    @Modifying
    @Query("delete from BaseOrgDO p where p.saasid=?1 ")
    void deleteBySaasId(String saasId);

    @Query("select id from BaseOrgDO where saasid = ?1")
    List findOrgCodeBySaasId(String saasId);
}