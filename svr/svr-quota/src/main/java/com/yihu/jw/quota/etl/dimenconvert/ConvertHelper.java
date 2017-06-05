package com.yihu.jw.quota.etl.dimenconvert;

import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.model.jpa.dimension.TjQuotaDimensionSlave;
import com.yihu.jw.quota.service.dimension.TjDimensionSlaveService;
import com.yihu.jw.quota.util.SpringUtil;
import com.yihu.jw.quota.vo.DataModel;
import com.yihu.jw.quota.vo.FilterModel;
import com.yihu.jw.quota.vo.QuotaDimensionSlaveVO;
import com.yihu.jw.quota.vo.QuotaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ClassArrayEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/5.
 * 维度的key值转换器
 */
@Component
@Scope("prototype")
public class ConvertHelper {
    @Autowired
    private TjDimensionSlaveService dimensionSlaveService;

    public FilterModel convert(FilterModel filterModel, QuotaVO quotaVO) throws Exception {
        //得到从维度
        List<TjQuotaDimensionSlave> tj = dimensionSlaveService.findTjQuotaDimensionSlaveByQuotaCode(quotaVO.getCode());
        for (int i = 0; i < tj.size(); i++) {
            TjQuotaDimensionSlave temp = tj.get(i);
            if (!StringUtils.isEmpty(temp)) {
                if (Contant.compute.add.equals(quotaVO.getComputeType())) {
                    //判断是否是累加计算 如果强转成list类型
                    List<DataModel> oneList = (List<DataModel>) filterModel.getData();
                    List<QuotaDimensionSlaveVO> oneQuotaDimensionSlaveVOList = Contant.init(tj, Contant.slave_dimension_key.one);
                    String clazz=oneQuotaDimensionSlaveVOList.get(i).getConvertClazz();
                    if(!StringUtils.isEmpty(clazz)){
                        //反射出对象并且调用convert方法去转换对应的slavekey
                        Object obj=Class.forName(clazz).newInstance();
                        Method method=obj.getClass().getMethod("convert",List.class,String.class);
                        method.invoke(obj,oneList,String.valueOf(i+1));
                    }
                } else if (Contant.compute.division.equals(quotaVO.getComputeType())) {
                    //判断是否是除法计算 如果强转成Map类型 key在Contant中
                    Map<String, List<DataModel>> dmMap = (Map<String, List<DataModel>>) filterModel.getData();
                    List<DataModel> oneList = dmMap.get(Contant.extract.computeKey1);
                    List<DataModel> twoList = dmMap.get(Contant.extract.computeKey2);

                    List<QuotaDimensionSlaveVO> oneQuotaDimensionSlaveVOList = Contant.init(tj, Contant.slave_dimension_key.one);
                    String clazzOne=oneQuotaDimensionSlaveVOList.get(i).getConvertClazz();
                    if(!StringUtils.isEmpty(clazzOne)){
                        Object obj=Class.forName(clazzOne);
                        Convert.class.getMethod("convert",List.class,String.class).invoke(obj,oneList,String.valueOf(i+1));
                    }
                    dmMap.put(Contant.extract.computeKey1,oneList);
                    List<QuotaDimensionSlaveVO> twoQuotaDimensionSlaveVOList = Contant.init(tj, Contant.slave_dimension_key.two);
                    String clazzTwo=twoQuotaDimensionSlaveVOList.get(i).getConvertClazz();
                    if(!StringUtils.isEmpty(clazzTwo)){
                        Object obj=Class.forName(clazzTwo);
                        Convert.class.getMethod("convert",List.class,String.class).invoke(obj,twoList,String.valueOf(i+1));
                        dmMap.put(Contant.extract.computeKey2,twoList);
                    }
                }
            }
        }
        return filterModel;
    }
}
