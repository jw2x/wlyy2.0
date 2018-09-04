package com.yihu.wlyy.statistics.etl.convert;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.dao.WlyyDimensionQuotaDao;
import com.yihu.wlyy.statistics.vo.DataModel;
import com.yihu.wlyy.statistics.vo.FilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by chenweida on 2017/6/5.
 * 维度的key值转换器
 */
@Component
@Scope("prototype")
public class ConvertHelper {
    @Autowired
    private WlyyDimensionQuotaDao wlyyDimensionQuotaDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 把每个维度的key清洗到对应的slaveKey中
     *
     * @param filterModel     过滤之后的model
     * @param dimensionQuotas 维度的列表
     * @return
     * @throws Exception
     */
    public FilterModel convert(FilterModel filterModel, List<DimensionQuotaDO> dimensionQuotas) throws Exception {
        List<DataModel> data = filterModel.getData();
        for (int i = 0; i < dimensionQuotas.size(); i++) {
            DimensionQuotaDO temp = dimensionQuotas.get(i);
            String clazz = temp.getConvertClazz();
            if (!StringUtils.isEmpty(clazz)) {
                //反射出对象并且调用convert方法去转换对应的slavekey
                Object obj = Class.forName(clazz).newInstance();
                Method method = obj.getClass().getMethod("convert", JdbcTemplate.class, List.class, String.class, DimensionQuotaDO.class);
                data = (List<DataModel>) method.invoke(obj, jdbcTemplate, data, String.valueOf(i + 1), temp);
                filterModel.setData(data);
            }
        }
        return filterModel;
    }
}
