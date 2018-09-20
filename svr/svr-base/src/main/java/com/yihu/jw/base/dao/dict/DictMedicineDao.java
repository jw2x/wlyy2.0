package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictMedicineDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


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
}