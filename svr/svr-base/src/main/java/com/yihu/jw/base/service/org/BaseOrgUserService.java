package com.yihu.jw.base.service.org;

import com.yihu.jw.base.dao.org.BaseOrgUserDao;
import com.yihu.jw.entity.base.org.BaseOrgUserDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 机构与机构管理员关联信息服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年10月20日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class BaseOrgUserService extends BaseJpaService<BaseOrgUserDO, BaseOrgUserDao> {

    @Autowired
    private BaseOrgUserDao baseOrgUserDao;

    public List<BaseOrgUserDO> findAllByOrgCode(String orgCode){
        List<BaseOrgUserDO> result = new ArrayList<>();
        return StringUtils.isEmpty(orgCode) ? result : baseOrgUserDao.findAllByOrgCode(orgCode);
    }
}
