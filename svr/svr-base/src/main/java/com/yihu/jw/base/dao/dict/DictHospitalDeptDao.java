package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictDiseaseDO;
import com.yihu.jw.entity.base.dict.DictHospitalDeptDO;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

/**
 * 
 * 医院科室字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface DictHospitalDeptDao extends PagingAndSortingRepository<DictHospitalDeptDO, Integer>, JpaSpecificationExecutor<DictHospitalDeptDO>  {

    @Query("select code as code,name as name from DictHospitalDeptDO where orgCode in ?1")
    List<Map<String,Object>> findByOrgCodeIn( String orgCode, Pageable pageable);

    @Query("select code as code,name as name from DictHospitalDeptDO")
    List<Map<String,Object>> findCodeAndName(Pageable pageable);

    List<DictHospitalDeptDO> findByOrgCode(String orgCode);

    List<DictHospitalDeptDO> findByOrgCodeIn(String orgCode);
}