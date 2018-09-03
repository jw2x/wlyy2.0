package com.yihu.jw.base.dao.sms;

import com.yihu.jw.entity.base.sms.SmsTemplateDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Dao - 短信模板
 * Created by progr1mmer on 2018/8/23.
 */
public interface SmsTemplateDao extends PagingAndSortingRepository<SmsTemplateDO, String>, JpaSpecificationExecutor<SmsTemplateDO> {

    List<SmsTemplateDO> findByClientIdAndType(String clientId, SmsTemplateDO.Type type);

}
