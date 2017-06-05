package com.yihu.jw.quota.etl.compute.slave;

import com.yihu.jw.quota.model.jpa.dimension.TjQuotaDimensionSlave;
import com.yihu.jw.quota.vo.DictModel;
import com.yihu.jw.quota.vo.MainDimensionModel;
import com.yihu.jw.quota.vo.QuotaDimensionSlaveVO;
import com.yihu.jw.quota.vo.SlaveDimensionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/1.
 * 从维度计算的类
 */
@Component
@Scope("prototype")
public class SlaveCompute {
    private Logger logger= LoggerFactory.getLogger(SlaveCompute.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, MainDimensionModel> compute(Map<String, MainDimensionModel> mainData,  List<QuotaDimensionSlaveVO> quotaDimensionSlaves) {
        try {
            //找出所有得到字典放到里面List里面去
            List<Map<String, String>> dictList = new ArrayList<>();
            for (int i = 0; i < quotaDimensionSlaves.size(); i++) {
                Map<String, String> dictMap = new HashMap<>();
                QuotaDimensionSlaveVO tjQuotaDimensionSlave= quotaDimensionSlaves.get(i);

                String dictSql=tjQuotaDimensionSlave.getDictSql();
                if(StringUtils.isEmpty(dictSql)){
                    logger.warn("slaveDict sql is null ,quotaID:"+tjQuotaDimensionSlave.getQuotaCode()+",quotaSlaveId:"+tjQuotaDimensionSlave.getId());
                }
                List<DictModel> quotaDataSources = jdbcTemplate.query(dictSql, new BeanPropertyRowMapper(DictModel.class));
                quotaDataSources.stream().forEach(one -> {
                    dictMap.put(one.getCode(), one.getName());
                });
                dictList.add(dictMap);
            }
            //递归遍历从维度
            for (int i = 0; i < quotaDimensionSlaves.size(); i++) {
                Map<String, MainDimensionModel> temp = new HashMap<>();
                //得到维度对应的字典
                Map<String, String> dict = dictList.get(i);
                //遍历元数据
                for (Map.Entry<String, MainDimensionModel> oneMainDimensionModel : mainData.entrySet()) {
                    //遍历字典
                    for (Map.Entry<String, String> oneDict : dict.entrySet()) {
                        List<SlaveDimensionModel> slaveDimensionModels = oneMainDimensionModel.getValue().getSlaveDimensionModels();
                        //生成新的key
                        String key = new StringBuffer(oneMainDimensionModel.getKey() + "-" + oneDict.getKey()).toString();

                        List<SlaveDimensionModel> slaveDimensionModelTemps = new ArrayList<>();

                        MainDimensionModel mainDimensionModelTemp = new MainDimensionModel();
                        BeanUtils.copyProperties(oneMainDimensionModel.getValue(), mainDimensionModelTemp);

                        for (int j = 0; j < slaveDimensionModels.size(); j++) {
                            SlaveDimensionModel oneSlaveDimensionModel = slaveDimensionModels.get(j);
                            SlaveDimensionModel slaveDimensionModelTemp = new SlaveDimensionModel();

                            BeanUtils.copyProperties(oneSlaveDimensionModel, slaveDimensionModelTemp);
                            //通过反射动态调用设置
                            String oneSlaveDimensionModelKey = (String) SlaveDimensionModel.class.getMethod(new StringBuffer("getSlaveKey" + (i+1)).toString()).invoke(oneSlaveDimensionModel);
                            if (oneDict.getKey().equals(oneSlaveDimensionModelKey)) {
                                slaveDimensionModelTemps.add(slaveDimensionModelTemp);
                            }
                        }
                        MainDimensionModel.class.getMethod(new StringBuffer("setSlaveKey"+ (i+1)).toString(),String.class).invoke(mainDimensionModelTemp,oneDict.getKey());
                        MainDimensionModel.class.getMethod(new StringBuffer("setSlaveKey"+ (i+1)+"Name").toString(),String.class).invoke(mainDimensionModelTemp,oneDict.getValue());
                        mainDimensionModelTemp.setSlaveDimensionModels(slaveDimensionModelTemps);

                        temp.put(key, mainDimensionModelTemp);
                    }
                }

                mainData = temp;
            }

            return mainData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
