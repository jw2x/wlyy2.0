package com.yihu.jw.base.service.org;

import com.yihu.jw.base.dao.org.BaseOrgSaasDao;
import com.yihu.jw.entity.base.org.BaseOrgSaasDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 机构与Saas关联信息服务service
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
public class BaseOrgSaasService extends BaseJpaService<BaseOrgSaasDO, BaseOrgSaasDao> {

    @Autowired
    private BaseOrgSaasDao baseOrgSaasDao;

    public List<BaseOrgSaasDO>  findBySaasid(String saasId){
        List<BaseOrgSaasDO> result =  new ArrayList<>();
        if(StringUtils.isEmpty(saasId)){
            return result;
        }
        return baseOrgSaasDao.findBySaasid(saasId);
    }
}
