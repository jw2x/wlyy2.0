package com.yihu.jw.quota.etl.save;

import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.etl.save.es.ElastricSearchSave;
import com.yihu.jw.quota.model.jpa.save.TjQuotaDataSave;
import com.yihu.jw.quota.service.save.TjDataSaveService;
import com.yihu.jw.quota.util.SpringUtil;
import com.yihu.jw.quota.vo.QuotaVO;
import com.yihu.jw.quota.vo.SaveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chenweida on 2017/6/2.
 */
@Component
@Scope("prototype")
public class SaveHelper {
    @Autowired
    private TjDataSaveService datsSaveService;

    public void save(List<SaveModel> sms, QuotaVO quotaVO) {
        //查看指标保存的数据源
        TjQuotaDataSave quotaDataSave = datsSaveService.findByQuota(quotaVO.getCode());
        switch (quotaDataSave.getType()) {
            case Contant.save.mysql: {
                break;
            }
            case Contant.save.redis: {
                break;
            }
            case Contant.save.es: {
                SpringUtil.getBean(ElastricSearchSave.class).save(sms,quotaDataSave.getJsonConfig());
                break;
            }
        }
    }
}
