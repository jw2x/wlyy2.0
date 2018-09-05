package com.yihu.wlyy.statistics.etl.extract.db;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.jw.entity.base.statistics.JobConfigDO;
import com.yihu.jw.entity.base.team.BaseTeamDO;
import com.yihu.wlyy.statistics.dao.WlyyDimensionQuotaDao;
import com.yihu.wlyy.statistics.vo.DataModel;
import com.yihu.wlyy.statistics.vo.DictModel;
import com.yihu.wlyy.statistics.vo.SaveModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by chenweida on 2017/12/28.
 */
@Component
public class Data2Save {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private WlyyDimensionQuotaDao dimensionQuotaDao;


    public List<SaveModel> data2save(List<DataModel> dataModels, JobConfigDO quartzJobConfig, Date quotaDate, String timeLevel) throws Exception{
        //得到全部的指标
        List<DimensionQuotaDO> dimensionQuotas = dimensionQuotaDao.findDimensionQuotasByQuotaCode(quartzJobConfig.getQuotaId());
        //初始化维度的数据
        Map<String, DataModel> dataModelMap = new HashMap<>();
        if (dataModels != null) {
            for (DataModel dataModel : dataModels) {
                StringBuilder sb = new StringBuilder();
                sb.append(dataModel.getTeam());
                for(int i = 0; i < dimensionQuotas.size(); i++){
                    int slaveKeyNum = i+1;
                    String invokeKey = (String) DataModel.class.getMethod("getSlaveKey"+slaveKeyNum).invoke(dataModel);
                    sb.append("-"+invokeKey);
                }
                if(dataModelMap.get(dataModel.getTeam())==null) {
                    dataModelMap.put(sb.toString(), dataModel);
                }else{
                    double result1 = dataModelMap.get(dataModel.getTeam()).getResult1();
                    dataModel.setResult1(dataModel.getResult1()+result1);
                    dataModelMap.put(sb.toString(), dataModel);
                }
            }
        }
        //得到全部团队
        List<BaseTeamDO> teams = findAllTeam();
        List<SaveModel> savelist = new ArrayList<>();


        //没维度
        for (BaseTeamDO adminTeam : teams) {
            if(adminTeam.getOrgId().length() != 10){
                continue;
            }
            SaveModel saveModel = new SaveModel();
            saveModel.setCity("350200");
            saveModel.setCityName("厦门市");
//            saveModel.setTown(adminTeam.getTownCode());
//            saveModel.setTownName(adminTeam.getTownName());
//            saveModel.setHospital(adminTeam.getOrgCode());
//            saveModel.setHospitalName(adminTeam.getOrgName());
            saveModel.setTeam(adminTeam.getId().toString());
            saveModel.setTeamName(adminTeam.getName());
            saveModel.setQuotaCode(quartzJobConfig.getQuotaId());
            saveModel.setAreaLevel(SaveModel.teamLevel);
            saveModel.setTimeLevel(timeLevel);
            saveModel.setCreateTime(new Date());
            saveModel.setQuotaDate(quotaDate);
            saveModel.setResult1(0.0);
            saveModel.setResult2(0.0);
            //设置没有维度的数据
            if (dimensionQuotas==null || dimensionQuotas.size()==0){
                DataModel dataModel = dataModelMap.get(adminTeam.getId().toString());
                if (dataModel!=null){
                    saveModel.setResult1(dataModel.getResult1());
                    saveModel.setResult2(dataModel.getResult1());
                }
            }
            savelist.add(saveModel);
        }
        //如果有维度的数据就设置维度的数据
        if (dimensionQuotas!=null && dimensionQuotas.size()>0){
            //如果有维度就设置维度的数据
            for (int i = 0; i < dimensionQuotas.size(); i++) {
                String dictSql = dimensionQuotas.get(i).getDictSql();
                List<DictModel> dictModels = jdbcTemplate.query(dictSql, new BeanPropertyRowMapper(DictModel.class));
                int savleKeyNum = i + 1;
                savelist = setSlaveKey(savleKeyNum, savelist, dictModels,dataModelMap,dimensionQuotas.size());
            }
        }
        return savelist;
    }

    private List<SaveModel> setSlaveKey(int savleKeyNum, List<SaveModel> savelist, List<DictModel> dictModels,Map<String, DataModel> dataModelMap,int dimensize) {
        List<SaveModel> newSavelist = new ArrayList<>();
        for(SaveModel one:savelist) {
            for(DictModel dict:dictModels){
            SaveModel newSaveModel = new SaveModel();
            BeanUtils.copyProperties(one, newSaveModel);
            try {
                SaveModel.class.getMethod("setSlaveKey" + savleKeyNum, String.class).invoke(newSaveModel, dict.getCode());
                SaveModel.class.getMethod("setSlaveKey" + savleKeyNum + "Name", String.class).invoke(newSaveModel, dict.getName());
                if (savleKeyNum == dimensize) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(newSaveModel.getTeam());
                    for (int i = 1; i <= dimensize; i++) {
                        String invokeKey = (String) SaveModel.class.getMethod("getSlaveKey" + i).invoke(newSaveModel);
                        sb.append("-" + invokeKey);
                    }
                    DataModel dataModel = dataModelMap.get(sb.toString());
                    if (dataModel != null) {
                        newSaveModel.setResult1(dataModel.getResult1());
                        newSaveModel.setResult2(dataModel.getResult1());
                    }
                }
                newSavelist.add(newSaveModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }
        return newSavelist;
    }


    /**
     * 得到全部团队
     *
     * @return
     */
    private List<BaseTeamDO> findAllTeam() {
        //  Map<String, AdminTeam> adminTeamMap = new HashMap<>();
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
                " AND length (h.`code`) = 10 "+
                "AND wat.available = 1";
        List<BaseTeamDO> adminTeams = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BaseTeamDO.class));

//        for (AdminTeam adminTeam : adminTeams) {
//            adminTeamMap.put(adminTeam.getId().toString(), adminTeam);
//        }
        return adminTeams;
    }

}
