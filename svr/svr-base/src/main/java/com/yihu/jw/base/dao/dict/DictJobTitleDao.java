package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictJobTitleDO;
import com.yihu.jw.entity.base.dict.DictMedicineDO;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

/**
 * 
 * 职称字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface DictJobTitleDao extends PagingAndSortingRepository<DictJobTitleDO, Integer>, JpaSpecificationExecutor<DictJobTitleDO>  {

    @Query("select code as code,name as name from DictJobTitleDO where saasId = :saasId")
    List<Map<String,Object>> findCodeAndNameBySaasId(@Param("saasId") String saasId, Pageable pageable);

    @Query("select code as code,name as name from DictJobTitleDO")
    List<Map<String,Object>> findCodeAndName(Pageable pageable);

    List<DictJobTitleDO> findBySaasId(String saasId);
}