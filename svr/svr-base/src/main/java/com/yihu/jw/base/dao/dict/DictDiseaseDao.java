package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictDiseaseDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


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
}