package com.yihu.jw.quota.etl.extract;

import com.yihu.jw.quota.etl.extract.es.EsExtract;
import com.yihu.jw.quota.etl.model.EsConfig;
import com.yihu.jw.quota.model.jpa.dimension.TjQuotaDimensionMain;
import com.yihu.jw.quota.model.jpa.dimension.TjQuotaDimensionSlave;
import com.yihu.jw.quota.model.jpa.source.TjDataSource;
import com.yihu.jw.quota.model.jpa.source.TjQuotaDataSource;
import com.yihu.jw.quota.service.dimension.TjDimensionMainService;
import com.yihu.jw.quota.service.dimension.TjDimensionSlaveService;
import com.yihu.jw.quota.service.source.TjDataSourceService;
import com.yihu.jw.quota.util.SpringUtil;
import com.yihu.jw.quota.vo.QuotaVO;
import com.yihu.jw.quota.vo.SaveModel;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
@Component
@Scope("prototype")
public class ExtractHelper {
    @Autowired
    private TjDataSourceService dataSourceService;
    @Autowired
    private TjDimensionMainService dimensionMainService;
    @Autowired
    private TjDimensionSlaveService dimensionSlaveService;

    private Logger logger = LoggerFactory.getLogger(ExtractHelper.class);

    /**
     * 公共的抽取数据
     *
     * @param quotaVO
     * @return
     * @throws Exception
     */
    public List<SaveModel> extractData(QuotaVO quotaVO, String startTime, String endTime,String timeLevel,String saasid) throws Exception {
        try {
            //得到该指标的数据来源
            TjQuotaDataSource quotaDataSource = dataSourceService.findSourceByQuotaCode(quotaVO.getCode());
            //如果为空说明数据错误
            if (quotaDataSource == null) {
                throw new Exception("QuotaDataSource data error");
            }
            //判断数据源是什么类型,根据类型和数据库相关的配置信息抽取数据
            if (TjDataSource.type_es.equals(quotaDataSource.getType())) {
                JSONObject obj = new JSONObject().fromObject(quotaDataSource.getConfigJson());
                EsConfig esConfig= (EsConfig) JSONObject.toBean(obj,EsConfig.class);
                //得到主维度
                List<TjQuotaDimensionMain> tjQuotaDimensionMains = dimensionMainService.findTjQuotaDimensionMainByQuotaIncudeAddress(quotaDataSource.getQuotaCode());
                //得到细维度
                List<TjQuotaDimensionSlave> tjQuotaDimensionSlaves = dimensionSlaveService.findTjQuotaDimensionSlaveByQuotaCode(quotaDataSource.getQuotaCode());
                //查询ES数据
                return  SpringUtil.getBean(EsExtract.class).extract(tjQuotaDimensionMains,tjQuotaDimensionSlaves,startTime,endTime,timeLevel,saasid,quotaVO,esConfig);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("extract error:" + e.getMessage());
            logger.error("quotaVOr:" + quotaVO.toString());

        }
        return null;
    }



}
