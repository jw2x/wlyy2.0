package com.yihu.jw.base.dao.doctor;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yihu.jw.entity.base.doctor.BaseDoctorDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 医生基础信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseDoctorDao extends PagingAndSortingRepository<BaseDoctorDO, String>, JpaSpecificationExecutor<BaseDoctorDO>  {

}