package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictIcd10DO;
import com.yihu.jw.entity.base.dict.DictJobTitleDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

/**
 * 
 * ICD10字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface DictIcd10Dao extends PagingAndSortingRepository<DictIcd10DO, Integer>, JpaSpecificationExecutor<DictIcd10DO>  {

    @Query("select code as code,name as name from DictIcd10DO where saasId = ?1")
    List<Map<String,Object>> findCodeAndNameSaasId(String saasId, Pageable pageable);

    @Query("select code as code,name as name from DictIcd10DO")
    List<Map<String,Object>> findCodeAndName(Pageable pageable);

    List<DictIcd10DO> findBySaasId(String saasId);

    Long countBySaasId(String saasId);
}