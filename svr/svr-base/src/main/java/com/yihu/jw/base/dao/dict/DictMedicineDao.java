package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictMedicineDO;
import com.yihu.jw.entity.base.system.SystemDictDO;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;


/**
 * 
 * 药品字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年09月11日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface DictMedicineDao extends PagingAndSortingRepository<DictMedicineDO, Integer>, JpaSpecificationExecutor<DictMedicineDO>  {

    @Query("select code as code,name as name from DictMedicineDO where saasId = :saasId")
    List<Map<String,Object>> findCodeAndNameBySaasId(@Param("saasId") String saasId, Pageable pageable);

    @Query("select code as code,name as name from DictMedicineDO")
    List<Map<String,Object>> findCodeAndName(Pageable pageable);

    List<DictMedicineDO> findBySaasId(String saasId);
}