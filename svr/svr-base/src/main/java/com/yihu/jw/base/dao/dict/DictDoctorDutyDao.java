package com.yihu.jw.base.dao.dict;

import com.yihu.jw.entity.base.dict.DictDoctorDutyDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 医生职务字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年10月19日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface DictDoctorDutyDao extends PagingAndSortingRepository<DictDoctorDutyDO, Integer>, JpaSpecificationExecutor<DictDoctorDutyDO>  {
}