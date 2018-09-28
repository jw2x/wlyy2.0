package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictIcd10DO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.dict.DictHealthProblemDO;

import java.util.List;

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

    List<DictIcd10DO> findCodeAndNameBySaasId(String saasId);

    List<DictIcd10DO> findCodeAndName();
}