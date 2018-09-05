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
import com.yihu.jw.util.ExcelData;
import com.yihu.jw.util.ISqlUtils;
import com.yihu.jw.util.ReadExcelUtil;
import jxl.Sheet;
import jxl.Workbook;
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
            String sql1 = "select * from wlyy_hospital_service_item where 1=1 AND service_item_id = '"+specialistServiceItemDO.getId()+"'"+sqlUtil;
            List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
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
     * @param workbook
     * @return
     */
    public MixEnvelop<Boolean,Boolean> importData(Workbook workbook) {
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        Sheet[] sheets = workbook.getSheets();
        Sheet sheet = sheets[0];
        int rows = ReadExcelUtil.getRightRows(sheet);
        for (int row = 1; row < rows; row++) {  //索引从0开始，第一行为标题
            SpecialistServiceItemDO itemDO = new SpecialistServiceItemDO();
            Map<Integer, ExcelData> mapping = mapping(itemDO);
            int finalRow = row;
            mapping.forEach((index, excelData) -> {
                String value = sheet.getCell(index, finalRow).getContents().trim();
                excelData.transform(value);
            });

            //Additional Handel
            List<SpecialistServiceItemDO> specialistServiceItemDOS = specialistServiceItemDao.findByTitle(itemDO.getTitle());
           if (specialistServiceItemDOS == null && specialistServiceItemDOS.size() ==0){
               continue;
           }else {
                itemDO.setStatus(1);
                specialistServiceItemDao.save(itemDO);
           }
        }
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 表格数据转为对象
     *
     * @param specialistServiceItemDO
     * @return
     */
    private Map<Integer, ExcelData> mapping(SpecialistServiceItemDO specialistServiceItemDO) {
        Map<Integer, ExcelData> dataMap = new HashMap<>();
        //项目名称
        dataMap.put(1, new ExcelData() {
            @Override
            public void transform(String data) {
                specialistServiceItemDO.setTitle(data);
            }
        });
        //项目内涵
        dataMap.put(2, new ExcelData() {
            @Override
            public void transform(String data) {
                specialistServiceItemDO.setContent(data);
            }
        });
        //除去内容
        dataMap.put(3, new ExcelData() {
            @Override
            public void transform(String data) {
                specialistServiceItemDO.setExcludeContent(data);
            }
        });
        //项目类型
        dataMap.put(4, new ExcelData() {
            @Override
            public void transform(String data) {
                Map<String, String> centerSite = new HashMap<>();
                centerSite.put("康复服务", "1");
                centerSite.put("健康服务", "2");
                specialistServiceItemDO.setItemType(Integer.parseInt(centerSite.get(data)));
            }
        });
        //医院等级
        dataMap.put(5, new ExcelData() {
            @Override
            public void transform(String data) {
                Map<String,Integer> grade = new HashMap<>();
                grade.put("所有",0);
                grade.put("一级及一级以下医疗机构",1);
                grade.put("二级医院",2);
                grade.put("三级医院",3);
                specialistServiceItemDO.setHospitalGrade(grade.get(data));
            }
        });
        //三级医院收费
        dataMap.put(6, new ExcelData() {
            @Override
            public void transform(String data) {
                specialistServiceItemDO.setThreeHospitals(Integer.parseInt(data));
            }
        });
        //二级医院收费
        dataMap.put(7, new ExcelData() {
            @Override
            public void transform(String data) {
                specialistServiceItemDO.setTwoHospitals(Integer.parseInt(data));

            }
        });
        //一级及下收费
        dataMap.put(8, new ExcelData() {
            @Override
            public void transform(String data) {
                specialistServiceItemDO.setOneHospitals(Integer.parseInt(data));
            }
        });
        //计价单位
        dataMap.put(9, new ExcelData() {
            @Override
            public void transform(String data) {
                    specialistServiceItemDO.setUnit(Integer.parseInt(data));
            }
        });
        //加收项目
        dataMap.put(10, new ExcelData() {
            @Override
            public void transform(String data) {
                specialistServiceItemDO.setAddItem(data);
            }
        });

        //病案项目
        dataMap.put(11, new ExcelData() {
            @Override
            public void transform(String data) {
                specialistServiceItemDO.setDiseaseItem(data);
            }
        });


        //是否预约
        dataMap.put(12, new ExcelData() {
            @Override
            public void transform(String data) {
                Map<String,Integer> reserve = new HashMap<>();
                reserve.put("是",1);
                reserve.put("否",0);
                specialistServiceItemDO.setReserve(reserve.get(data));
            }
        });

        //完成方式
        dataMap.put(13, new ExcelData() {
            @Override
            public void transform(String data) {
                Map<String,Integer> type = new HashMap<>();
                type.put("扫码",1);
                type.put("上传附件",0);
                type.put("健康教育",2);
                type.put("健康指导",3);
                type.put("随访",4);
                specialistServiceItemDO.setType(type.get(data));
            }
        });

        //是否评价
        dataMap.put(14, new ExcelData() {
            @Override
            public void transform(String data) {
                Map<String,Integer> evaluate = new HashMap<>();
                evaluate.put("是",1);
                evaluate.put("否",0);
                specialistServiceItemDO.setEvaluation(evaluate.get(data));
            }
        });

        //是否生效
        dataMap.put(15, new ExcelData() {
            @Override
            public void transform(String data) {
                Map<String,Integer> imediate = new HashMap<>();
                imediate.put("是",1);
                imediate.put("否",0);
                specialistServiceItemDO.setEvaluation(imediate.get(data));
            }
        });
        specialistServiceItemDO.setId(UUID.randomUUID().toString());
        return dataMap;
    }




}
