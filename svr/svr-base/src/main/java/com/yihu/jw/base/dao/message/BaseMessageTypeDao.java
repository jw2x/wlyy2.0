package com.yihu.jw.base.dao.message;

import com.yihu.jw.entity.base.message.BaseMessageTypeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 消息类型字典 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年09月14日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface BaseMessageTypeDao extends PagingAndSortingRepository<BaseMessageTypeDO, Integer>, JpaSpecificationExecutor<BaseMessageTypeDO>  {
}