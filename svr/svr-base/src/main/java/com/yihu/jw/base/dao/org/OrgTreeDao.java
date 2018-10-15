package com.yihu.jw.base.dao.org;

import com.yihu.jw.base.service.org.OrgTree;
import com.yihu.jw.base.service.org.tree.TreeNode;
import com.yihu.jw.entity.base.org.BaseOrgDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

/**
 * 
 * 机构树形结构信息 数据库访问层
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
public interface OrgTreeDao extends PagingAndSortingRepository<OrgTree, Integer>, JpaSpecificationExecutor<OrgTree>  {

    @Query("select id as id,code as code,name as name,case del when 1 then '有效' else '失效' end as status,concat(provinceName,cityName,townName,streetName) as address from BaseOrgDO where code like ?1")
    List<Map<String,Object>> findByCode(String code, Pageable pageable);

    @Override
    List<OrgTree> findAll();

}