package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictDiseaseDO;
import com.yihu.jw.entity.base.dict.DictIcd10DO;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.dict.DictHealthProblemDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 健康问题字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface DictHealthProblemDao extends PagingAndSortingRepository<DictHealthProblemDO, Integer>, JpaSpecificationExecutor<DictHealthProblemDO>  {

    @Query("select code as code,name as name from DictHealthProblemDO where saasId = ?1")
    List<Map<String,Object>> findCodeAndNameBySaasId(String saasId, Pageable pageable);

    @Query("select code as code,name as name from DictHealthProblemDO")
    List<Map<String,Object>> findCodeAndName(Pageable pageable);

    List<DictHealthProblemDO> findBySaasId(String saasId);

    Long countBySaasId(String saasId);
}