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

    List<OrgTree> findByLevel(Integer level);

    List<OrgTree> findByLevelLessThanEqual(Integer level);

    OrgTree findByCodeAndLevel(String code,Integer level);

    OrgTree findByCodeAndParentCode(String code,String parentCode);

    OrgTree findByCode(String code);

    boolean existsByCode(String code);
}