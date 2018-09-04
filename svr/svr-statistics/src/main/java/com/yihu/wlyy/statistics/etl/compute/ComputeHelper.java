package com.yihu.wlyy.statistics.etl.compute;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.jw.entity.base.team.BaseTeamDO;
import com.yihu.wlyy.statistics.util.DateUtil;
import com.yihu.wlyy.statistics.vo.DataModel;
import com.yihu.wlyy.statistics.vo.SaveModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by chenweida on 2017/6/1.
 */
@Component
@Scope("prototype")
public class ComputeHelper {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * @param dataModels      过滤之后的数据
     * @param dimensionQuotas 指标的维度
     * @param wlyyJobCongId
     * @param endTime
     * @return
     */
    public List<SaveModel> compute(List<DataModel> dataModels, List<DimensionQuotaDO> dimensionQuotas, String wlyyJobCongId, String endTime, String timeLevel) {
        try {
            //得到全部团队
            List<BaseTeamDO> teams = findAllTeam();
            //计算数据 返回map key 根据团队和维度拼凑在map中的key   1-2-1  如果维度是长度是0返回团队id 1
            Map<String, List<DataModel>> computeMap = computeDataModel(dimensionQuotas, dataModels);
            //初始化map并且设置统计出来的数目
            List<SaveModel> savemodels = initAndSetResult(teams, dimensionQuotas, computeMap, wlyyJobCongId, endTime, timeLevel);

            return savemodels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * //计算数据 返回map
     *
     * @param dataModels      过滤过后的元数据
     * @param dimensionQuotas 该指标的维度
     * @return
     */
    private Map<String, List<DataModel>> computeDataModel(List<DimensionQuotaDO> dimensionQuotas, List<DataModel> dataModels) {
        Map<String, List<DataModel>> returnMap = new HashMap<>();
        dataModels.stream().forEach(one -> {
            String key = getKey(one, dimensionQuotas.size());
            List<DataModel> sms = new ArrayList<DataModel>();
            if (returnMap.containsKey(key)) {
                sms = returnMap.get(key);
            }
            sms.add(one);
            returnMap.put(key, sms);
        });

        return returnMap;
    }

    /**
     * 获取key  根据团队和维度拼凑在map中的key   1-2-1  如果维度是长度是0返回团队id 1
     *
     * @param one
     * @param size
     * @return
     */
    private String getKey(DataModel one, int size) {
        try {
            if (size > 0) {
                StringBuffer str = new StringBuffer(one.getTeam());
                for (int i = 1; i <= size; i++) {
                    String invokeKey = (String) DataModel.class.getMethod("getSlaveKey" + i).invoke(one);
                    str.append("-" + invokeKey);
                }
                return str.toString();
            } else {
                return one.getTeam();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 得到全部团队
     *
     * @return
     */
    private List<BaseTeamDO> findAllTeam() {
        String sql = "SELECT " +
                "  wat.id id, " +
                "  wat.name name, " +
                "  h.`code` org_code, " +
                "  h.`name` org_name, " +
                "  h.town   town_code, " +
                "  h.town_name  town_name " +
                "FROM " +
                "  dm_hospital h, " +
                "  wlyy_admin_team wat " +
                "WHERE " +
                "  h.`code` = wat.org_code " +
                "AND wat.available = 1";
        List<BaseTeamDO> adminTeams = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BaseTeamDO.class));
        return adminTeams;
    }

    public static void main(String[] args) {
        System.out.println("3502060200".substring(0, 8));
    }

    /**
     * 初始化map
     */
    private List<SaveModel> initAndSetResult(List<BaseTeamDO> teams, List<DimensionQuotaDO> dimensionQuotas, Map<String, List<DataModel>> countMap, String wlyyJobCongId, String endTime, String timeLevel) throws Exception {
        List<SaveModel> returnList = new ArrayList<>();
        Map<String, SaveModel> lastMaps = new HashMap<>();

        //初始化团队的数据
        for (int i = 0; i < teams.size(); i++) {
            BaseTeamDO one = teams.get(i);
            //排除测试机构
            if (one.getOrgId().length() == 10) {
                SaveModel saveModel = new SaveModel();
                saveModel.setCity("350200");
                saveModel.setCityName("厦门市");
//                saveModel.setTown(one.getTownCode());
//                saveModel.setTownName(one.getTownName());
//                //把末尾不是00的机构转换成00
//                String orgCode = one.getOrgCode();
//                if (!"00".equals(orgCode.substring(8))) {
//                    orgCode = orgCode.substring(0, 8) + "00";
//                }
//                saveModel.setHospital(orgCode);
//                saveModel.setHospitalName(one.getOrgName());
                saveModel.setTeam(one.getId().toString());
                saveModel.setTeamName(one.getName());
                saveModel.setQuotaCode(wlyyJobCongId);
                saveModel.setCreateTime(new Date());
                saveModel.setTimeLevel(timeLevel);
                saveModel.setAreaLevel("5");//团队
                saveModel.setQuotaDate(DateUtil.strToDate(endTime, "yyyy-MM-dd"));
                lastMaps.put(one.getId().toString(), saveModel);
            }
        }

        //根据维度初始化数据
            for (int i = 0; i < dimensionQuotas.size(); i++) {
                Map<String, SaveModel> mapTemp = new HashMap<>();
                DimensionQuotaDO dimensionQuota = dimensionQuotas.get(i);
//                List<DictModel> dictModels = jdbcTemplate.query(dimensionQuota.getDictSql(), new BeanPropertyRowMapper(DictModel.class));
                List<Map<String, Object>> listMap = jdbcTemplate.queryForList(dimensionQuota.getDictSql());
                for (Map.Entry<String, SaveModel> oneSaveModel : lastMaps.entrySet()) {
                    for (int j = 0; j < listMap.size(); j++) {
                        SaveModel saveModelTemp = new SaveModel();
                        Map<String, Object> dictModel = listMap.get(j);

                        BeanUtils.copyProperties(oneSaveModel.getValue(), saveModelTemp);
                        SaveModel.class.getMethod("setSlaveKey" + (i + 1) + "Name", String.class).invoke(saveModelTemp, dictModel.get("name"));
                        SaveModel.class.getMethod("setSlaveKey" + (i + 1), String.class).invoke(saveModelTemp, dictModel.get("code"));
                        //根据维度生成新的key
                        StringBuffer strKey = new StringBuffer(oneSaveModel.getKey() + "-" + dictModel.get("code"));
                        mapTemp.put(strKey.toString(), saveModelTemp);
                    }
                }
            //如果字典长度是0 抛出异常
            if (listMap.size() == 0) {
                throw new Exception("dict size:0,sql:" + dimensionQuota.getDictSql());
            }
            lastMaps = mapTemp;
        }
//        for (int i = 0; i < dimensionQuotas.size(); i++) {
//            Map<String, SaveModel> mapTemp = new HashMap<>();
//            WlyyDimensionQuota dimensionQuota = dimensionQuotas.get(i);
//            List<DictModel> dictModels = jdbcTemplate.query(dimensionQuota.getDictSql(), new BeanPropertyRowMapper(DictModel.class));
//            for (Map.Entry<String, SaveModel> oneSaveModel : lastMaps.entrySet()) {
//                for (int j = 0; j < dictModels.size(); j++) {
//                    SaveModel saveModelTemp = new SaveModel();
//                    DictModel dictModel = dictModels.get(j);
//
//                    BeanUtils.copyProperties(oneSaveModel.getValue(), saveModelTemp);
//                    SaveModel.class.getMethod("setSlaveKey" + (i + 1) + "Name", String.class).invoke(saveModelTemp, dictModel.getName());
//                    SaveModel.class.getMethod("setSlaveKey" + (i + 1), String.class).invoke(saveModelTemp, dictModel.getCode());
//                    //根据维度生成新的key
//                    StringBuffer strKey = new StringBuffer(oneSaveModel.getKey() + "-" + dictModel.getCode());
//                    mapTemp.put(strKey.toString(), saveModelTemp);
//                }
//            }
//            //如果字典长度是0 抛出异常
//            if (dictModels.size() == 0) {
//                throw new Exception("dict size:0,sql:" + dimensionQuota.getDictSql());
//            }
//            lastMaps = mapTemp;
//        }

        //设置值
        for (Map.Entry<String, SaveModel> one : lastMaps.entrySet()) {
            if (countMap.containsKey(one.getKey())) {
                List<DataModel> dataModels = countMap.get(one.getKey());
                Double result1 = 0.0;
                Double result2 = 0.0;
                for (int j = 0; j < dataModels.size(); j++) {
                    DataModel dataModel = dataModels.get(j);
                    result1 += dataModel.getNum();
                    result2++;
                }
                SaveModel saveModel = one.getValue();
                saveModel.setResult1(result1);
                saveModel.setResult2(result2);
                if(dataModels.size()>0&& StringUtils.isNotEmpty(dataModels.get(0).getSlaveKey1())&&!StringUtils.isNotEmpty(saveModel.getSlaveKey1())){
                    saveModel.setSlaveKey1(dataModels.get(0).getSlaveKey1());
                }
                if(dataModels.size()>0&& StringUtils.isNotEmpty(dataModels.get(0).getSlaveKey2())&&!StringUtils.isNotEmpty(saveModel.getSlaveKey2())){
                    saveModel.setSlaveKey2(dataModels.get(0).getSlaveKey2());
                }
                returnList.add(saveModel);
            } else {
                returnList.add(one.getValue());
            }
        }
        return returnList;
    }
}
