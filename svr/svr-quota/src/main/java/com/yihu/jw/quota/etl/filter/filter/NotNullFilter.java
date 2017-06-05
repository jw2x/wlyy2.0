package com.yihu.jw.quota.etl.filter.filter;

import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.model.jpa.dimension.TjDimensionSlave;
import com.yihu.jw.quota.service.dimension.TjDimensionSlaveService;
import com.yihu.jw.quota.vo.DataModel;
import com.yihu.jw.quota.vo.ErrModel;
import com.yihu.jw.quota.vo.FilterModel;
import com.yihu.jw.quota.vo.QuotaVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/1.
 */
@Component
@Scope("prototype")
public class NotNullFilter {
    private Logger logger = LoggerFactory.getLogger(NotNullFilter.class);

    @Autowired
    private TjDimensionSlaveService dimensionSlaveService;

    /**
     * 不判断主维度的数据是否为空，只判断从维度额的数据是否为空
     *
     * @param filterModel
     * @param quotaVO
     * @return
     */
    public FilterModel filter(FilterModel filterModel, QuotaVO quotaVO) {
        try {
            //得到从维度
            List<TjDimensionSlave> tj = dimensionSlaveService.getDimensionSlaveByQuotaCode(quotaVO.getCode());

            if (Contant.compute.add.equals(quotaVO.getComputeType())) {
                //判断是否是累加计算 如果强转成list类型
                List<DataModel> dmList = (List<DataModel>) filterModel.getData();
                //过滤数据
                filterList(dmList, tj, filterModel.getErrorModels());
            } else if (Contant.compute.division.equals(quotaVO.getComputeType())) {
                //判断是否是除法计算 如果强转成Map类型 key在Contant中
                Map<String, List<DataModel>> dmMap = (Map<String, List<DataModel>>) filterModel.getData();
                List<DataModel> one = dmMap.get(Contant.extract.computeKey1);
                List<DataModel> two = dmMap.get(Contant.extract.computeKey2);
                filterList(one, tj, filterModel.getErrorModels());
                filterList(two, tj, filterModel.getErrorModels());
            }
            return filterModel;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    private void filterList(List<DataModel> dmList, List<TjDimensionSlave> tj, List<ErrModel> errorModels) {
        Iterator<DataModel> iterator= dmList.iterator();
        while (iterator.hasNext()) {
            DataModel dataModel = iterator.next();
            for (int i = 1; i <= tj.size(); i++) {
                try {
                    //根据反射判断字段值是否为空
                    Object obj = DataModel.class.getMethod("getSlaveKey" + i).invoke(dataModel);
                    if (StringUtils.isEmpty(obj)) {
                        dmList.iterator().remove();
                        errorModels.add(new ErrModel(dataModel.getBusinessId(), new StringBuffer("slaveKey" + i + " is null").toString()));
                    }
                } catch (Exception e) {
                    logger.error(dataModel.toString() + "," + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
