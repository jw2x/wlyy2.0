package com.yihu.jw.base.service.org;

import com.yihu.jw.base.dao.org.OrgTreeDao;
import com.yihu.jw.entity.base.org.BaseOrgDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 机构信息服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年10月15日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class OrgTreeService extends BaseJpaService<OrgTree, OrgTreeDao> {

    @Autowired
    private OrgTreeDao orgTreeDao;

    public void addOrgTreeNode(BaseOrgDO baseOrgDO){
//        if(){}
    }

}
