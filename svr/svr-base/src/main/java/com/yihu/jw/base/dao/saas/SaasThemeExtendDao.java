package com.yihu.jw.base.dao.saas;

import com.yihu.jw.entity.base.saas.SaasThemeExtendDO;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 主题风格扩展表（type=2时存多图）
 * @author yeshijie on 2018/10/16.
 */
public interface SaasThemeExtendDao extends PagingAndSortingRepository<SaasThemeExtendDO, String> {


}
