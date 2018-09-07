package com.yihu.jw.base.service.version;

import com.yihu.jw.base.dao.version.AppVersionDao;
import com.yihu.jw.entity.base.version.AppVersionDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * 
 * app版本号表服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年09月07日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class AppVersionService extends BaseJpaService<AppVersionDO, AppVersionDao> {
}
