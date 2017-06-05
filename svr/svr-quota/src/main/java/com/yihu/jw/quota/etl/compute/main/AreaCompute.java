package com.yihu.jw.quota.etl.compute.main;

import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.model.jpa.compute.TjCompute;
import com.yihu.jw.quota.vo.*;
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
 */
@Component
@Scope("prototype")
public class AreaCompute {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param dmList 需要统计的元数据
     * @param sql 查询字典的sql语句
     * @param areaLevel 可能是 1 省 2 市  3区  4机构 5团队
     * @return
     */
    public Map<String, MainDimensionModel> compute(QuotaVO quotaVO,List<DataModel> dmList, String sql, String areaLevel) {
        if(StringUtils.isEmpty(sql)){
            return new HashMap<>(); //没有字典返回null
        }
        //得到字典
        List<DictModel> quotaDataSources = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DictModel.class));
        //把字典转成Map
        Map<String,String> dictModelMap=new HashMap<>();
        quotaDataSources.stream().forEach(one->{
            dictModelMap.put(one.getCode(),one.getName());
        });

        Map<String, MainDimensionModel> datas = new HashMap<>();
        //设置全部key到map中
        quotaDataSources.stream().forEach(one -> {
            datas.put(one.getCode(),null);
        });
        //只是用来是设置为空的时候填充上级
       final MainDimensionModel mainDimensionModelTemp=new MainDimensionModel();
        //数据根据key分组
        dmList.stream().forEach(one->{
            MainDimensionModel mainDimensionModel= datas.get(one.getKey(areaLevel));
            if(mainDimensionModel==null){
                mainDimensionModel=new MainDimensionModel();
                mainDimensionModel.setAreaLevel(areaLevel);
                mainDimensionModel.setProvince(one.getProvince());
                mainDimensionModel.setCity(one.getCity());
                mainDimensionModel.setTown(one.getTown());
                mainDimensionModel.setHospital(one.getHospital());
                mainDimensionModel.setTeam(one.getTeam());
                mainDimensionModel.setQuotaCode(quotaVO.getCode());
                //设置名称
                switch (areaLevel){
                    case Contant.main_dimension_areaLevel.area_province:{mainDimensionModel.setProvinceName(dictModelMap.get(one.getProvince()));break;}
                    case Contant.main_dimension_areaLevel.area_city:{mainDimensionModel.setCityName(dictModelMap.get(one.getCity()));break;}
                    case Contant.main_dimension_areaLevel.area_town:{mainDimensionModel.setTownName(dictModelMap.get(one.getTown()));break;}
                    case Contant.main_dimension_areaLevel.area_org:{mainDimensionModel.setHospitalName(dictModelMap.get(one.getHospital()));break;}
                    case Contant.main_dimension_areaLevel.area_team:{mainDimensionModel.setTeamName(dictModelMap.get(one.getTeam()));break;}
                }
                BeanUtils.copyProperties(mainDimensionModel,mainDimensionModelTemp);
            }
            //设置从维度
            mainDimensionModel.getSlaveDimensionModels().add(new SlaveDimensionModel(
                    one.getSlaveKey1(),
                    one.getSlaveKey2(),
                    one.getSlaveKey3(),
                    one.getSlaveKey4()
            ));
            datas.put(one.getKey(areaLevel),mainDimensionModel);
        });
        //如果是空的设置初始值
        initNull(quotaVO, areaLevel, quotaDataSources, datas, mainDimensionModelTemp);

        return datas;
    }

    private void initNull(QuotaVO quotaVO, String areaLevel, List<DictModel> quotaDataSources, Map<String, MainDimensionModel> datas, MainDimensionModel mainDimensionModelTemp) {
        quotaDataSources.stream().forEach(one -> {
            MainDimensionModel mainDimensionModel= datas.get(one.getCode());
            if(mainDimensionModel==null){
                mainDimensionModel=new MainDimensionModel();
                mainDimensionModel.setAreaLevel(areaLevel);
                mainDimensionModel.setQuotaCode(quotaVO.getCode());
                //设置名称
                switch (areaLevel){
                    case Contant.main_dimension_areaLevel.area_province:{
                        mainDimensionModel.setProvince(one.getCode());
                        mainDimensionModel.setProvinceName(one.getName());break;
                    }
                    case Contant.main_dimension_areaLevel.area_city:{
                        mainDimensionModel.setProvince(mainDimensionModelTemp.getProvince());
                        mainDimensionModel.setCity(one.getCode());
                        mainDimensionModel.setCityName(one.getName());break;
                    }
                    case Contant.main_dimension_areaLevel.area_town:{
                        mainDimensionModel.setProvince(mainDimensionModelTemp.getProvince());
                        mainDimensionModel.setCity(mainDimensionModelTemp.getCity());
                        mainDimensionModel.setTown(one.getCode());
                        mainDimensionModel.setTownName(one.getName());break;
                    }
                    case Contant.main_dimension_areaLevel.area_org:{
                        mainDimensionModel.setProvince(mainDimensionModelTemp.getProvince());
                        mainDimensionModel.setCity(mainDimensionModelTemp.getCity());
                        mainDimensionModel.setTown(mainDimensionModelTemp.getTown());
                        mainDimensionModel.setHospital(one.getCode());
                        mainDimensionModel.setHospitalName(one.getName());break;
                    }
                    case Contant.main_dimension_areaLevel.area_team:{
                        mainDimensionModel.setProvince(mainDimensionModelTemp.getProvince());
                        mainDimensionModel.setCity(mainDimensionModelTemp.getCity());
                        mainDimensionModel.setTown(mainDimensionModelTemp.getTown());
                        mainDimensionModel.setHospital(mainDimensionModelTemp.getHospital());
                        mainDimensionModel.setTeam(one.getCode());
                        mainDimensionModel.setTeamName(one.getName());break;
                    }
                }
                datas.put(one.getCode(),mainDimensionModel);
            }
        });
    }
}
