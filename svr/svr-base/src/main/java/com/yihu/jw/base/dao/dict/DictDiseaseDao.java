package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictDiseaseDO;
import com.yihu.jw.entity.base.dict.DictJobTitleDO;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;


/**
 * 
 * 病种字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  		2018年09月05日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface DictDiseaseDao extends PagingAndSortingRepository<DictDiseaseDO, Integer>, JpaSpecificationExecutor<DictDiseaseDO>  {

    @Query("select code as code,name as name from DictDiseaseDO where saasId = :saasId")
    List<Map<String,Object>> findCodeAndNameBySaasId(@Param("saasId") String saasId, Pageable pageable);

    @Query("select code as code,name as name from DictDiseaseDO")
    List<Map<String,Object>> findCodeAndName(Pageable pageable);

    List<DictDiseaseDO> findBySaasId(String saasId);
}