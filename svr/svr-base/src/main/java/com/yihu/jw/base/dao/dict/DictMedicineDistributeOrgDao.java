package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictMedicineDistributeOrgDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 机构药品分发字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年09月11日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface DictMedicineDistributeOrgDao extends PagingAndSortingRepository<DictMedicineDistributeOrgDO, Integer>, JpaSpecificationExecutor<DictMedicineDistributeOrgDO>  {
}