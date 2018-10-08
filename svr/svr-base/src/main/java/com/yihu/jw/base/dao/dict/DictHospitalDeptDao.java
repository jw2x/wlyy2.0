package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictHealthProblemDO;
import com.yihu.jw.entity.base.dict.DictIcd10DO;
import feign.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.dict.DictHospitalDeptDO;

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

    @Query("select code as code,name as name from DictHospitalDeptDO where saasId = :saasId")
    List<Map<String,Object>> findCodeAndNameBySaasId(@Param("saasId") String saasId);

    @Query("select code as code,name as name from DictHospitalDeptDO")
    List<Map<String,Object>> findCodeAndName();
}