package com.yihu.jw.service;/**
 * Created by nature of king on 2018/8/17.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.dao.SpecialistServiceItemDao;
import com.yihu.jw.dao.SpecialistServiceItemOperateLogDao;
import com.yihu.jw.entity.specialist.HospitalServiceItemDO;
import com.yihu.jw.entity.specialist.SpecialistEvaluateDO;
import com.yihu.jw.entity.specialist.SpecialistServiceItemDO;
import com.yihu.jw.entity.specialist.SpecialistServiceItemOperateLogDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author wangzhinan
 * @create 2018-08-17 8:48
 * @desc 服务项目
 **/
@Service
@Transactional
public class SpecialistServiceItemService {

    @Autowired
    private SpecialistServiceItemDao specialistServiceItemDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SpecialistServiceItemOperateLogDao specialistServiceItemOperateLogDao;


    /**
     * 添加服务项目
     *
     * @param specialistServiceItemDO
     * @return
     */
    public MixEnvelop<Boolean,Boolean> insert(SpecialistServiceItemDO specialistServiceItemDO){
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        specialistServiceItemDao.save(specialistServiceItemDO);
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 按条件查询
     *
     * @param specialistServiceItemDO
     * @param page
     * @param size
     * @return
     */
    public MixEnvelop<SpecialistServiceItemDO,SpecialistEvaluateDO> select(SpecialistServiceItemDO specialistServiceItemDO, Integer page, Integer size){
        MixEnvelop<SpecialistServiceItemDO,SpecialistServiceItemDO> envelop = new MixEnvelop<>();
        String sql = ISqlUtils.getSql(specialistServiceItemDO,page,size,"*");
        List<SpecialistServiceItemDO> specialistServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SpecialistServiceItemDO.class));
        String sqlcount = new ISqlUtils().getSql(specialistServiceItemDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success,specialistServiceItemDOS,page,size,count);
    }


    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public MixEnvelop<Boolean,Boolean> batchDelete(List<String> ids){
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        for (int i =0;i<ids.size();i++){
            SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(ids.get(i));
            specialistServiceItemDO.setStatus(0);
            specialistServiceItemDO.setCreateTime(new Date());
            specialistServiceItemDO.setUpdateTime(new Date());
            specialistServiceItemDao.save(specialistServiceItemDO);
        }
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 修改服务项目
     *
     * @param specialistServiceItemDO
     * @return
     */
    public MixEnvelop<Boolean,Boolean> update(SpecialistServiceItemDO specialistServiceItemDO){
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        SpecialistServiceItemDO specialistServiceItemDO1 = specialistServiceItemDao.findOne(specialistServiceItemDO.getId());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(specialistServiceItemDO1);
        String sql = ISqlUtils.getUpdateSql(specialistServiceItemDO);
        jdbcTemplate.update(sql);
        Class c = specialistServiceItemDO.getClass();
        Table table = (Table)c.getAnnotation(Table.class);
        String tableName = table.name();
        Field[] fArray = c.getDeclaredFields();
        JSONArray array = new JSONArray();
        for (Field f:fArray){
            String getMethoName = "";
            JSONObject object = new JSONObject();
            boolean isCExist = f.isAnnotationPresent(Column.class);
            if (isCExist){
                Column mc = f.getAnnotation(Column.class);
                String columeName = mc.name();
                String name = f.getName();
                Class a= f.getType();
                Object value= null;
                getMethoName = "get" + name.substring(0,1).toUpperCase()+name.substring(1);
                try {
                    Method m = c.getMethod(getMethoName);
                    value = (Object)m.invoke(specialistServiceItemDO);
                    if (value == null || "".equals(value)){
                        continue;
                    }
                    else if (value instanceof  String || value instanceof Integer){
                        value = "'"+value+"'";
                        object.put(name+"旧",jsonObject.getString(name));
                        object.put(name,value);
                        array.add(object);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        SpecialistServiceItemOperateLogDO specialistServiceItemOperateLogDO = new SpecialistServiceItemOperateLogDO();
        specialistServiceItemOperateLogDO.setSaasId("dev");
        specialistServiceItemOperateLogDO.setServiceItemId(specialistServiceItemDO.getId());
        specialistServiceItemOperateLogDO.setStatus(1);
        specialistServiceItemOperateLogDO.setOperateLog(array.toJSONString());
        specialistServiceItemOperateLogDO.setCreateTime(new Date());
        specialistServiceItemOperateLogDO.setUpdateTime(new Date());
        specialistServiceItemOperateLogDao.save(specialistServiceItemOperateLogDO);
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 根据医院code获取服务项目
     *
     * @param hospital
     * @return
     */
    public MixEnvelop<SpecialistServiceItemDO,SpecialistServiceItemDO> selectByHospital(String hospital){
        MixEnvelop<SpecialistServiceItemDO,SpecialistServiceItemDO> envelop = new MixEnvelop<>();
        String sql = "select * from wlyy_service_item where status = 1";
        List<SpecialistServiceItemDO> specialistServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SpecialistServiceItemDO.class));
        List<SpecialistServiceItemDO> specialistServiceItemDOList = new ArrayList<>();
        for (int i =0;i<specialistServiceItemDOS.size();i++){
            SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDOS.get(i);
            String sqlUtil = "";
            if (StringUtils.isNoneBlank(hospital)) {
                sqlUtil = " and hospital = '" + hospital + "'";
            }
            String sql1 = "select * from wlyy_hospital_service_item where 1=1 AND status = 1 AND service_item_id = '"+specialistServiceItemDO.getId()+"'"+sqlUtil;
            List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
            if (StringUtils.isNoneBlank(hospital)){
                if (hospitalServiceItemDOS.size() != 0 && hospitalServiceItemDOS != null){
                    specialistServiceItemDOList.add(specialistServiceItemDO);
                }
            }
            if (hospitalServiceItemDOS.size() == 0 || hospitalServiceItemDOS == null){
               specialistServiceItemDOList.add(specialistServiceItemDO);
            }
        }
        envelop.setDetailModelList(specialistServiceItemDOList);
        return envelop;
    }


    /**
     * 导数据
     *
     * @param specialistServiceItemDOS
     * @return
     */
    public MixEnvelop<Boolean,Boolean> importData(List<SpecialistServiceItemDO> specialistServiceItemDOS) {
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        if (specialistServiceItemDOS != null && specialistServiceItemDOS.size()!=0){
            for (SpecialistServiceItemDO specialistServiceItemDO:specialistServiceItemDOS){
                List<SpecialistServiceItemDO> specialistServiceItemDOList = specialistServiceItemDao.findByTitle(specialistServiceItemDO.getTitle());
                if (specialistServiceItemDOList == null && specialistServiceItemDOList.size() ==0){
                    continue;
                }else {
                    specialistServiceItemDO.setStatus(1);
                    specialistServiceItemDO.setId(UUID.randomUUID().toString());
                    specialistServiceItemDao.save(specialistServiceItemDO);
                }
            }
        }
        envelop.setObj(true);
        return envelop;
    }

}
