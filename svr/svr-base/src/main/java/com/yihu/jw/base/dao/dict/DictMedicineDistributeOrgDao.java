package com.yihu.jw.base.dao.dict;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.DictMedicineDistributeOrgDO;

/**
 * 
 * 机构药品分发字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  		2018年09月07日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface DictMedicineDistributeOrgDao extends PagingAndSortingRepository<DictMedicineDistributeOrgDO, Integer>, JpaSpecificationExecutor<DictMedicineDistributeOrgDO>  {
}