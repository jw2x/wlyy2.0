package com.yihu.jw.base.dao.saas;

import com.yihu.jw.entity.base.saas.BaseEmailTemplateConfigDO;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - BaseEmailTemplateConfig
 * Created by zdm on 2018/8/14.
 */
public interface BaseEmailTemplateConfigDao extends PagingAndSortingRepository<BaseEmailTemplateConfigDO, String> {

    BaseEmailTemplateConfigDO findById(String id);

    BaseEmailTemplateConfigDO findByTemplateName(String templateName);
}
