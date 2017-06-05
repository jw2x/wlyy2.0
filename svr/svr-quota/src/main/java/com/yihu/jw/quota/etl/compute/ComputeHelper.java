package com.yihu.jw.quota.etl.compute;

import com.mchange.lang.IntegerUtils;
import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.etl.compute.main.AreaCompute;
import com.yihu.jw.quota.etl.compute.slave.SlaveCompute;
import com.yihu.jw.quota.model.jpa.dimension.TjQuotaDimensionMain;
import com.yihu.jw.quota.model.jpa.dimension.TjQuotaDimensionSlave;
import com.yihu.jw.quota.service.dimension.TjDimensionMainService;
import com.yihu.jw.quota.service.dimension.TjDimensionSlaveService;
import com.yihu.jw.quota.util.SpringUtil;
import com.yihu.jw.quota.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/1.
 */
@Component
@Scope("prototype")
public class ComputeHelper {
    @Autowired
    private TjDimensionMainService dimensionMainService;
    @Autowired
    private TjDimensionSlaveService dimensionSlaveService;

    public List<SaveModel> compute(Object dataModels, QuotaVO quotaVO, String timeLevel, String saasid, String startTime) {
        //返回的list
        List<SaveModel> saveModels = new ArrayList<>();
        //只得到地址维度的所有主维度
        List<TjQuotaDimensionMain> quotaDimensionMains = dimensionMainService.findTjQuotaDimensionMainByQuotaIncudeAddress(quotaVO.getCode());
        //得到从维度
        List<TjQuotaDimensionSlave> quotaDimensionSlaves = dimensionSlaveService.findTjQuotaDimensionSlaveByQuotaCode(quotaVO.getCode());
        if (Contant.compute.add.equals(quotaVO.getComputeType())) {
            //判断是否是累加计算 如果强转成list类型
            List<DataModel> dmList = (List<DataModel>) dataModels;
            ;
            Map<String, MainDimensionModel> one = getLastData(quotaVO, quotaDimensionMains,Contant. init(quotaDimensionSlaves, Contant.slave_dimension_key.one), dmList);
            for (Map.Entry<String, MainDimensionModel> tjone : one.entrySet()) {
                SaveModel saveModel = new SaveModel();
                BeanUtils.copyProperties(tjone.getValue(), saveModel);
                saveModel.setTimeLevel(timeLevel);
                saveModel.setSaasId(saasid);
                saveModel.setQuotaDate(startTime);
                saveModel.setResult(tjone.getValue().getSlaveDimensionModels().size() );
                saveModels.add(saveModel);
            }
        } else if (Contant.compute.division.equals(quotaVO.getComputeType())) {
            //判断是否是除法计算 如果强转成Map类型 key在Contant中
            Map<String, List<DataModel>> dmMap = (Map<String, List<DataModel>>) dataModels;
            List<DataModel> oneList = dmMap.get(Contant.extract.computeKey1);
            List<DataModel> twoList = dmMap.get(Contant.extract.computeKey2);

            //计算出分子
            Map<String, MainDimensionModel> one = getLastData(quotaVO, quotaDimensionMains, Contant. init(quotaDimensionSlaves, Contant.slave_dimension_key.one), oneList);
            //计算出分母
            Map<String, MainDimensionModel> tow = getLastData(quotaVO, quotaDimensionMains, Contant. init(quotaDimensionSlaves, Contant.slave_dimension_key.two), twoList);

            for (Map.Entry<String, MainDimensionModel> tjone : one.entrySet()) {
                SaveModel saveModel = new SaveModel();
                BeanUtils.copyProperties(tjone.getValue(), saveModel);
                saveModel.setTimeLevel(timeLevel);
                saveModel.setSaasId(saasid);
                saveModel.setQuotaDate(startTime);
                //如果是2位数的计算 多保存分子和分母
                Integer oneResult=tjone.getValue().getSlaveDimensionModels().size();
                Integer twoResult=tow.get(tjone.getKey()).getSlaveDimensionModels().size();

                //为了保持数据的格式统一是integer 所以这边默认保留2位乘以100
                DecimalFormat df=new DecimalFormat("0.00");
                String tempResult=df.format((double)oneResult/twoResult);
                Integer intResult= new Double((Double.valueOf(tempResult)*100)).intValue();


                saveModel.setResult(intResult);
                saveModel.setOne(oneResult);
                saveModel.setTwo(twoResult);
                saveModels.add(saveModel);
            }
        }


        return saveModels;
    }



    private Map<String, MainDimensionModel> getLastData(QuotaVO quotaVO, List<TjQuotaDimensionMain> quotaDimensionMains, List<QuotaDimensionSlaveVO> quotaDimensionSlaves, List<DataModel> dmList) {
        Map<String, MainDimensionModel> mainData = new HashMap<>();
        //先统计主维度的数据-----地址维度
        quotaDimensionMains.stream().forEach(one -> {
            mainData.putAll(SpringUtil.getBean(AreaCompute.class).compute(
                    quotaVO,
                    dmList,//数据
                    one.getDitcSql(), //字典的sql语句
                    Contant.main_dimension_areaLevel.getAreaLevelByMainDimension(one.getType()) //维度转换
            ));
        });
        //再统计从维度的数据
        return SpringUtil.getBean(SlaveCompute.class).compute(
                mainData,//数据
                quotaDimensionSlaves //字典的sql语句
        );
    }
}
